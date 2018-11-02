package com.dreamer.service.user;

import com.dreamer.domain.account.GoodsAccount;
import com.dreamer.domain.authorization.Authorization;
import com.dreamer.domain.authorization.AuthorizationType;
import com.dreamer.domain.user.*;
import com.dreamer.domain.user.enums.AgentStatus;
import com.dreamer.domain.user.enums.UserStatus;
import com.dreamer.repository.account.GoodsAccountDAO;
import com.dreamer.repository.authorization.AuthorizationDAO;
import com.dreamer.repository.authorization.AuthorizationTypeDAO;
import com.dreamer.repository.pmall.order.OrderDAO;
import com.dreamer.repository.system.SystemParameterDAOInf;
import com.dreamer.repository.user.*;
import com.dreamer.service.user.agentCode.AgentCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ps.mx.otter.exception.ApplicationException;
import ps.mx.otter.exception.DataDuplicateException;
import ps.mx.otter.exception.DataNotFoundException;
import ps.mx.otter.exception.ValidationException;
import ps.mx.otter.utils.date.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("agentHandler")
public class AgentHandlerImpl implements AgentHandler {


    @Override
    @Transactional
    public void setWxOpenIdTo(Agent agent, String openId) {
        // TODO Auto-generated method stub
//		agent.setWxOpenid(openId);
        agentDAO.update(agent);
    }

    /* (non-Javadoc)
     * @see com.dreamer.service.user.AgentHandler#selfRegister(com.dreamer.domain.user.Agent, java.lang.String)
     */
    @Override
    @Transactional
    public Agent selfRegister(Agent agent, String referrerAgentCode) {
    return null;
    }













    /* (non-Javadoc)
     * @see com.dreamer.service.user.AgentHandler#transfer(com.dreamer.domain.user.Agent, com.dreamer.domain.user.Agent, com.dreamer.domain.user.User)
     */
    @Override
    @Transactional
    public void transfer(Agent fromAgent, Agent toAgent, User operator) {
        List<Agent> agents = agentDAO.findChildren(fromAgent);
        agents.forEach(a -> {
            a.setParent(toAgent);
        });
        agentDAO.merge(toAgent);
    }









    @Override
    @Transactional
    public void removeVoucher(User operator, VoucherTransfer voucherTransfer) {
        voucherTransferDAO.delete(voucherTransfer);
    }

    /* (non-Javadoc)
     * @see com.dreamer.service.user.AgentHandler#transferPoints(com.dreamer.domain.user.User, com.dreamer.domain.user.PointsTransfer, java.lang.String, java.lang.String, java.lang.Double)
     */
    @Override
    @Transactional
    public void transferPoints(User operator, PointsTransfer transfer, String toAgentCode, String toAgentName, Double transferPoints) {
        Agent toAgent = agentDAO.findByAgentCode(toAgentCode);
        Agent fromAgent = (Agent) transfer.getUserByFromAgent();
        if (Objects.isNull(toAgent)) {
            throw new DataNotFoundException("代理编号对应的代理不存在");
        }
        if (!Objects.equals(toAgent.getRealName(), toAgentName)) {
            throw new DataNotFoundException("代理姓名错误");
        }
        transfer.transferPoints(fromAgent, toAgent, transferPoints);
        pointsTransferDAO.save(transfer);
    }


    @Override
    @Transactional
    public void addVoucher(Agent operator, VoucherTransfer transfer) {
        Agent user_temp = mutedUserDAO.findById(3);//公司
        transfer.commit(user_temp, operator);//提交订单
        voucherTransferDAO.save(transfer);//保存
    }


    @Override
    @Transactional
    public void addAdvance(User operator, AdvanceTransfer transfer) {
        Agent user_temp = mutedUserDAO.findById(3);//公司
        transfer.commit(user_temp, operator);//提交订单
        advanceTransferDAO.save(transfer);//保存
    }

    @Override
    @Transactional
    public void batchGenerateSubdomain() {
        // TODO Auto-generated method stub
        agentDAO.batchGenerateSubdomain();
    }

    @Override
    public Agent findAgentById(Integer id) {
        return agentDAO.findById(id);
    }



    @Autowired
    private AgentDAO agentDAO;
    @Autowired
    private MutedUserDAO mutedUserDAO;

    @Autowired
    private PointsTransferDAO pointsTransferDAO;
    @Autowired
    private VoucherTransferDAO voucherTransferDAO;

    @Autowired
    private AdvanceTransferDAO advanceTransferDAO;

}
