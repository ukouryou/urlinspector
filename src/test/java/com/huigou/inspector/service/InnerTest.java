package com.huigou.inspector.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.huigou.inspector.common.BaseTest;


public class InnerTest extends BaseTest{
	
	private BlockingQueue<String> disposedBlockingQueue = new  ArrayBlockingQueue<String>(10000,true);
	private ThreadPoolExecutor updateExecutor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
	
	@Test	
	public void testurl() throws InterruptedException{
		 
		String urlStr = "http://www.360buy.com/product/5701271.html";
			
		BlockingQueue<String> undisposedQueue = new ArrayBlockingQueue<String>(10000);
		
		undisposedQueue.add(urlStr);
		undisposedQueue.add("http://www.360buy.com/product/5701asdf27.html");
		undisposedQueue.add("http://www.360buy.com/product/5701asdf271.html");
		undisposedQueue.add("http://www.360buy.com/product/5701asdf272.html");
		undisposedQueue.add("http://www.360buy.com/product/5701asdf273.html");
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
		System.out.println("undisposed size : " + undisposedQueue.size());
		
		ExecutorService updatExecutorService = Executors.newSingleThreadExecutor();
		updatExecutorService.execute(new UpdateThread());
		
		while (true) {
			Iterator<String> iterator = undisposedQueue.iterator();
			System.out.println("undisposed size a : " + undisposedQueue.size());
			System.out.println("undisposedQueue.remainingCapacity()" + undisposedQueue.remainingCapacity());
			
			while (iterator.hasNext()) {
				executor.submit(new CurrentUrlTest(iterator.next()));
				iterator.remove();
				System.out.println("undisposed size b: " + undisposedQueue.size());
			}
			System.out.println("finished Testing : " + disposedBlockingQueue.size());
			Thread.sleep(10000L);
			
		}
     }
	
	
	public class UpdateThread implements Runnable{

		@Override
		public void run() {
			System.out.println("UpdateThread");
			
			while (true) {
				Iterator<String> iterator = disposedBlockingQueue.iterator();
				System.out.println("dispose");
				while (iterator.hasNext()) {
					System.out.println("update start!");
					updateExecutor.submit(new UpdateUrlTest(iterator.next()));
					
					//update index
					//update hbase
					//update mysql
					iterator.remove();
				}
				
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
		
	}
	
	 
	 class CurrentUrlTest implements Callable<String> {

			//String urlStr = "http://www.amazon.cn/gp/product/B0087MRT3O2345345/ref=s9_al_bw_g107_ir01353245324534545?pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=center-4&pf_rd_r=1E64XDQM4SHD3DFD6PN3&pf_rd_t=101&pf_rd_p=646272352345232&pf_rd_i=6649780512342134";
			int counts = 0;
			String urlStr = "";
			
			public CurrentUrlTest(String url) {
				this.urlStr = url;
			}
			
			@Override
			public String call() throws Exception {
				if (urlStr == null || urlStr.length() <= 0) {
					System.out.println("NULL");
				}
				URL url = null;
				
				HttpURLConnection con = null;
				
				while (counts < 3) {
					try {
						url = new URL(urlStr);
						con = (HttpURLConnection) url.openConnection();
						int state = con.getResponseCode();
						System.out.println(con.getURL());
						System.out.println(counts + "= " + state);
						if (state == 200) {
							
							String returnUrl = con.getURL().toString();
							
							if(urlStr.equalsIgnoreCase(returnUrl)) {
								// l.debug("URL可用！");
								System.out.println("URL可用！");
							} else {
								disposedBlockingQueue.add(urlStr);
							}
						} else {
							disposedBlockingQueue.add(urlStr);
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
				return null;
			}
		}
	 
	 class UpdateUrlTest implements Callable<String> {

		 private String strUrl;
		 
		 public UpdateUrlTest (String url) {
			 this.strUrl = url;
		 }
		 
		@Override
		public String call() throws Exception {
			System.out.println("update url >>>>>>>>>>>>>>>>: " + strUrl);
			return null;
		}
		 
		 
	 }
	 
	 
	 
}
