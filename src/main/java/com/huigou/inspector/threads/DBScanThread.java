package com.huigou.inspector.threads;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.huigou.inspector.common.utils.Constants;
import com.huigou.inspector.service.ProductService;
import com.huigou.inspector.vo.ProductVO;
@Component
public class DBScanThread implements Runnable {

	private static final Logger log = Logger.getLogger(DBScanThread.class);
	
	@Resource
	private ProductService productService;
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			long start = System.currentTimeMillis();
			
			List<ProductVO> products = productService.getProducts(i);
			
			long end = System.currentTimeMillis();
			
			System.out.println("time elapsed: " + (end - start));
			log.info("time elapsed: " + (end - start));
			log.info("current queue size is : " + Constants.PRODUCT_BLOCKING_QUEUE.size());
			
			Constants.PRODUCT_BLOCKING_QUEUE.addAll(products);
			if (Constants.FULL_SIZE < Constants.PRODUCT_BLOCKING_QUEUE.size()) {
				try {
					if(100000 < Constants.PRODUCT_BLOCKING_QUEUE.size()) {
						Thread.sleep(10000000);
					}else if(60000 < Constants.PRODUCT_BLOCKING_QUEUE.size()){
						Thread.sleep(6000000);
					}else if(30000 < Constants.PRODUCT_BLOCKING_QUEUE.size()){
						Thread.sleep(1800000);
					}else {
						Thread.sleep(600000);
					}
				} catch (InterruptedException e) {
					log.error("got error when load products from DB", e);
					e.printStackTrace();
				}
			}
		}
	}

}
