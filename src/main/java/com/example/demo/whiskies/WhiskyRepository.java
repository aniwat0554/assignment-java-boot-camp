package com.example.demo.whiskies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WhiskyRepository extends JpaRepository<Whisky,Integer> {

    List<Whisky> findByNameContaining(String name);


    Optional<Whisky> findByName(String name);
}
