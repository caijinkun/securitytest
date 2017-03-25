package com.cjk.param.common;

import javax.validation.constraints.NotNull;

public class PageParam {
	@NotNull(message="offset不能为空")
	private Integer offset;
	@NotNull(message="limit不能为空")
	private Integer limit;
	private String order;
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
