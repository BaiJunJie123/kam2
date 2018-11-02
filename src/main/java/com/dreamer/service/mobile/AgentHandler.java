package com.dreamer.service.mobile;

import com.dreamer.domain.user.Agent;
import com.dreamer.domain.user.AgentLevel;
import ps.mx.otter.utils.SearchParameter;

import java.util.List;

/**
 * Created by huangfei on 02/07/2017.
 */
public interface AgentHandler extends BaseHandler<Agent> {



    /**
     * 登陆 手机号/编号
     *
     * @param name
     * @param paw
     * @return
     */
    Agent login(String name, String paw);

    //找出某个代理下面有UnionId的代理
    List<Agent> findByParentHasUnionId(Integer pid);

    //创建游客
    Agent createVisitor(String unionId, String openId, String nickName, String headerImg, String refCode);

    //注册或者完善信息
    Agent selfRegister(Agent agent, String refCode);

    Agent addAgentByAdmin(Agent agent,String refCode);

    void changePaw(Integer uid,String oldP,String newP,String conP);


    boolean canReward(Agent agent);

    boolean canPmallReward(Agent agent);

    Agent findByAgentCodeOrId(String refCode);

    boolean isSuperVip(Agent agent);

    boolean isVip(Agent agent);

    boolean isNewVip(Agent agent);

    List<Agent> findAgents(SearchParameter<Agent> parameter);

    String getLevelName(Agent agent);

    void changeAgentLevel(Agent agent, Integer gid, Integer lid);

    Boolean canChangeLevelByAgent(Agent agent,Agent operater);

    void changeStatus(Integer uid,Integer tid);

    AgentLevel getLevel(Agent agent);

    Agent findBoss(Integer aid);//找一个代理的领袖

    Boolean isBoss(Integer aid,Integer pid);

    Boolean isAteam(Integer aid,Integer bid);//一个团队

    Boolean isUp(Integer aid,Integer pid);//上面的人

    /**
     *
     * @param aid
     * @param uid
     * @return
     */
    Boolean canTransferAccounts(Integer aid,Integer uid);//是否可以转货

    Boolean canTransferGoods(Integer aid,Integer uid);//是否可以转货

    Boolean isVipBoss(Integer aid);


    List<Agent> getAllChildrens(String agentCode);//获取下面所有的代理

    Object findNamePidByAgentCode(String agentCode);

    String createAgentCode();


}
