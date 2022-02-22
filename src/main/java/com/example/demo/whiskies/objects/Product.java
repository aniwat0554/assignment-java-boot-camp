package com.example.demo.whiskies.objects;

import com.example.demo.pricing.Price;

public interface Product {
    void setPrice(Price price);
    Price getPrice();

    void setReview(String review);

    String getReview();

    void setTasteProfile(String tasteProfile);

    String getTasteProfile();

    void setName(String name);

    String getName();

    void setDegree(int degree);

    int getDegree();

    void setDescription(String description);

    String getDescription();

    int getId();

    void setId(int id);
}
