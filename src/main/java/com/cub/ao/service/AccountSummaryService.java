package com.cub.ao.service;

import org.springframework.stereotype.Service;

import com.cub.ao.vo.AccountSummaryVo;
@Service
public interface AccountSummaryService {
	
	public AccountSummaryVo getSummaryDetails(String customerId); 
}
