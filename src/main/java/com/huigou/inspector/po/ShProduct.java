package com.huigou.inspector.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sh_product", catalog = "websiteschema")
public class ShProduct implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8779962738744098913L;
	private Long productid;
	private Integer l2productid;
	private String prodName;
	private Double price;
	private Double marketPrice;
	private Long categoryid;
	private String imgurl;
	private String srcProdid;
	private String srcUrl;
	private String domain;
	private String siteName;
	private String status;
	private Date shelveTime;
	private Date createTime;
	private String operator;
	private Date operateTime;
	private String isSearch;
	private String digest;
	private Double attention;
	private String oneStop;
	private String qualityGoods;
	private String cod;

	// Constructors

	/** default constructor */
	public ShProduct() {
	}

	/** full constructor */
	public ShProduct(Long productid, Integer l2productid, String prodName,
			Double price, Double marketPrice, Long categoryid, String imgurl, String srcProdid,
			String srcUrl, String domain, String siteName, String status, Date shelveTime,
			Date createTime, String operator, Date operateTime,
			String isSearch, String digest, Double attention, String oneStop,
			String qualityGoods, String cod) {
		this.productid = productid;
		this.l2productid = l2productid;
		this.prodName = prodName;
		this.price = price;
		this.marketPrice = marketPrice;
		this.categoryid = categoryid;
		this.imgurl = imgurl;
		this.srcProdid = srcProdid;
		this.srcUrl = srcUrl;
		this.domain = domain;
		this.siteName = siteName;
		this.status = status;
		this.shelveTime = shelveTime;
		this.createTime = createTime;
		this.operator = operator;
		this.operateTime = operateTime;
		this.isSearch = isSearch;
		this.digest = digest;
		this.attention = attention;
		this.oneStop = oneStop;
		this.qualityGoods = qualityGoods;
		this.cod = cod;
	}

	// Property accessors
	@Id
	@Column(name = "productid", nullable = false)
	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	@Column(name = "l2productid", nullable = false)
	public Integer getL2productid() {
		return l2productid;
	}

	public void setL2productid(Integer l2productid) {
		this.l2productid = l2productid;
	}

	@Column(name = "prod_name", length = 600)
	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Column(name = "price", precision = 12, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "market_price", precision = 12, scale = 0)
	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	@Column(name = "categoryid")
	public Long getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "imgurl", length = 500)
	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Column(name = "src_prodid", length = 100)
	public String getSrcProdid() {
		return srcProdid;
	}

	public void setSrcProdid(String srcProdid) {
		this.srcProdid = srcProdid;
	}

	@Column(name = "src_url", length = 800)
	public String getSrcUrl() {
		return this.srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	@Column(name = "domain", length = 200)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "site_name", length = 200)
	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Column(name = "shelve_time", length = 19)
	public Date getShelveTime() {
		return shelveTime;
	}

	public void setShelveTime(Date shelveTime) {
		this.shelveTime = shelveTime;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "operator", length = 60)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "operate_time", length = 19)
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "is_search", length = 1)
	public String getIsSearch() {
		return this.isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	@Column(name = "digest", length = 1000)
	public String getDigest() {
		return this.digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	@Column(name = "attention", precision = 12, scale = 0)
	public Double getAttention() {
		return this.attention;
	}

	public void setAttention(Double attention) {
		this.attention = attention;
	}

	@Column(name = "one_stop", length = 1)
	public String getOneStop() {
		return this.oneStop;
	}

	public void setOneStop(String oneStop) {
		this.oneStop = oneStop;
	}

	@Column(name = "quality_goods", length = 1)
	public String getQualityGoods() {
		return this.qualityGoods;
	}

	public void setQualityGoods(String qualityGoods) {
		this.qualityGoods = qualityGoods;
	}

	@Column(name = "cod", length = 1)
	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}