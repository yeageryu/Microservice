package com.cloud.jarbase.config;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setObjectWrapperFactory(new MapWrapperFactory());
            }
        };
    }

    /**
     * mybatis 自定义拦截器(补充createtime 和updaatetime)
     */
    @Bean
    public Interceptor getTimeInterceptor() {
        return new CreateUpdateTimeInterceptor();
    }

    /**
     * mybatis 自定义拦截器(打印带参数的sql)
     */
    @Bean
    public Interceptor getPrintSqlInterceptor() {
        return new PrintSqlInterceptor();
    }
}
