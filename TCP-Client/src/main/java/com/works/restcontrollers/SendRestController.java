package com.works.restcontrollers;

import com.works.configs.IGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendRestController {

    private final IGateway iGateway;

    @GetMapping("/send/{data}")
    public String sendMessage(@PathVariable String data) {
        return iGateway.sendMessage(data);
    }

}
