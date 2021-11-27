package org.pcloud.propertiesinjection.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "${domain}")
@RestController
public class ExampleApi {
    @Value("${message}")
    private String message;

    @GetMapping
    public String getMessage() {
        return message;
    }
}
