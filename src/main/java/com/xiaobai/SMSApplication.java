package com.xiaobai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;//使用插件读取mapper类, 通用mapper配置

@SpringBootApplication
//这个表示mybatis自动扫描dao接口的包名，com.xiaobai.sys.mapper
@MapperScan("com.xiaobai.*.mapper")
public class SMSApplication{

	public static void main(String[] args) {
		SpringApplication.run(SMSApplication.class, args);
	}
}
