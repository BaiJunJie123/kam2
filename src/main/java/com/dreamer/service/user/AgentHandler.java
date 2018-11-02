package com.dreamer.service.user;

import com.dreamer.domain.authorization.Authorization;
import com.dreamer.domain.user.*;

public interface AgentHandler {

	 Agent selfRegister(Agent agent, String referrerAgentCode);






	void transfer(Agent fromAgent, Agent toAgent, User operator);

	
	 void setWxOpenIdTo(Agent agent,String openId);


	 void removeVoucher(User operator, VoucherTransfer voucherTransfer);

	 void transferPoints(User operator, PointsTransfer transfer,
			String toAgentCode, String toAgentName, Double transferPoints);


	 void addVoucher(Agent operator, VoucherTransfer transfer);



	 void addAdvance(User operator, AdvanceTransfer transfer);






	 Agent findAgentById(Integer id);


	 void batchGenerateSubdomain();

}