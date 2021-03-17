package com.sparta.malik.tfltraintracker.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import com.sparta.malik.tfltraintracker.pojo.TrainPOJO;
import com.sparta.malik.tfltraintracker.services.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class TrainsRestController {

    private final TrainsService trainsService;

    private final PathtrainRestController pathtrainRestController;

    ObjectMapper objectMapper = new ObjectMapper();

    String[] trainlines = new String[]{
            "bakerloo", "central", "circle", "district",
//            "dlr", "emirates-air-line",
            "hammersmith-city", "jubilee",
//            "london-overground",
            "metropolitan", "northern", "piccadilly",
//            "tfl-rail",
            "tram", "victoria",
//            "waterloo-city"
    };

    List<TrainPOJO>[] listTrain = new List[trainlines.length];

    @Autowired
    public TrainsRestController(TrainsService trainsService, PathtrainRestController pathtrainRestController) {
        this.trainsService = trainsService;
        this.pathtrainRestController = pathtrainRestController;
    }

//    @GetMapping("/")
//    public RedirectView onLoad() {
//        try {
//            int highestID = pathtrainRestController.getHighestPathId();
//            for (int i = 0; i < trainlines.length; i++) {
//                listTrain[i] = objectMapper.readValue(new URL("https://api.tfl.gov.uk/line/" + trainlines[i] + "/arrivals"), new TypeReference<List<TrainPOJO>>(){});
//                if (listTrain[i] != null) {
//                    for (TrainPOJO trainPOJO : listTrain[i]) {
//                        TrainsEntity trainsEntity = new TrainsEntity();
//                        try {
//                            trainsEntity.setTrainId(parseInt(i + "0" + trainPOJO.getVehicleId()));
//                            highestID++;
//                        } catch (Exception e) {
//                            continue;
//                        }
//                        trainsEntity.setLineId(trainPOJO.getLineId());
//                        newTrain(trainsEntity);
//                        PathtrainEntity pathtrainEntity = new PathtrainEntity();
//                        pathtrainEntity.setPathId(highestID + 1);
//                        pathtrainEntity.setTrainId(trainsEntity.getTrainId());
//                        pathtrainEntity.setPlatformName(trainPOJO.getPlatformName());
//                        pathtrainEntity.setCurrentLocation(trainPOJO.getCurrentLocation());
//                        pathtrainEntity.setDirection(trainPOJO.getDirection());
//                        pathtrainEntity.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//                        pathtrainRestController.newPath(pathtrainEntity);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/index.html");
//        return redirectView;
//    }

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
