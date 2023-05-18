package com.works.configs;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IGateway {

    @Gateway
    String sendMessage(String payload);

}
