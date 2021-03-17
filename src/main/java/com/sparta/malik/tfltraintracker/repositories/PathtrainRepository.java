package com.sparta.malik.tfltraintracker.repositories;

import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathtrainRepository extends CrudRepository<PathtrainEntity, Integer> {
    // Method?!
}