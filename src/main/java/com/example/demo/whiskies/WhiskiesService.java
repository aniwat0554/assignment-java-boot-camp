package com.example.demo.whiskies;

import com.example.demo.pricing.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WhiskiesService {

    @Autowired
    private WhiskyRepository whiskyRepository;



    public List<Whisky> searchWhiskies(String name, int itemCount){

        List<Whisky> whiskyList = whiskyRepository.findByNameContaining(name);





        return whiskyList;
    }
}
