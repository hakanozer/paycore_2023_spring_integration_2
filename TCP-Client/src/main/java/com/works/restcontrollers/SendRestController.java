package com.works.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendRestController {


    @GetMapping("/send/{data}")
    public String sendMessage(@PathVariable String data) {
        return "";
    }

}
