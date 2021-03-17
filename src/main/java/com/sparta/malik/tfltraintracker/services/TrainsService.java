package com.sparta.malik.tfltraintracker.services;

import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import com.sparta.malik.tfltraintracker.repositories.TrainsRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainsService {
    private final TrainsRepository trainsRepository;

    public TrainsService(TrainsRepository trainsRepository) {
        this.trainsRepository = trainsRepository;
    }

    public Iterable<TrainsEntity> getAllTrains() {
        return trainsRepository.findAll();
    }

    public void saveTrain(TrainsEntity trainsEntity) {
        trainsRepository.save(trainsEntity);
    }

}
