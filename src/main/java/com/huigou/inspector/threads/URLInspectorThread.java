package com.huigou.inspector.threads;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.huigou.inspector.common.utils.Constants;
import com.huigou.inspector.common.utils.DateUtil;
import com.huigou.inspector.service.ProductService;
import com.huigou.inspector.vo.ProductVO;

@Scope("prototype")
@Component
public class URLInspectorThread implements Runnable {

	private static final Logger log = Logger.getLogger(URLInspectorThread.class);
	
	private AtomicBoolean finishFlag = new AtomicBoolean(true);
	
	@Resource
	private ProductService productService;
	
	@Override
	public void run() {
		
		System.out.println(Thread.currentThread());
		log.info("start"+DateUtil.getYYYYMMDDHHMMSSFormat(new Date()));
		//HttpURLConnection.setFollowRedirects(false);
		
		while (finishFlag.get()) {
			
			try {
				System.out.println("run...");
				ProductVO productVO = Constants.PRODUCT_BLOCKING_QUEUE.poll(2, TimeUnit.MINUTES);
				System.out.println("product info : " + productVO);
				if (null == productVO) {
					finishFlag.set(false);
				} else {
					URL url = null;
					HttpURLConnection con = null;
					String productUrl = productVO.getProdsrcUrl();
					int counts = 0;
					
					while (counts < 3) {
						try {
							url = new URL(productUrl);
							con = (HttpURLConnection) url.openConnection();
							
							//con.setFollowRedirects(false);
							
							int state = con.getResponseCode();
							System.out.println(con.getURL());
							System.out.println(counts + "= " + state);
							
							if (200 == state) {
								String returnUrl = con.getURL().toString();
								if(productUrl.equalsIgnoreCase(returnUrl)) {
									if (log.isDebugEnabled()) {
										log.debug("URL " + productUrl + " is avaliable!");
									}
									System.out.println("URL可用！" + productUrl);
								} else {
									
									if (counts < 2) {
										continue;
									}
									counts++;
									//Constants.INVALIDURL_BLOCKING_QUEUE.add(productVO);
									this.productService.updateProduct(productVO);
									if (log.isDebugEnabled()) {
										log.debug("URL " + productUrl + " is not avaliable! HTTP code is :" + state);
									}
									log.info("URL " + productUrl + " is not avaliable! HTTP code is :" + state);
									log.info("return URL is : " + returnUrl);
									log.info("counts is : " + counts);
								}
							} else {
								
								if (counts < 2) {
									continue;
								}
								counts++;
								
								//Constants.INVALIDURL_BLOCKING_QUEUE.add(productVO);
								this.productService.updateProduct(productVO);
								log.info("URL " + productUrl + " is not avaliable! HTTP code is :" + state);
								if (log.isDebugEnabled()) {
									log.debug("URL " + productUrl + " is not avaliable! HTTP code is :" + state);
								}
							}
							break;
						} catch (Exception ex) {
							counts++;
							log.error("URL is not avaliable，conenct " + counts + " times " + productUrl, ex);
							System.out.println("URL is not avaliable，conenct " + counts + " times " + productUrl);
						} finally {
							con.disconnect();
						}
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				finishFlag.set(false);
				log.error("got error when inspector url!", e);
			}
		}
		log.info("end :" +DateUtil.getYYYYMMDDHHMMSSFormat(new Date()));

	}

}
