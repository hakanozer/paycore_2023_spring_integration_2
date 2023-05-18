package com.works.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("tcp.server")
@Data
public class TcpServerProps {
    private int port;
}
