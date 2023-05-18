package com.works.configs;

import com.works.props.TcpClientProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;
import org.springframework.integration.support.MessageBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@EnableIntegration
@IntegrationComponentScan
@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final TcpClientProps props;

    @Bean
    public IntegrationFlow client() {
        return f -> f.handle(Tcp.outboundGateway(Tcp.nioClient("localhost", props.getPort())
                .deserializer(TcpCodecs.lengthHeader1())
                .serializer(TcpCodecs.lengthHeader1())));
    }


    @Bean
    public IntegrationFlow mainFlow() {
        return IntegrationFlows
                .from(integerMessageSource(), c -> c.poller(Pollers.fixedRate(1000)))
                .publishSubscribeChannel(pubSub -> pubSub
                        .subscribe(flow -> flow
                                .bridge(e -> e.id("s1"))
                                .handle(message -> System.out.println("Handling message, step 1: {} " + message.getPayload())))
                        .subscribe(flow -> flow
                                .bridge(e -> e.id("s2"))
                                .handle(message -> System.out.println("Handling message, step 2: {} " + message.getPayload())))
                        .subscribe(flow -> flow
                                .transform(source -> MessageBuilder.withPayload("Error").build())
                                .handle(message -> {
                                    System.err.println( "Error ");
                                }))
                        .subscribe(flow -> flow
                                .bridge(e -> e.id("s4"))
                                .handle(message -> System.out.println("Handling message, step 4: {} " + message.getPayload())))
                )
                .get();
        // @formatter:on
    }


    @Bean
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new AtomicInteger());
        source.setMethodName("getAndIncrement");
        return source;
    }


}
