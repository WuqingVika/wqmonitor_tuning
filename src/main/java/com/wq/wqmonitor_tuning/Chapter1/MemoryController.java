package com.wq.wqmonitor_tuning.Chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryController {
	
	private List<User>  userList = new ArrayList<User>();
	private List<Class<?>>  classList = new ArrayList<Class<?>>();

	/**最大内存
	 * -Xmx32M -Xms32M
	 * */
	@GetMapping("/heap")//堆内存溢出
	public String heap() {
		int i=0;
		while(true) {
			//撑爆堆内存嘎嘎
			userList.add(new User(i++, UUID.randomUUID().toString()));
		}
		/*Exception in thread "http-nio-8080-exec-1" java.lang.OutOfMemoryError: GC overhead limit exceeded
		Exception in thread "http-nio-8080-AsyncTimeout" java.lang.OutOfMemoryError: GC overhead limit exceeded*/
	}

	/**
	 * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
	 * */
	@GetMapping("/nonheap")
	public String nonheap() {//非堆内存溢出
		while(true) {
			classList.addAll(Metaspace.createClasses());
			//java.lang.OutOfMemoryError: Metaspace
		}
	}

}
