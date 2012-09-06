package com.huigou.inspector.common;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations = { "classpath:/applicationContext.xml",
		"classpath:/servlet-context.xml", "classpath:/servlet-context.xml" })*/
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class BaseTest {

	@Test
	public void pathTest() {
		
		Set<String> set = new HashSet<String>();
		System.out.println(set.size());
		
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		
		System.out.println(BaseTest.class.getClassLoader().getResource("servlet-context.xml"));
	}
	
	
	
}
