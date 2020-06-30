/**
 * 不用了，用的时候，只需要在mapper的方法上加注解：@TargetDataSource("test2")
 */

package com.cloud.jarbase.dynamicdb;

import com.example.multiDataSource.pojo.dto.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
@Order(1)
public class DataSourceAspect {
    //指定AOP切入点
    @Pointcut("execution(* com.example.multiDataSource.service..*.*(..))")
    public void service() {
    }

    @Before("service()")
    public void before(JoinPoint point) {
        try {
            String whodb = ThreadLocalHolder.getWhodb();

            DynamicDataSourceHolder.putDataSource(whodb);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("service()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSource();
    }
}
