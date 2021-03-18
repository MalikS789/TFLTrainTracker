package com.sparta.malik.tfltraintracker.controllers;

import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import com.sparta.malik.tfltraintracker.pojo.History;
import com.sparta.malik.tfltraintracker.pojo.TrainPOJO;
import com.sparta.malik.tfltraintracker.services.PathtrainService;
import com.sparta.malik.tfltraintracker.services.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TrainsRestController {

    private final TrainsService trainsService;
    private final PathtrainService pathtrainService;
    ObjectMapper objectMapper = new ObjectMapper();
    String[] trainlines = new String[]{"bakerloo", "central", "circle", "district", "hammersmith-city", "jubilee", "metropolitan", "northern", "piccadilly", "tram", "victoria"};
    List<TrainPOJO>[] listTrain = new List[trainlines.length];

    @Autowired
    public TrainsRestController(TrainsService trainsService, PathtrainService pathtrainService) {
        this.trainsService = trainsService;
        this.pathtrainService = pathtrainService;
    }

    @GetMapping("/")
    public String onLoad() {
//        try {
//            int highestID = pathtrainRestController.getHighestPathId();
//            for (int i = 0; i < trainlines.length; i++) {
//                listTrain[i] = objectMapper.readValue(new URL("https://api.tfl.gov.uk/line/" + trainlines[i] + "/arrivals"), new TypeReference<List<TrainPOJO>>() {
//                });
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

        String generatedWebpage = "The database is up to date! <br/>\n" +
                "put <a href=\"./trains\">/trains</a> to see a JSON of all trains <br/>\n" +
                "put <a href=\"./paths\">/paths</a> to see a json of all activity of all trains <br/>\n" +
                "put e.g. <a href=\"./bakerloo/204\">/bakerloo/204</a> to see a map of the route the train with the id: 204 line took! <br/>\n" +
                "\n" +
                "<SELECT id=\"s1\" NAME=\"section\" onChange=\"SelectRedirect();\">\n";

        Iterable<TrainsEntity> trains = findAll();

        for (TrainsEntity train : trains) {
            String id = String.valueOf(train.getTrainId());
            String idconcat = id.substring(2);
            String lineid = train.getLineId();
            generatedWebpage += "<Option value=\"" + lineid + "/" + idconcat + "\">" + "train " + idconcat + " on " + lineid + "</option>";
        }

        generatedWebpage += "</SELECT>\n" +
                "<script language=\"javascript\">\n" +
                "    function SelectRedirect(){\n" +
                "        window.location=\"../\" + document.getElementById('s1').value;\n" +
                "    }\n" +
                "</script>";

        return generatedWebpage;
    }

    @GetMapping("/{name}/{id}")
    public String getTrainInfo(@PathVariable String name, @PathVariable String id) {
        String finalID;
        switch (name) {
            case "central":
                finalID = "10";
                break;
            case "circle":
                finalID = "20";
                break;
            case "district":
                finalID = "30";
                break;
            case "hammersmith-city":
                finalID = "40";
                break;
            case "jubilee":
                finalID = "50";
                break;
            case "metropolitan":
                finalID = "60";
                break;
            case "northern":
                finalID = "70";
                break;
            case "piccadilly":
                finalID = "80";
                break;
            case "tram":
                finalID = "90";
                break;
            case "victoria":
                finalID = "100";
                break;
            default:
                finalID = "00";
        }
        String url;
        if (!finalID.equals("00")) {
            int num = parseInt(id);
            if (num < 10) {
                url = finalID + "00" + id;
            } else if (num < 100) {
                url = finalID + "0" + id;
            } else {
                url = finalID + id;
            }
        } else {
            url = id;
        }

        String result = "";

        List<History> list = pathtrainService.getPathFromTrain(url);

        ArrayList<String> seen = new ArrayList<>();

        for (History history : list) {
            if (!seen.contains(history.getPlatform())) {
                seen.add(history.getPlatform());
            }
        }

        result += "<table><tr>";

        for (String platform : seen) {
            result += "<th>" + platform + "</th>";
        }
        result += "</tr>";

        Timestamp previous = null;

        for (History history : list) {
            if (previous == null || previous.before(history.getTimestamp())) {
                result += "<tr><td>" + history.getTimestamp() + "</td>";
                for (String platform : seen) {
                    if (history.getPlatform().equals(platform)) {
                        result += "<td>" + history.getCurrentLocation() + "</td>";
                    } else {
                        result += "<td></td>";
                    }
                }
                result += "</tr>";
            }
            previous = history.getTimestamp();
        }
        result += "</table>";

        return result;
    }

    @GetMapping("/error")
    public RedirectView error() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("error.html");
        return redirectView;
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
