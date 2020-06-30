package com.cloud.jarbase.util;

import org.springframework.context.ApplicationContext;

/**
 * @author weseal
 * @description
 * @create 2019/1/14
 */
public class SpringApplicationUtils {
	
	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringApplicationUtils.applicationContext = applicationContext;
	}
	
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static <T> T getBean(Class<T> t) {
		return applicationContext.getBean(t);
	}
	
	public static <T> T getBean(String beanName, Class<T> t) {
		return applicationContext.getBean(beanName, t);
	}
	
	
}
