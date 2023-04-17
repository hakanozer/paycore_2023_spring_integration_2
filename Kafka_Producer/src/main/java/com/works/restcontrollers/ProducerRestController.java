package com.works.restcontrollers;

import com.works.props.User;
import com.works.services.UserProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerRestController {

    final UserProducerService userProducerService;

    @PostMapping("/send")
    public void send( @RequestBody User user ) {
        userProducerService.sendData(user);
    }

}
