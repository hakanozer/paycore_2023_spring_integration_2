package com.works.listeners;

import com.works.props.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerListener {

    @RabbitListener(queues = "paycore-queue")
    public void messageListener(Message message) {
        System.out.println( message );
    }


}
