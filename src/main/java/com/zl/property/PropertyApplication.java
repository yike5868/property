package com.zl.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
//		String sr = hidePhone("18655881180");
//		System.out.println(sr);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize("100MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}

	public static String hidePhone(String args) {

		String phoneNumber = args.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		return phoneNumber;
	}
}
