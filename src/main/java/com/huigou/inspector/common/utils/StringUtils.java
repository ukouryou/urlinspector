package com.huigou.inspector.common.utils;


public class StringUtils {

	
	public static String addZeroPrefix(String parentId) {
		
		if (null == parentId) {
			return "";
		}
		
		String parentValue = String.valueOf(parentId) + "";
		int currentLength = parentValue.length();
			
		return parentValue;
	}
}
