package com.huigou.inspector.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.huigou.inspector.common.utils.DateUtil;
import com.huigou.inspector.dao.BaseDao;
import com.huigou.inspector.dao.ProductDao;
import com.huigou.inspector.po.ShProduct;
import com.huigou.inspector.vo.ProductVO;

/**
 * 
 * @author andy
 *
 */

@Repository
public class ProductDaoImpl extends BaseDao implements ProductDao {
	
	private static final Logger log = Logger.getLogger(ProductDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVO> getProducts(int l2productid) {
		List<ProductVO> result = new ArrayList<ProductVO>();
		
		String hsql = " from ShProduct p where l2productid=:l2productid";
		Query q = getSession().createQuery(hsql);
		q.setInteger("l2productid", l2productid);
		List<ShProduct> list = q.list();
		if(null!=list && list.size()>0){
			
			for(ShProduct sp : list){
				
				if("1".equals(sp.getStatus())) {
					ProductVO p = new ProductVO();
					
					p.setL2productid(sp.getL2productid());
					p.setProductid(sp.getProductid());
					p.setProdsrcUrl(sp.getSrcUrl());
					
					result.add(p);
				}
			}
		}
		return result;
	}
	
	
	@Override
	public void updateProduct(ProductVO product) {
		
		String sql = "UPDATE sh_product SET status = '0', operate_time = '" + DateUtil.getYYYYMMDDHHMMSSFormat(new Date())
					+ "' WHERE productid = '" +product.getProductid()
					+"' AND l2productid = '"+product.getL2productid()+ "'";
		
		log.info(sql);
		//System.out.println(sql);
		
		getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
}
