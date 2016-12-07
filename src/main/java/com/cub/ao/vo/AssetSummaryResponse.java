package com.cub.ao.vo;

import java.math.BigDecimal;
import java.util.List;

public class AssetSummaryResponse {

	private String customerId;
	private BigDecimal totalAssets;
	private List<AssetSummaryVO> assetSummaryList;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public BigDecimal getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}
	public List<AssetSummaryVO> getAssetSummaryList() {
		return assetSummaryList;
	}
	public void setAssetSummaryList(List<AssetSummaryVO> assetSummaryList) {
		this.assetSummaryList = assetSummaryList;
	}
	
	
	
}
