package com.cub.ao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Niraja Deshpande
 *
 */

public class LoanAccountVO_ implements Serializable{

	private static final long serialVersionUID = 545993571579708976L;
	
	private String accountTypeCodeDesc;
	private String loanAccountNumber;
	private String customerId;
	private String productCodeDesc;
	private BigDecimal currentPeriodPrincipalAmt;
	private int currentPeriodInterestAmt;
	private String currencyCode;
	private String facilityStartDate;
	
	 
	public String getAccountTypeCodeDesc() {
		return accountTypeCodeDesc;
	}
	public void setAccountTypeCodeDesc(String accountTypeCodeDesc) {
		this.accountTypeCodeDesc = accountTypeCodeDesc;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductCodeDesc() {
		return productCodeDesc;
	}
	public void setProductCodeDesc(String productCodeDesc) {
		this.productCodeDesc = productCodeDesc;
	}
	public BigDecimal getCurrentPeriodPrincipalAmt() {
		return currentPeriodPrincipalAmt;
	}
	public void setCurrentPeriodPrincipalAmt(BigDecimal currentPeriodPrincipalAmt) {
		this.currentPeriodPrincipalAmt = currentPeriodPrincipalAmt;
	}
	public int getCurrentPeriodInterestAmt() {
		return currentPeriodInterestAmt;
	}
	public void setCurrentPeriodInterestAmt(int currentPeriodInterestAmt) {
		this.currentPeriodInterestAmt = currentPeriodInterestAmt;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getFacilityStartDate() {
		return facilityStartDate;
	}
	public void setFacilityStartDate(String facilityStartDate) {
		this.facilityStartDate = facilityStartDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
