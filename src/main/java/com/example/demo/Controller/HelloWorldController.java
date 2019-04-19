package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 */
@RestController
public class HelloWorldController {


    /**
     *
     */

    private static final String HELLO = "hello ";

    @GetMapping("hello/{user}")
    public String say(@RequestParam(name = "user") String user) {
        return HELLO + user ;
    }

    
}