package com.zcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhuangcy
 * @date 2021/3/10
 * @description 启动入口
 */
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ActivityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityDemoApplication.class, args);
    }

}
