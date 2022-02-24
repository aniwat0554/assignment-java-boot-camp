package com.example.demo.whiskies;

import com.example.demo.whiskies.objects.Whisky;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WhiskyRepositoryTest {

    @Autowired
    private WhiskyRepository whiskyRepository;
    @Test
    void findByNameContaining() {
        //Act
        List<Whisky> whiskyList = whiskyRepository.findByNameContaining("Johny Walker");
        //Assert
        assertEquals(5,whiskyList.size());
        //Act
        List<Whisky> whiskyRedList = whiskyRepository.findByNameContaining("RedLabel");
        //Assert
        assertEquals(1,whiskyRedList.size());
        assertEquals("RedLabel Johny Walker",whiskyRedList.get(0).getName());
    }

    @Test
    void findByName() {
        //Act
        Whisky red = whiskyRepository.findByName("RedLabel Johny Walker").get();
        //Assert
        assertEquals("RedLabel Johny Walker",red.getName());
    }
}