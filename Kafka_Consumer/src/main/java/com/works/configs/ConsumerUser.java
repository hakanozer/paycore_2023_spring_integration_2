package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
public class ConsumerUser {

    final ObjectMapper objectMapper;

    @KafkaListener(groupId = "group_1", topics = "paycoreTopic")
    public void userListener(String userData) {
        try {
            User user = objectMapper.readValue(userData, User.class);
            System.out.println( user );
            long end = System.currentTimeMillis();
            System.out.println("Consumer end : " + end);
        }catch (Exception ex) {
            System.err.println("userListener Error : " + ex);
        }
    }


}
