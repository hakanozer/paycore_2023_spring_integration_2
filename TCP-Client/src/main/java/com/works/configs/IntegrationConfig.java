package com.works.configs;

import com.works.props.TcpClientProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;

@EnableIntegration
@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final TcpClientProps props;

    @Bean
    public IntegrationFlow integrationFlow() {
        return f -> f.handle(Tcp.outboundGateway(Tcp.nioClient(props.getHost(),props.getPort())
                .serializer(TcpCodecs.crlf())
                .get()))
                .transform(Transformers.objectToString());
    }

}
