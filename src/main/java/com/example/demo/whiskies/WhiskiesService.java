package com.example.demo.whiskies;

import com.example.demo.Exception.WhiskyNotFoundException;
import com.example.demo.whiskies.objects.Whisky;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WhiskiesService {

    @Autowired
    private WhiskyRepository whiskyRepository;



    public List<Whisky> searchWhiskies(String name, int itemCount){

        List<Whisky> whiskyList = whiskyRepository.findByNameContaining(name);





        return whiskyList;
    }

    public Whisky getWhisky(String name){
        Optional<Whisky> whisky = whiskyRepository.findByName(name);
        if(whisky.isPresent()){
            return whisky.get();
        }
        throw new WhiskyNotFoundException(name);
    }
}
