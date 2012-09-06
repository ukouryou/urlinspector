package com.huigou.inspector.vo;

import java.util.Date;

/**
 * 
 * @author andy
 *
 */

public class ProductVO {

	/** 产品ID */
	private Long productid;

	/** 产品分区标识 */
	private Integer l2productid;

	/** 产品名称 */
	private String prodName;

	/** 产品源URL */
	private String prodsrcUrl;

	/** 产品所属域名 */
	private String domain;

	/** 商城名称 */
	private String siteName;

	/** 产品状态 */
	private String status;

	/** 操作时间 */
	private Date operateTime;

	
	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Integer getL2productid() {
		return l2productid;
	}

	public void setL2productid(Integer l2productid) {
		this.l2productid = l2productid;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProdsrcUrl() {
		return prodsrcUrl;
	}

	public void setProdsrcUrl(String prodsrcUrl) {
		this.prodsrcUrl = prodsrcUrl;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}
