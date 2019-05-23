package com.wapice.lof.theapplicatoin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@RestController
@EnableWebMvc
public class TheapplicationApplication implements InitializingBean, WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, GreetingController {
	
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
	
    @Value("${spring.application.name}")
    private String appName;

    @Value("${port.num.min}")
    private int minPort;
    
    @Value("${port.num.max}")
    private int maxPort;
    
	public static void main(String[] args) {
		SpringApplication.run(TheapplicationApplication.class, args);
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
	    int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
	    factory.setPort(port);
	    System.getProperties().put("server.port", port);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
	}

    @Override
    public String greeting() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
}
