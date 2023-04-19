package com.works.restcontrollers;

import com.works.props.User;
import com.works.services.UserProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProducerRestController {

    final UserProducerService userProducerService;

    @PostMapping("/send/{time}")
    public void send(@RequestBody User user, @PathVariable long time) {
        userProducerService.sendData(user, time);
    }

}
