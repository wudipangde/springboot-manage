package com.pd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author able
 */
@SpringBootApplication
@MapperScan("com.pd.manage.dao")
public class SpringbootManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootManageApplication.class, args);
	}
}
