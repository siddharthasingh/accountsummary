package com.cub.ao.vo;

import org.springframework.stereotype.Component;

@Component
public class AccountSummaryVo {
	private String customerId;
	private AssetsListVo listOfAssets;
	private LiabilityListVo listOfLiability;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public AssetsListVo getListOfAssets() {
		return listOfAssets;
	}
	public void setListOfAssets(AssetsListVo listOfAssets) {
		this.listOfAssets = listOfAssets;
	}
	public LiabilityListVo getListOfLiability() {
		return listOfLiability;
	}
	public void setListOfLiability(LiabilityListVo listOfLiability) {
		this.listOfLiability = listOfLiability;
	}
}
