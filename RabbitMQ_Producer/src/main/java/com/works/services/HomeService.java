package com.works.services;

import com.works.props.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.name}")
    private String qname;

    public void sender(Message message) {
        rabbitTemplate.convertAndSend(qname, message);
    }

}
