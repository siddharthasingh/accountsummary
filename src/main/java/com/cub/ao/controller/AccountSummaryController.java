package com.cub.ao.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cub.ao.exception.RecordNotFoundException;
import com.cub.ao.service.AccountSummaryServiceImpl;
import com.cub.ao.util.AccountSummaryConstants;
import com.cub.ao.vo.AccountSummaryVo;

/**
 * This Class is main controller for CustomerAccountSummary micro service
 * It contains operation to provide Account Summary for a given customer Id 
 *
 */
@RestController
public class AccountSummaryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountSummaryController.class);
	private static final String CLASS_NAME = AccountSummaryController.class.getName();
	@Autowired(required=true)
	private AccountSummaryServiceImpl accountSummaryService; 
	
	public AccountSummaryController() {		
	}	
/**
 * This operation provides account summary information for the given customer id
 * @param customerId
 * @return AccountSummaryVo 
 */
	@RequestMapping(value="/summary", method = RequestMethod.GET)	
	@ResponseBody		
	public AccountSummaryVo getAccountSummary(@RequestParam(value = "customerId", required=true) String customerId) {
		final String methodName = "getAccountSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		AccountSummaryVo accSummary;
		
		if(!StringUtils.isEmpty(customerId)){
			accSummary= accountSummaryService.getSummaryDetails(customerId);
			
		}else{
			throw new RecordNotFoundException(AccountSummaryConstants.NO_RECORDS_FOUND);
		}
		LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return accSummary;
		
	}
}
