package com.works.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;

@Configuration
@RequiredArgsConstructor
public class ConsumerUser {

    final ObjectMapper objectMapper;

    @KafkaListener(groupId = "group_1", topics = { "paycoreTopic", "paycoreTopic1" })
    public void userListener(String userData, ConsumerRecordMetadata metadata) {
        try {
            User user = objectMapper.readValue(userData, User.class);
            System.out.println( user );
            System.out.println( metadata.partition() );
            System.out.println( metadata.timestamp() );
            System.out.println( metadata.topic() );
            System.out.println( metadata.offset() );
        }catch (Exception ex) {
            System.err.println("userListener Error : " + ex);
        }
    }

    @KafkaListener(groupId = "group_2", topics = { "paycoreTopic2", "paycoreTopic3" })
    public void userListener_1(String userData, ConsumerRecordMetadata metadata) {
        try {
            System.out.println(userData);
            System.out.println( metadata.partition() );
            System.out.println( metadata.timestamp() );
            System.out.println( metadata.topic() );
        }catch (Exception ex) {
            System.err.println("userListener Error : " + ex);
        }
    }


}
