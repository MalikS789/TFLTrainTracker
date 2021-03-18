package com.sparta.malik.tfltraintracker.services;

import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.pojo.History;
import com.sparta.malik.tfltraintracker.repositories.PathtrainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public int getHighestPathId() {
        Iterable<PathtrainEntity> list = pathtrainRepository.findAll();
        int highest = -1;
        for (PathtrainEntity pathtrainEntity : list) {
            if (pathtrainEntity.getPathId() > highest) {
                highest = pathtrainEntity.getPathId();
            }
        }
        return highest;
    }

    public List<History> getPathFromTrain(String id) {
        int idd = Integer.parseInt(id);
        Iterable<PathtrainEntity> pathList = pathtrainRepository.findAll();
        ArrayList<History> histories = new ArrayList<History>();
        for (PathtrainEntity pathtrainEntity : pathList) {
            if (pathtrainEntity.getTrainId() == idd) {
                History path = new History(pathtrainEntity.getPlatformName(), pathtrainEntity.getCurrentLocation(), pathtrainEntity.getTimestamp());
                histories.add(path);
            }
        }
        return histories;
    }

    public void newPath(PathtrainEntity pathtrainEntity) {

    }
}