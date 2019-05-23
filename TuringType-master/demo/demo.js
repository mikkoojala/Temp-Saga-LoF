var corpus1 =
`


package com.wapice.lof.theapplicatoin;\n\
\n\
import javax.jms.JMSException;\n\
\n\
import org.apache.activemq.command.ActiveMQTextMessage;\n\
import org.springframework.jms.annotation.JmsListener;\n\
import org.springframework.stereotype.Component;\n\
\n\
@Component\n\
public class TempReceiver {\n\
\n\
	@JmsListener(destination = MainConfiguration.MESSAGE_QUEUE_NAME, containerFactory = MainConfiguration.JMS_LISTENER_FACTORY)\n\
	public void receiveMessage(ActiveMQTextMessage email) {\n\
		System.out.println("Received <" + email + ">");\n\
\n\
		try {\n\
			if (email.getText().contains("do not ack")) {\n\
				email.onMessageRolledBack();\n\
				throw new RuntimeException();\n\
			} else {\n\
				email.acknowledge();\n\
			}\n\
		} catch (JMSException e) {\n\
			// TODO Auto-generated catch block\n\
			e.printStackTrace();\n\
		}\n\
	}\n\
}\n\


`;

var corpus2 =
"Use me like this:\n\
\n\
new TuringType(domElement, 'Just some text.');\n\
\n\
Or pass some options if you feel like it:\n\
\n\
new TuringType(domElement, 'Terrible but fast typist.', { accuracy: 0.3, interval: 20, callback: allDone });\n\
\n\
Enjoy. Click the Github link in the upper right to see my code.";


var el = document.getElementById('test');
new TuringType(el, corpus1, { interval: 5, accuracy: .6, callback: null });
