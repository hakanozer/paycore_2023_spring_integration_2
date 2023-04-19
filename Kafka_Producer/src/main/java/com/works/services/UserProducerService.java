package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProducerService {

    final KafkaTemplate<String, String> kafkaTemplate;
    final ObjectMapper objectMapper;

    public void sendData(User user, long timex) {
        String data = "";
            user.setName(UUID.randomUUID().toString());
            try {
                data = objectMapper.writeValueAsString(user);
            }catch (Exception ex) {}
            long time = System.currentTimeMillis();

        String finalData = data;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                kafkaTemplate.send("paycoreTopic1", 1, time, "part_key" , finalData).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        System.err.println("Send Error : " + ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        System.out.println("onSuccess : " + result.toString());
                        System.out.println(result.getProducerRecord().timestamp());
                    }

                });


            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, timex);

    }

}
