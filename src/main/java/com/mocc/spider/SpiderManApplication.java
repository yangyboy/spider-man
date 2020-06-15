package com.mocc.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mocc.spider.mapper")
public class SpiderManApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderManApplication.class, args);
	}

}
