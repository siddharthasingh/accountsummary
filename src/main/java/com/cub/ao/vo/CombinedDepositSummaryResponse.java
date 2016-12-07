package com.cub.ao.vo;

import java.math.BigDecimal;
import java.util.List;

public class CombinedDepositSummaryResponse {

	private BigDecimal combinedDepositTotalTWD;
	private List<CombinedDepositVO> listOfCombinedDepositDetails;
	private List<ForeignCurrencyTotalVO> listOfForeignCurrencyTotal;

	public BigDecimal getCombinedDepositTotalTWD() {
		return combinedDepositTotalTWD;
	}

	public void setCombinedDepositTotalTWD(BigDecimal combinedDepositTotalTWD) {
		this.combinedDepositTotalTWD = combinedDepositTotalTWD;
	}

	public List<CombinedDepositVO> getListOfCombinedDepositDetails() {
		return listOfCombinedDepositDetails;
	}

	public void setListOfCombinedDepositDetails(
			List<CombinedDepositVO> listOfCombinedDepositDetails) {
		this.listOfCombinedDepositDetails = listOfCombinedDepositDetails;
	}

	public List<ForeignCurrencyTotalVO> getListOfForeignCurrencyTotal() {
		return listOfForeignCurrencyTotal;
	}

	public void setListOfForeignCurrencyTotal(
			List<ForeignCurrencyTotalVO> listOfForeignCurrencyTotal) {
		this.listOfForeignCurrencyTotal = listOfForeignCurrencyTotal;
	}

}
