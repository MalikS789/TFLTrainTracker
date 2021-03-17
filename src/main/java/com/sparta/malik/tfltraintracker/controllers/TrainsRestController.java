package com.sparta.malik.tfltraintracker.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import com.sparta.malik.tfltraintracker.pojo.TrainPOJO;
import com.sparta.malik.tfltraintracker.services.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class TrainsRestController {

    private final TrainsService trainsService;

    private final PathtrainRestController pathtrainRestController;

    ObjectMapper objectMapper = new ObjectMapper();
//    TrainPOJO trainPOJO;

    String[] trainlines = new String[]{
            "bakerloo", "central", "circle", "district", "dlr", "emirates-air-line", "hammersmith-city", "jubilee",
            "london-overground", "metropolitan", "northern", "piccadilly", "tfl-rail", "tram", "victoria",
            "waterloo-city"
    };

    List<TrainPOJO>[] listTrain = new List[trainlines.length];

    @Autowired
    public TrainsRestController(TrainsService trainsService, PathtrainRestController pathtrainRestController) {
        this.trainsService = trainsService;
        this.pathtrainRestController = pathtrainRestController;
    }

    @GetMapping("/")
    public void onLoad() {
        try {
            for (int i = 0; i < trainlines.length; i++) {
                listTrain[i] = objectMapper.readValue(new URL("http://api.tfl.gov.uk/line/" + trainlines[i] + "/arrivals"), new TypeReference<List<TrainPOJO>>(){});
                if (listTrain[i] != null) {
                    for (TrainPOJO trainPOJO : listTrain[i]) {
                        TrainsEntity trainsEntity = new TrainsEntity();
                        trainsEntity.setTrainId(parseInt(trainPOJO.getVehicleId()));
                        trainsEntity.setLineId(trainPOJO.getLineId());
                        PathtrainEntity pathtrainEntity = new PathtrainEntity();

//                        private int pathId;
//                        private Integer trainId;
//                        private String platformName;
//                        private String currentLocation;
//                        private String direction;
//                        private Timestamp timestamp;

                        pathtrainEntity.setTrainId(trainsEntity.getTrainId());
                        pathtrainEntity.setTrainId(trainsEntity.getTrainId());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/trains")
    public Iterable<TrainsEntity> findAll() {
        return trainsService.getAllTrains();
    }

    @PostMapping("/trains")
    public TrainsEntity newTrain(@RequestBody TrainsEntity trainsEntity) {
        trainsService.saveTrain(trainsEntity);
        return trainsEntity;
    }

}
