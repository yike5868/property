package com.zl.property.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
