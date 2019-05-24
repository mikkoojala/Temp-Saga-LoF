package com.wapice.lof.theapplicatoin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@RestController
@EnableWebMvc
public class TheapplicationApplication implements InitializingBean, GreetingController {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(TheapplicationApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public String greeting() {

		JmsTemplate paymentMessageProducerTemplate = context.getBean(JmsTemplate.class);

		sendPaymentMessage(paymentMessageProducerTemplate);

		return String.format("Hello from '%s' in port %s!", eurekaClient.getApplication(appName).getName(),
				System.getProperties().get("server.port"));
	}

	private void sendPaymentMessage(JmsTemplate paymentMessageProducerTemplate) {
		paymentMessageProducerTemplate.convertAndSend(MainConfiguration.PAYMENT_MESSAGE_QUEUE_NAME, "the paymnet");
	}
}
