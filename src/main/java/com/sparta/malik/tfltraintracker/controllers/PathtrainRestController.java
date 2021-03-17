package com.sparta.malik.tfltraintracker.controllers;

import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.services.PathtrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathtrainRestController {

    private final PathtrainService pathtrainService;

    @Autowired
    public PathtrainRestController(PathtrainService pathtrainService) {
        this.pathtrainService = pathtrainService;
    }

    @GetMapping("/paths")
    public Iterable<PathtrainEntity> findAll() {
        return pathtrainService.getAllPaths();
    }

    @PostMapping("/paths")
    public PathtrainEntity newPath(@RequestBody PathtrainEntity pathEntity) {
        pathtrainService.savePath(pathEntity);
        return pathEntity;
    }

    public int getHighestPathId() {
        return pathtrainService.getHighestPathId();
    }
}
