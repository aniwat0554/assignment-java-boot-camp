package com.example.demo.whiskies;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhiskiesController {

    @GetMapping("/whiskies/{name}")
    public WhiskiesResponse getWhisky(@PathVariable String name){
        return new WhiskiesResponse(name);
    }
}
