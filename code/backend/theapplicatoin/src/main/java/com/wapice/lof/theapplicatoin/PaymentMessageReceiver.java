package com.wapice.lof.theapplicatoin;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessageReceiver {

	@JmsListener(destination = MainConfiguration.PAYMENT_MESSAGE_QUEUE_NAME, containerFactory = MainConfiguration.JMS_LISTENER_FACTORY)
	public void consumePaymentMessage(TextMessage paymentRequest) {
		System.out.println("Payment message details: " + paymentRequest);
		try {
			System.out.println("Payment request payload: " + paymentRequest.getText());
		} catch (JMSException e) {
			throw new RuntimeException();
		}
	}
}
