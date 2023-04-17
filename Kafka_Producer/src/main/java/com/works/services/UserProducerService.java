package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProducerService {

    final KafkaTemplate<String, String> kafkaTemplate;
    final ObjectMapper objectMapper;

    public void sendData( User user ) {
        String data = "";
        for (int i = 0; i < 10000; i++) {
            user.setName(UUID.randomUUID().toString());
            try {
                data = objectMapper.writeValueAsString(user);
            }catch (Exception ex) {}
                kafkaTemplate.send("paycoreTopic",data).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        System.err.println("Send Error : " + ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        System.out.println("onSuccess : " + result.toString());
                        long end = System.currentTimeMillis();
                        System.out.println("end : " + end);
                    }
                });
            }

    }

}
