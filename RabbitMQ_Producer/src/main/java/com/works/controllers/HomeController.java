package com.works.controllers;

import com.works.props.Message;
import com.works.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    final HomeService service;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/send")
    public String send(Message message) {
        System.out.println(message);
        service.sender(message);
        return "home";
    }

}
