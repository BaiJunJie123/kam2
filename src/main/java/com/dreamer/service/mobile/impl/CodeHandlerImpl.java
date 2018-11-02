package com.dreamer.service.mobile.impl;

import com.dreamer.domain.mall.securityCode.SecurityCode;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import com.dreamer.repository.goods.CodeDao;
import com.dreamer.service.mobile.AgentHandler;
import com.dreamer.service.mobile.CodeHandler;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ps.mx.otter.exception.ApplicationException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeHandlerImpl extends BaseHandlerImpl<SecurityCode> implements CodeHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //大小码绑定
    @Override
    @Transactional
    public void bind(List<Integer> boxs, List<Integer> codes, Integer perBox) {
        for (int i = 0; i < codes.size(); i++) {
            String code = String.valueOf(codes.get(i));
            String box = String.valueOf(boxs.get(i / perBox));
            SecurityCode securityCode = new SecurityCode();
            securityCode.setAgentCode("01");//设置为公司
            securityCode.setCode(code);
            securityCode.setBox(box);
            securityCode.setUpdateTime(new Date());
            securityCode.setOwner("公司");
            securityCode.setRecorder("绑定人员");
            securityCode.setGoodsName("暂未绑定");
            codeDao.merge(securityCode);
        }
    }

    @Override
    public List<SecurityCode> searchByCode(String code) {
        return codeDao.searchByCode(code);
    }

    @Override
    public List<SecurityCode> searchByCodeWithActivity(String code) {
        return codeDao.searchByCodeWithActivity(code);
    }

    //扫码录入
    @Override
    @Transactional
    public void scan(List<String> boxs, List<String> codes, String agentCode, String goodsName, User user) {
        //都为空
        if ((boxs == null || boxs.isEmpty()) && (codes == null || codes.isEmpty())) {
            throw new ApplicationException("没有检测到大码与小码!");
        }

        //大小码要分开录入
        if (boxs != null && boxs.size() != 0 && codes != null && codes.size() != 0) {
            throw new ApplicationException("大码小码请分开录入!");
        }



        List<SecurityCode> securityCodes = new ArrayList<>();

        //如果是大码 判断每一项录入的大码是否关联
        if (boxs != null && boxs.size() != 0) {
            List<Object>   objects = new ArrayList<>();
            for (String box : boxs){
                objects.add(box);
            }
            Long nowM = System.currentTimeMillis();
            securityCodes = codeDao.getListIn("box", objects);
            if(securityCodes==null||securityCodes.isEmpty()){
                throw new ApplicationException("大码不存在,没有绑定!");
            }
            Long laterM = System.currentTimeMillis() - nowM;
            logger.error("查询大码" + objects.size() + "个花费" + laterM + "毫秒");
            //如果有没有绑定的码 抛出异常
            for (String box : boxs) {
                Boolean isExit = false;
                for (SecurityCode code : securityCodes) {
                    if (code.getBox().equals(box)) {
                        isExit = true;
                        break;
                    }
                }
                if (!isExit) {
                    throw new ApplicationException("大码" + box + "没有绑定,请勿发给代理!");
                }
            }
        }
        //如果是小码
        if (codes != null && codes.size() != 0) {
            List<Object>   objects = new ArrayList<>();
            for (String b : codes) {
                objects.add(b);
            }
            securityCodes = codeDao.getListIn("code", objects);
            if(securityCodes==null||securityCodes.isEmpty()){
                throw new ApplicationException("小码不存在没有绑定!");
            }
        }
        logger.error("扫码查询用户编码:"+agentCode);
        Long nowM = System.currentTimeMillis();
        Object[]  objects = (Object[])agentHandler.findNamePidByAgentCode(agentCode);
        if (objects == null) {
            throw new ApplicationException("要绑定的代理不存在");
        }
        String name = (String) objects[0];
        Integer pid = (Integer) objects[1];
        Long laterM = System.currentTimeMillis() - nowM;
        logger.error("查询用户名字" +agentCode+"花费" + laterM + "毫秒");

        if (user.isAdmin() || user.isMutedUser()) {//公司录入
            Integer[] cids = ArrayUtils.toObject(securityCodes.stream().mapToInt(sc -> sc.getId()).toArray());
            Long a = System.currentTimeMillis();
            //公司批量修改码的属性
            codeDao.mergeList(cids, user.getRealName(), name, agentCode, goodsName);
            Long b = System.currentTimeMillis() - a;
            logger.error("公司批量修改花费"+b+"秒");
        } else {//代理录入
            Agent fromAgent = agentHandler.get(user.getId());
            //只能录入给自己的代理
            if (!pid.equals(user.getId())) {
                throw new ApplicationException("要绑定的代理不是自己的直接代理，不能录入！");
            }

            //统一修改自己的码
            Integer[] cids = ArrayUtils.toObject(securityCodes.stream().filter(sc -> sc.getAgentCode().equals(fromAgent.getAgentCode())).mapToInt(sc -> sc.getId()).toArray());
            if (cids == null || cids.length < 1) {
                logger.error("都不是自己的码!!");
                return;
            }
            Long a = System.currentTimeMillis();
            codeDao.mergeList(cids, user.getRealName(), name, agentCode, goodsName);
            Long b = System.currentTimeMillis() - a;
            logger.info("代理批量修改花费"+b+"秒");
        }

    }

    public static void main(String[] args) {
        List<SecurityCode> securityCodes = new ArrayList<>();
        SecurityCode s = new SecurityCode();
        s.setId(1);
        securityCodes.add(s);
        Integer[] cids = ArrayUtils.toObject(securityCodes.stream().filter(sss -> sss.getId().equals(1)).mapToInt(ss -> ss.getId()).toArray());
        for (int c : cids) {
            System.out.println(c);
        }
    }

    @Autowired
    private AgentHandler agentHandler;

    private CodeDao codeDao;

    public CodeDao getCodeDao() {
        return codeDao;
    }

    @Autowired
    public void setCodeDao(CodeDao codeDao) {
        super.setBaseDao(codeDao);
        this.codeDao = codeDao;
    }
}
