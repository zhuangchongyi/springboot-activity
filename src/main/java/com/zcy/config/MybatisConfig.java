package com.zcy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuangcy
 * @date 2021/3/12
 * @description Mybatis-Plus 配置类
 */
@Configuration
@MapperScan("com.zcy.mapper")
public class MybatisConfig {

}
