package com.cub.ao.vo;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public class CreditCardStatementVo {
	private String customerId;
	private String billMonth;
	private BigDecimal currentBillCycleAmt;
	private BigDecimal minBillAmt;
	private String paymentDeadline;
	public String getPaymentDeadline() {
		return paymentDeadline;
	}
	public void setPaymentDeadline(String paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}
	private BigDecimal totalRewardPt;
	private BigDecimal arearesAmt;
	private BigDecimal maxCreditLimit;	
	private BigDecimal totalConsumption;
	private BigDecimal availableLimit;	
	private BigDecimal cashWithdrawalLimit;	
	private BigDecimal cashWithdrawed;	
	private BigDecimal availableCashLimit;	
	private String settlementDate;
	private String latestPayDate;
	private BigDecimal latestPayAmt;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public BigDecimal getCurrentBillCycleAmt() {
		return currentBillCycleAmt;
	}
	public void setCurrentBillCycleAmt(BigDecimal currentBillCycleAmt) {
		this.currentBillCycleAmt = currentBillCycleAmt;
	}
	public BigDecimal getMinBillAmt() {
		return minBillAmt;
	}
	public void setMinBillAmt(BigDecimal minBillAmt) {
		this.minBillAmt = minBillAmt;
	}
	
	public BigDecimal getTotalRewardPt() {
		return totalRewardPt;
	}
	public void setTotalRewardPt(BigDecimal totalRewardPt) {
		this.totalRewardPt = totalRewardPt;
	}
	public BigDecimal getArearesAmt() {
		return arearesAmt;
	}
	public void setArearesAmt(BigDecimal arearesAmt) {
		this.arearesAmt = arearesAmt;
	}
	public BigDecimal getMaxCreditLimit() {
		return maxCreditLimit;
	}
	public void setMaxCreditLimit(BigDecimal maxCreditLimit) {
		this.maxCreditLimit = maxCreditLimit;
	}
	public BigDecimal getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(BigDecimal totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public BigDecimal getAvailableLimit() {
		return availableLimit;
	}
	public void setAvailableLimit(BigDecimal availableLimit) {
		this.availableLimit = availableLimit;
	}
	public BigDecimal getCashWithdrawalLimit() {
		return cashWithdrawalLimit;
	}
	public void setCashWithdrawalLimit(BigDecimal cashWithdrawalLimit) {
		this.cashWithdrawalLimit = cashWithdrawalLimit;
	}
	public BigDecimal getCashWithdrawed() {
		return cashWithdrawed;
	}
	public void setCashWithdrawed(BigDecimal cashWithdrawed) {
		this.cashWithdrawed = cashWithdrawed;
	}
	public BigDecimal getAvailableCashLimit() {
		return availableCashLimit;
	}
	public void setAvailableCashLimit(BigDecimal availableCashLimit) {
		this.availableCashLimit = availableCashLimit;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getLatestPayDate() {
		return latestPayDate;
	}
	public void setLatestPayDate(String latestPayDate) {
		this.latestPayDate = latestPayDate;
	}
	public BigDecimal getLatestPayAmt() {
		return latestPayAmt;
	}
	public void setLatestPayAmt(BigDecimal latestPayAmt) {
		this.latestPayAmt = latestPayAmt;
	}
}
