package com.dreamer.repository.goods;

import com.dreamer.domain.mall.securityCode.SecurityCode;
import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import com.dreamer.repository.mobile.AgentDao;
import com.dreamer.service.mobile.AgentHandler;
import com.dreamer.service.mobile.CodeHandler;
import com.dreamer.service.mobile.impl.AgentHandlerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableTransactionManagement
@ContextConfiguration(locations = {"classpath*:spring/spring-service.xml","classpath*:spring/spring-dao.xml"})
public class CodeDaoTest {

    @Autowired
    private AgentHandler agentHandler;

    @Autowired
    private CodeDao codeDao;

    @Autowired
    private CodeHandler codeHandler;


    @Autowired
    private AgentDao agentDao;

    @Test
    @Transactional
    public void findCodesByBoxs() {
        List<Object> objects = new ArrayList<>();
        objects.add("281803");
        objects.add("265912");
        objects.add("277796");
        List<String> boxs = new ArrayList<>();
        boxs.add("281803");
        boxs.add("265912");
        boxs.add("277796");
        List<SecurityCode> list = codeDao.getListIn("box",objects);
        System.out.println(list.size());
        for(String box : boxs){
            boolean isExit = false;
            for(SecurityCode code : list){
                if (box.equals(code.getBox())){
                   isExit=true;
                }
            }
            if(!isExit){
                System.out.println("======");
            }
        }
//        Long a = System.currentTimeMillis();
//        Integer code = 813001;
//        String startCode = "kam" + code;
//        List<String> agentCodes = (List<String>) agentDao.findAgentCodesAfter(startCode);
//        for (int i = 0; i < 99999; i++) {
//            Integer nowCode = code + i;
//            if (!agentCodes.get(i).contains(String.valueOf(nowCode))) {
//                System.out.println("kam"+nowCode);break;
//            }
//        }
//        Long b = System.currentTimeMillis() - a;
//        System.out.println("====" + b);
//        Long a = System.currentTimeMillis();
//       Object[] objects =(Object[])  agentHandler.findNamePidByAgentCode("kam0818155");
//        System.out.println(objects);
////        agentHandler.get("agentCode","kam081855");
////        List agents =  agentDao.findAgentBySql(289374);
//        Long b = System.currentTimeMillis() - a;
//        System.out.println("===="+b);
//        List<Object> boxs = new ArrayList<>();
//        boxs.add("81139");
//        boxs.add("149881");
//        System.out.println("---");
//        Long a = System.currentTimeMillis();
////        codeDao.getListIn("box",boxs);
//        Long b = System.currentTimeMillis() - a;
//        System.out.println("===="+b);
//
//        List<String> boxs1 = new ArrayList<>();
//        boxs1.add("81139");
//        System.out.println("---");
//        a = System.currentTimeMillis();
//        codeDao.mergeList(new Integer[]{9007738},"","","","");
//        b = System.currentTimeMillis() - a;
//        System.out.println("===="+b);

    }
    @Test
    @Transactional
    public void test(){
        System.out.println(agentHandler.createAgentCode());
    }

}