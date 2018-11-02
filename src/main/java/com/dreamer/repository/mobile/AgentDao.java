package com.dreamer.repository.mobile;

import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.User;
import org.hibernate.criterion.*;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ps.mx.otter.utils.SearchParameter;

import java.util.List;

/**
 * Created by huangfei on 28/06/2017.
 */
@Repository
public class AgentDao extends BaseDaoImpl<Agent> {


    public List<Agent> findByParentHasUnionId(Integer pid) {
        DetachedCriteria dc = DetachedCriteria.forClass(Agent.class);
        dc.add(Restrictions.eq("parent.id", pid));
        dc.add(Restrictions.isNotNull("wxUnionID"));
        dc.add(Restrictions.ne("wxUnionID", ""));
        //TODO 没有结果不知道这里会返回什么
        return findByCriteria(dc);
    }


    public List<Agent> findAgents(SearchParameter<Agent> parameter) {
//        Example example = Example.create(parameter.getEntity()).enableLike(MatchMode.ANYWHERE);
        DetachedCriteria criteria = DetachedCriteria.forClass(Agent.class);
//        criteria.add(example);

        if(parameter.getEntity().getAgentCode()!=null){
//            criteria.add(Restrictions.eq("parent.id",parameter.getEntity().getParent().getId()));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("agentCode","%"+parameter.getEntity().getAgentCode()+"%"));
            disjunction.add(Restrictions.like("mobile","%"+parameter.getEntity().getAgentCode()+"%"));
            disjunction.add(Restrictions.like("weixin","%"+parameter.getEntity().getAgentCode()+"%"));
            disjunction.add(Restrictions.like("realName","%"+parameter.getEntity().getAgentCode()+"%"));
            criteria.add(disjunction);
        }

        if(parameter.getEntity().getParent()!=null){
            criteria.add(Restrictions.eq("parent.id",parameter.getEntity().getParent().getId()));
        }
        //时间
        if (parameter.getStartTime() != null || parameter.getEndTime() != null) {
            criteria.add(Restrictions.between("joinDate", parameter.getStartTimeByDate(), parameter.getEndTimeByDate()));
        }
        criteria.addOrder(Order.desc("joinDate"));
        return searchByPage(parameter,criteria);
    }


    public Object findNamePidByAgentCode(String agentCode){
        String sql = "select real_name,parent from user where agent_code = :agentCode";
        Query query = currentSession().createNativeQuery(sql);
//        query.setParameter("agentCode",agentCode);
        query.setParameter("agentCode",agentCode);
        return  query.uniqueResult();
    }

    public List findAgentCodesAfter(String code){
        String sql = "select DISTINCT agent_code from user where agent_code >= :agentCode order by  agent_code";
        Query query = currentSession().createNativeQuery(sql);
//        query.setParameter("agentCode",agentCode);
        query.setParameter("agentCode",code);
        return  query.list();
    }

    /**
     * 找出所有的编号
     * @return
     */
    public List<String> findAllCode(){
        String sql = "select `agent_code` from user where `agent_code`  != ''";
        Query query = currentSession().createNativeQuery(sql);
        return query.list();
    }

}