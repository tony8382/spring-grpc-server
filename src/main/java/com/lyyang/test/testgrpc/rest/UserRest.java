package com.lyyang.test.testgrpc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRest {

    @GetMapping("name/{name}")
    public int id(@PathVariable("name") String name) {
        return 10;
    }

    @GetMapping("currentTime")
    public Long getCurrentTimeMillis() {
        return 123L;
    }

}
