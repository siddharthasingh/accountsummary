package com.cub.ao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ForeignCurrencyTotalVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 550130189147648L;
	String currencyCodeDesc;
	BigDecimal availableBalanceTotal;
	BigDecimal availableBalanceTWDTotal;
	public String getCurrencyCodeDesc() {
		return currencyCodeDesc;
	}
	public void setCurrencyCodeDesc(String currencyCodeDesc) {
		this.currencyCodeDesc = currencyCodeDesc;
	}
	public BigDecimal getAvailableBalanceTotal() {
		return availableBalanceTotal;
	}
	public void setAvailableBalanceTotal(BigDecimal availableBalanceTotal) {
		this.availableBalanceTotal = availableBalanceTotal;
	}
	public BigDecimal getAvailableBalanceTWDTotal() {
		return availableBalanceTWDTotal;
	}
	public void setAvailableBalanceTWDTotal(BigDecimal availableBalanceTWDTotal) {
		this.availableBalanceTWDTotal = availableBalanceTWDTotal;
	}
	
	
}
