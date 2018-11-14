package com.business.img.entity;

import com.phiz.common.base.BaseEntity;


public class ImgData extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String link;

    private String remark;

    private Long sort;

    private Long click;

    private String tag;

    private String conver;

    private String type;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Long getClick() {
		return click;
	}

	public void setClick(Long click) {
		this.click = click;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getConver() {
		return conver;
	}

	public void setConver(String conver) {
		this.conver = conver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}