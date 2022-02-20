package com.example.demo.whiskies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky,Integer> {

    List<Whisky> findByNameContaining(String name);
}
