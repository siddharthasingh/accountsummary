package com.cub.ao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

 

public class LoanAccountVO implements Serializable {

	private static final long serialVersionUID = 545993571579708976L;

	private String accountType;
	private String loanAcctNbr;
	private String branchCode;
	private String currencyCode;
	private BigDecimal loanAmount;
	private BigDecimal outstandingLoanAmount;
	private String timestamp;
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getLoanAcctNbr() {
		return loanAcctNbr;
	}
	public void setLoanAcctNbr(String loanAcctNbr) {
		this.loanAcctNbr = loanAcctNbr;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getOutstandingLoanAmount() {
		return outstandingLoanAmount;
	}
	public void setOutstandingLoanAmount(BigDecimal outstandingLoanAmount) {
		this.outstandingLoanAmount = outstandingLoanAmount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
