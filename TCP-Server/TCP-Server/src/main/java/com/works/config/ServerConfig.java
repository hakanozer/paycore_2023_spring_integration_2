package com.works.config;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

    private final int port = 5000;

    @Bean
    TcpNetServerConnectionFactory serverConnexion() {
        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(port);
        factory.setSingleUse(true);
        factory.setSoTimeout(10000);
        return factory;
    }

    @Bean
    TcpInboundGateway tcpGateway(TcpNetServerConnectionFactory serverConnexion, MessageChannel reqChannel) {
        TcpInboundGateway gateway = new TcpInboundGateway();
        gateway.setConnectionFactory(serverConnexion);
        gateway.setRequestChannel(reqChannel);
        gateway.setReplyChannel(reqChannel);
        return gateway;
    }

    @Bean
    public MessageChannel reqChannel() {
        return new DirectChannel();
    }

}
