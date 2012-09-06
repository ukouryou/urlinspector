package com.huigou.inspector.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.huigou.inspector.vo.ProductVO;

public class Constants {
	
	public static class DataBase {
		public static final int CATEGORYIDSLICE = 6;
		public static final String ISDELETE_N = "N";
		public static final String ISDELETE_Y = "Y";
	}
	
	public static final int FULL_SIZE = 10000;
	
	public  static final BlockingQueue<ProductVO> PRODUCT_BLOCKING_QUEUE = new LinkedBlockingQueue<ProductVO>();
	public  static final BlockingQueue<ProductVO> INVALIDURL_BLOCKING_QUEUE = new LinkedBlockingQueue<ProductVO>();
	
}
