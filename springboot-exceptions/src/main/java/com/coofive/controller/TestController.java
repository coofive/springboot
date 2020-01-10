package com.coofive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coofive
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "it's ok";
    }
}
