package com.example.adviceexample.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoApi {

    @GetMapping("hello")
    public void hello(){
        log.debug("hello Call");
    }

    @GetMapping("hello")
    public Message helloMessage(@RequestParam(name = "msg") String msg) {
        return new Message(msg);
    }

    @RequiredArgsConstructor
    @Getter
    private static class Message {
        private final String msg;
    }
}
