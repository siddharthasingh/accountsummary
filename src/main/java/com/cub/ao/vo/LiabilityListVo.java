package com.cub.ao.vo;

import java.util.List;

public class LiabilityListVo {
	private String liabilityTotal;
	private List<LiabilityVo> liability;
	public String getLiabilityTotal() {
		return liabilityTotal;
	}
	public void setLiabilityTotal(String liabilityTotal) {
		this.liabilityTotal = liabilityTotal;
	}
	public List<LiabilityVo> getLiability() {
		return liability;
	}
	public void setLiability(List<LiabilityVo> liability) {
		this.liability = liability;
	}
	
			
}
