package com.cub.ao.vo;

import java.math.BigDecimal;
import java.util.List;


/**
 * 
 * @author Niraja Deshpande
 *
 */

public class LoanAccountResponse {

	private String customerId;
	private BigDecimal outstandingTotal;
	private List<LoanAccountVO> loanAccountList;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public BigDecimal getOutstandingTotal() {
		return outstandingTotal;
	}
	public void setOutstandingTotal(BigDecimal outstandingTotal) {
		this.outstandingTotal = outstandingTotal;
	}
	public List<LoanAccountVO> getLoanAccountList() {
		return loanAccountList;
	}
	public void setLoanAccountList(List<LoanAccountVO> loanAccountList) {
		this.loanAccountList = loanAccountList;
	}
	
	
	
}
