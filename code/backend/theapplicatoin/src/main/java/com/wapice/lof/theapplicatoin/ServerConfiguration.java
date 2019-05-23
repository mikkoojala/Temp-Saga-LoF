package com.wapice.lof.theapplicatoin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;

@Configuration
public class ServerConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${port.num.min}")
	private int minPort;

	@Value("${port.num.max}")
	private int maxPort;

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
		factory.setPort(port);
		System.getProperties().put("server.port", port);
	}
}
