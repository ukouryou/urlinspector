package com.huigou.inspector.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huigou.inspector.threads.DBScanThread;
import com.huigou.inspector.threads.URLInspectorThread;

@Service
public class InspectorService {

	@Value(value="#{globalProperties['threadnumber.inspector']}")
	private int threadNumber;
	@Resource 
	private DBScanThread dbScanThread;
	
	private ExecutorService executorService = null;
	
	public void inspect() {
		System.out.println(threadNumber);
		
		executorService = Executors.newFixedThreadPool(threadNumber);
		
		this.executorService.execute(dbScanThread);
		for (int i = 0; i < threadNumber - 1; i++) {
			this.executorService.execute((URLInspectorThread)Inspector.getBean("URLInspectorThread"));
		}
		this.executorService.shutdown();
	}
	

}
