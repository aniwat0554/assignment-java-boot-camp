package com.example.demo.whiskies;


import com.example.demo.pricing.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WhiskiesController {

    @GetMapping("/whiskies")
    public WhiskiesResponse getWhisky(@RequestParam String name){

        WhiskiesResponse whiskiesResponse = new WhiskiesResponse();
        whiskiesResponse.setTotal(10);
        whiskiesResponse.setPage(1);


        Price redPrice = new Price(600,"THB",0);
        Whisky redLabel = new Whisky("RedLabel",redPrice);
        redLabel.setDegree(40);
        redLabel.setDescription("Great blend");
        redLabel.setReview("www.google.com");
        redLabel.setTasteProfile("sweet");

        Price blackPrice = new Price(600,"THB",0);
        Whisky blackLabel = new Whisky("BlackLabel",blackPrice);
        blackLabel.setDegree(40);
        blackLabel.setDescription("Great blend");
        blackLabel.setReview("www.google.com");
        blackLabel.setTasteProfile("sweet");

        Price greenPrice = new Price(600,"THB",10);
        Whisky greenLabel = new Whisky("GreenLabel",greenPrice);
        greenLabel.setDegree(40);
        greenLabel.setDescription("Great blend");
        greenLabel.setReview("www.google.com");
        greenLabel.setTasteProfile("sweet");

        Price goldPrice = new Price(600,"THB",0);
        Whisky goldLabel = new Whisky("GoldLabel",goldPrice);
        goldLabel.setDegree(40);
        goldLabel.setDescription("Great blend");
        goldLabel.setReview("www.google.com");
        goldLabel.setTasteProfile("sweet");

        Price bluePrice = new Price(600,"THB",0);
        Whisky blueLabel = new Whisky("BlueLabel",redPrice);
        blueLabel.setDegree(40);
        blueLabel.setDescription("Great blend");
        blueLabel.setReview("www.google.com");
        blueLabel.setTasteProfile("sweet");


        ArrayList<Whisky> whiskyList = new ArrayList<Whisky>() ;
        whiskyList.add(redLabel);
        whiskyList.add(blackLabel);
        whiskyList.add(greenLabel);
        whiskyList.add(blueLabel);
        whiskyList.add(goldLabel);
        whiskiesResponse.setWhiskiesResponse(whiskyList);

        return whiskiesResponse;
    }
}
