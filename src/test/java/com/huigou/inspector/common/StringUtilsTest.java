package com.huigou.inspector.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class StringUtilsTest extends BaseTest {

	@Test
	public void test() {
		// System.out.println("  " + StringUtils.addZeroPrefix("123"));
	}

	@Test
	public void dateTest() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String parsedDate = sdf.format(date);
		System.out.println(parsedDate);
	}
	
}
