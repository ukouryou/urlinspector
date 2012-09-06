package com.huigou.inspector.service;

import static org.junit.Assert.fail;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.http.conn.params.ConnConnectionParamBean;
import org.junit.Test;


import com.huigou.inspector.common.BaseTest;

public class urlConnectionTest extends BaseTest{

	@Test
	public void testDeleteTrademark() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteListOfTrademark() {
		fail("Not yet implemented");
	}

	@Test
	public void testUrlConnection() {
		//String urlStr = "http://www.360buy.com/product/5701271.html";
		//String urlStr = "http://www.amazon.cn/gp/product/B0087MRT3O2345345/ref=s9_al_bw_g107_ir01353245324534545?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=center-4&pf_rd_r=1E64XDQM4SHD3DFD6PN3&pf_rd_t=101&pf_rd_p=646272352345232&pf_rd_i=6649780512342134";
		String urlStr = "http://www.360buy.com/product/1003437721.html";
		int counts = 0;
			
		if (urlStr == null || urlStr.length() <= 0) {
			System.out.println("NULL");
		}
		URL url = null;
		
		
		HttpURLConnection con = null;
		
		
		while (counts < 3) {
			try {
				url = new URL(urlStr);
				con = (HttpURLConnection) url.openConnection();
				//con.setInstanceFollowRedirects(followRedirects)
				int state = con.getResponseCode();
				System.out.println(con.getURL());
				System.out.println(counts + "= " + state);
				if (state == 200) {
					
					String returnUrl = con.getURL().toString();
					
					if(urlStr.equalsIgnoreCase(returnUrl)) {
						// l.debug("URL可用！");
						System.out.println("URL可用！");
					} else {
						//blockingQueue.add(urlStr);
					}
				} else {
					System.out.println("does not exist!" + state);
				}
				
				
				break;
			} catch (Exception ex) {
				counts++;
				// l.error("URL不可用，连接第 " + counts + " 次" + urlStr, ex);
				System.out.println("URL不可用，连接第 " + counts + " 次" + urlStr);
				urlStr = null;
				continue;
			} finally {
				con.disconnect();
			}
		}
		System.out.println(url);
	}
	

}
