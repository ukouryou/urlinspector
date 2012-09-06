package com.huigou.inspector.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Inspector {

	private static ApplicationContext applicationContext;
	
	static {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InspectorService inspectorService = (InspectorService) Inspector.getBean("inspectorService");
		inspectorService.inspect();
	}

}
