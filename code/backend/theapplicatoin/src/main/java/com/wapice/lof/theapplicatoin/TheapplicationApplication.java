package com.wapice.lof.theapplicatoin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheapplicationApplication implements InitializingBean {

	@Value("${someproperty}")
	private String value;

	@Value("${onlydefaultproperty}")
	private String onlydefaultproperty;

	public static void main(String[] args) {
		SpringApplication.run(TheapplicationApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(value);
		System.out.println(onlydefaultproperty);
	}
}
