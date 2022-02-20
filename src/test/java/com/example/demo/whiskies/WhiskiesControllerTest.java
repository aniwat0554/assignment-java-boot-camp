package com.example.demo.whiskies;

import com.example.demo.whiskies.objects.WhiskiesResponse;
import com.example.demo.whiskies.objects.Whisky;
import com.example.demo.whiskies.objects.WhiskyDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WhiskiesControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Searching : Search for Johny Walker and get 5 Johny Walker whiskies")
    void searchJohnyWalker() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(10,response.getTotal());
        assertEquals(5,response.getWhiskiesResponse().size());

    }

    @Test
    @DisplayName("Searching : Search for Johny Walker and check if red label is there")
    void checkRedLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(false,response.getWhiskiesResponse().stream().filter(whisky -> "RedLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
    }

    @Test
    @DisplayName("Searching : Search for Johny Walker and check if green label is there")
    void checkGreenLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(false,response.getWhiskiesResponse().stream().filter(whisky -> "GreenLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
    }

    @Test
    @DisplayName("Discounting : Search for Johny Walker and check if green label price is correct")
    void checkGreenLabelDiscount() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        Whisky greenLabel = response.getWhiskiesResponse().stream().filter(whisky -> "GreenLabel Johny Walker".equals(whisky.getName())).findAny().get();
        assertEquals(790,greenLabel.getPrice().getNetPrice());
    }

    @Test
    @DisplayName("Search for Johny Walker and check if blue label is there")
    void checkBlueLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(false,response.getWhiskiesResponse().stream().filter(whisky -> "BlueLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
    }

    @Test
    @DisplayName("Search for Johny Walker and check if black label is there")
    void checkBlackLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(false,response.getWhiskiesResponse().stream().filter(whisky -> "BlackLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
    }

    @Test
    @DisplayName("Search for Johny Walker and check if gold label is there")
    void checkGoldLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(false,response.getWhiskiesResponse().stream().filter(whisky -> "GoldLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
    }

    @Test
    @DisplayName("Get Red Label Johny Walker detail and check its content")
    void testGetWhiskyDetail() {
        WhiskyDetailResponse response = testRestTemplate.getForObject("/whiskies/RedLabel Johny Walker",WhiskyDetailResponse.class);
        assertEquals("RedLabel Johny Walker",response.getWhiskyDetail().getName());
    }
}