package com.huigou.inspector.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huigou.inspector.dao.ProductDao;
import com.huigou.inspector.service.ProductService;
import com.huigou.inspector.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	
	@Resource
	private ProductDao productDao; 
	
	@Transactional
	@Override
	public List<ProductVO> getProducts(int l2productid) {
		log.info("current prodcut table partition is " + l2productid);
		return this.productDao.getProducts(l2productid);
	}

	@Transactional
	@Override
	public void updateProduct(ProductVO product) {
		this.productDao.updateProduct(product);
	}

}
