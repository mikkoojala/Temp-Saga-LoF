package com.wapice.lof.msgapp1;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TempReceiver {

	@JmsListener(destination = "temp", containerFactory = "myFactory")
	public void receiveMessage(ActiveMQTextMessage email) {
		System.out.println("Received <" + email + ">");

		try {
			if(email.getText().contains("do not ack")) {
				email.onMessageRolledBack();
				throw new RuntimeException();
			} else {
				email.acknowledge();
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
