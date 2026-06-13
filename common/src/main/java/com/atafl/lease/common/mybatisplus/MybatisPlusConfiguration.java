package com.atafl.lease.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atafl.lease.web.*.mapper")
public class MybatisPlusConfiguration {

}