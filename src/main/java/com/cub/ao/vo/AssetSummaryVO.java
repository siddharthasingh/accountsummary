package com.cub.ao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author 1226135
 *
 */
public class AssetSummaryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 545993571579708976L;
	private String accountType;
	private String accountNumber;
	private String branchName;
	private String currencyCode;
	private BigDecimal availableBalance;
	private BigDecimal accountBalance;
	private BigDecimal tdFaceAmount;
	private String openDate;
	private String closeDate;
	
	private String timeStamp;
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public BigDecimal getTdFaceAmount() {
		return tdFaceAmount;
	}
	public void setTdFaceAmount(BigDecimal tdFaceAmount) {
		this.tdFaceAmount = tdFaceAmount;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	
	
	
}
