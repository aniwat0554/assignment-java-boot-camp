package com.example.demo.whiskies;


import com.example.demo.pricing.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WhiskiesController {

    @Autowired
    private WhiskiesService whiskiesService;

    @GetMapping("/whiskies")
    public WhiskiesResponse getWhisky(@RequestParam String name){

        List<Whisky> whiskyList = this.whiskiesService.searchWhiskies(name,5);
        WhiskiesResponse whiskiesResponse = new WhiskiesResponse();
        whiskiesResponse.setTotal(10);
        whiskiesResponse.setPage(1);



        whiskiesResponse.setWhiskiesResponse(whiskyList);

        return whiskiesResponse;
    }
}
