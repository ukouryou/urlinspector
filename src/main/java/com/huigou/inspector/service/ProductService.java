package com.huigou.inspector.service;

import java.util.List;

import com.huigou.inspector.vo.ProductVO;

public interface ProductService {

	/**
	 * load products according to the l2productid
	 * @param l2productid
	 * @return
	 */
	public List<ProductVO> getProducts(int l2productid);
	
	/**
	 * update product information
	 * 
	 * @param product
	 */
	public void updateProduct(ProductVO product);
}
