package com.dreamer.repository.goods;

import com.dreamer.domain.mall.securityCode.SecurityCode;
import com.dreamer.domain.user.enums.AgentStatus;
import com.dreamer.repository.mobile.BaseDaoImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CodeDao extends BaseDaoImpl<SecurityCode> {

    public List<SecurityCode> searchByCode(String code){
        String hql = "select sc from SecurityCode as sc left join Agent  as user on user.agentCode = sc.agentCode  where (sc.code = :code or sc.box = :code)";
        org.hibernate.query.Query query = currentSession().createQuery(hql);
        query.setParameter("code",code);
//        query.setParameter("status", AgentStatus.ACTIVE);
        return  query.list();
    }


    public List<SecurityCode> searchByCodeWithActivity(String code){
        String hql = "select sc from SecurityCode as sc left join Agent  as user on user.agentCode = sc.agentCode  where (sc.code = :code or sc.box = :code) and user.agentStatus = :status";
        org.hibernate.query.Query query = currentSession().createQuery(hql);
        query.setParameter("code",code);
        query.setParameter("status", AgentStatus.ACTIVE);
        return  query.list();
    }

    public void mergeList(Integer[] cids,String recorder,String name,String agentCode,String goodsName){
        String hql = "update security_code sc set sc.agent_code = :agentCode,sc.goods_name = :goodsName,sc.update_time = current_time(),sc.recorder =  CONCAT(sc.recorder,:recorder),sc.owner = :name where sc.id in (:cids)";
        Query query = currentSession().createNativeQuery(hql);
        query.setParameter("name",name);
        query.setParameter("agentCode",agentCode);
        query.setParameter("goodsName",goodsName);
        query.setParameter("recorder","_"+recorder);
        query.setParameterList("cids",cids);
        query.executeUpdate();
    }










}
