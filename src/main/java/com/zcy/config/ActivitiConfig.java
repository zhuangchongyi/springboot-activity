package com.zcy.config;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author zhuangcy
 * @date 2021/3/11
 * @description activiti配置类 
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    @Qualifier("salveDataSource")
    private DataSource salveDataSource;

//    @Primary
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager, SpringAsyncExecutor springAsyncExecutor) throws IOException {
        return baseSpringProcessEngineConfiguration(salveDataSource, transactionManager, springAsyncExecutor);
    }

}