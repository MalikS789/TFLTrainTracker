package com.sparta.malik.tfltraintracker.services;

import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.repositories.PathtrainRepository;
import org.springframework.stereotype.Service;

@Service
public class PathtrainService {

    private final PathtrainRepository pathtrainRepository;

    public PathtrainService(PathtrainRepository pathtrainRepository) {
        this.pathtrainRepository = pathtrainRepository;
    }

    public Iterable<PathtrainEntity> getAllPaths() {
        return pathtrainRepository.findAll();
    }

    public void savePath(PathtrainEntity trainsEntity) {
        pathtrainRepository.save(trainsEntity);
    }

}