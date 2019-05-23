package com.wapice.lof.theapplicatoin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@RestController
@EnableWebMvc
public class TheapplicationApplication implements InitializingBean, GreetingController {

	@Value("${someproperty}")
	private String value;

	@Value("${onlydefaultproperty}")
	private String onlydefaultproperty;
	
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
	
    @Value("${spring.application.name}")
    private String appName;

	public static void main(String[] args) {
		SpringApplication.run(TheapplicationApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(value);
		System.out.println(onlydefaultproperty);
	}

    @Override
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
}
