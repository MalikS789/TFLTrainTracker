package com.sparta.malik.tfltraintracker.repositories;

import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainsRepository extends CrudRepository<TrainsEntity, Integer> {
    // Method?!
}
