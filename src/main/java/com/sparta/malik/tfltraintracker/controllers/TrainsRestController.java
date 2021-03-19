package com.sparta.malik.tfltraintracker.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sparta.malik.tfltraintracker.entities.PathtrainEntity;
import com.sparta.malik.tfltraintracker.entities.TrainsEntity;
import com.sparta.malik.tfltraintracker.pojo.History;
import com.sparta.malik.tfltraintracker.pojo.TrainPOJO;
import com.sparta.malik.tfltraintracker.services.PathtrainService;
import com.sparta.malik.tfltraintracker.services.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        String generatedWebpage = "<style>img {\n" +
                "    border: 1px solid #ddd;\n" +
                "    border-radius: 4px;\n" +
                "    padding: 5px;\n" +
                "    /*width: 150px;*/\n" +
                "}\n" +
                "\n" +
                "#managePosts table {\n" +
                "    width: 100%; \n" +
                "    border-collapse: collapse; \n" +
                "}\n" +
                "\n" +
                "#managePosts tr:nth-of-type(odd) { \n" +
                "    background: rgba(0,0,0,0.05); \n" +
                "}\n" +
                "\n" +
                "#managePosts th { \n" +
                "    padding: 8px;\n" +
                "    text-align: left;\n" +
                "    /*color: darkslategray;*/\n" +
                "    background: #333; \n" +
                "    font-weight: bold; \n" +
                "}\n" +
                "\n" +
                "#managePosts td, th { \n" +
                "    padding: 6px;\n" +
                "    /*border: 1px solid #606eb2;*/ \n" +
                "    text-align: left; \n" +
                "}\n" +
                "\n" +
                ".post {\n" +
                "    text-align: center;\n" +
                "    background: rgba(0,0,0,0.05); \n" +
                "}\n" +
                "\n" +
                "td, th { \n" +
                "    padding: 6px;\n" +
                "    /*border: 1px solid #606eb2;*/ \n" +
                "    text-align: left; \n" +
                "}\n" +
                "\n" +
                "div {\n" +
                "    /*overflow:auto;*/\n" +
                "    margin:10px;\n" +
                "}\n" +
                "\n" +
                ".leftNav {\n" +
                "    float: left;\n" +
                "    position: relative;\n" +
                "    z-index: 2;\n" +
                "    width: 200px\n" +
                "}\n" +
                "\n" +
                ".searchResults {\n" +
                "    float: right;\n" +
                "    position: relative;\n" +
                "}\n" +
                "\n" +
                "label {\n" +
                "    display:block;\n" +
                "    font-weight: bold;\n" +
                "    color: #060360\n" +
                "}\n" +
                "\n" +
                "input[type=submit] {\n" +
                "    padding:5px 15px; \n" +
                "    background:#606eb2; \n" +
                "    border:0 none;\n" +
                "    color: #fff;\n" +
                "    cursor:pointer;\n" +
                "    border-radius: 5px; \n" +
                "}\n" +
                "\n" +
                ".button {\n" +
                "    background-color: #606eb2;\n" +
                "    border: none;\n" +
                "    color: #fff;\n" +
                "    padding:5px 15px; \n" +
                "    text-align: center;\n" +
                "    text-decoration: none;\n" +
                "    display: inline-block;\n" +
                "    margin: 4px 2px;\n" +
                "    cursor: pointer;\n" +
                "}\n" +
                "\n" +
                "input{\n" +
                "    background-color: rgba(0,0,0,0.05);\n" +
                "    display:inline-block;\n" +
                "    padding:5px;\n" +
                "    border-width:0px;\n" +
                "}\n" +
                "\n" +
                "html {\n" +
                "    font-family: Helvetica, sans-serif;\n" +
                "}\n" +
                "\n" +
                ".CookiePolicyWarning {\n" +
                "    background: rgba(255,255,0,0.3);\n" +
                "    font-weight: bold;\n" +
                "}\n" +
                "\n" +
                ".theForm {\n" +
                "    float: right;\n" +
                "}\n" +
                "\n" +
                "body{\n" +
                "    background: linear-gradient(274deg, #ffffff, #a8aafb);\n" +
                "    background-size: 200% 200%;\n" +
                "    animation: AnimationName 14s ease infinite;\n" +
                "    /*font-family: 'Open Sans', sans-serif;*/\n" +
                "}\n" +
                "\n" +
                ".search {\n" +
                "    width: 100%;\n" +
                "    position: relative\n" +
                "}\n" +
                "\n" +
                ".red-star {\n" +
                "    color: red;\n" +
                "}\n" +
                "\n" +
                ".searchTerm {\n" +
                "    float: left;\n" +
                "    width: 100%;\n" +
                "    border: 3px solid #606eb2;\n" +
                "    padding: 5px;\n" +
                "    height: 30px;\n" +
                "    border-radius: 5px;\n" +
                "    outline: none;\n" +
                "    color: #000000;\n" +
                "}\n" +
                "\n" +
                ".searchButton {\n" +
                "    position: absolute;  \n" +
                "    right: -50px;\n" +
                "    width: 40px;\n" +
                "    height: 46px;\n" +
                "    border: 1px solid #606eb2;\n" +
                "    background: #606eb2;\n" +
                "    text-align: center;\n" +
                "    color: #fff;\n" +
                "    border-radius: 5px;\n" +
                "    cursor: pointer;\n" +
                "    font-size: 20px;\n" +
                "}\n" +
                "\n" +
                ".searchTerm:focus{\n" +
                "    color: #606eb2;\n" +
                "}\n" +
                "\n" +
                ".footer{ \n" +
                "    position: absolute;     \n" +
                "    text-align: center;    \n" +
                "    bottom: 0px; \n" +
                "    width: 100%;\n" +
                "}  \n" +
                "\n" +
                ".manageFooter{ \n" +
                "    /*position: fixed;*/     \n" +
                "    text-align: center;    \n" +
                "    bottom: 0px; \n" +
                "    width: 100%;\n" +
                "}  \n" +
                "\n" +
                "/*Resize the wrap to see the search bar change!*/\n" +
                ".wrap{\n" +
                "    width: 50%;\n" +
                "    position: absolute;\n" +
                "    top: 50%;\n" +
                "    left: 50%;\n" +
                "    transform: translate(-50%, -50%);\n" +
                "}\n" +
                "\n" +
                ".background {\n" +
                "    position: absolute;\n" +
                "    top: 0;\n" +
                "    left: 0;\n" +
                "    right: 0;\n" +
                "    bottom: 0;\n" +
                "    background: linear-gradient(274deg, #ffffff, #a8aafb);\n" +
                "    background-size: 200% 200%;\n" +
                "    animation: AnimationName 1s ease infinite;\n" +
                "}\n" +
                "\n" +
                "@keyframes AnimationName { \n" +
                "    0%{background-position:0% 53%}\n" +
                "    50%{background-position:100% 48%}\n" +
                "    100%{background-position:0% 53%}\n" +
                "}\n" +
                "\n" +
                "@media only screen and (max-width: 786px) {\n" +
                "\n" +
                "    .leftNav {\n" +
                "        z-index: 2;\n" +
                "        width: 100%;\n" +
                "        background-color: rgba(0,0,0,0.05);\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    .leftNav input[type=number]{\n" +
                "        width: 90%;\n" +
                "    }\n" +
                "\n" +
                "    .leftNav input[type=text]{\n" +
                "        width: 90%;\n" +
                "    }\n" +
                "\n" +
                "    .wrap {\n" +
                "        width: 60%;\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    table tr, td { \n" +
                "        width : 100%;\n" +
                "    }\n" +
                "\n" +
                "    #results tr, td { \n" +
                "        text-align: center;\n" +
                "        width : 100%;\n" +
                "    }\n" +
                "\n" +
                "    #LogInTable thead, tbody, th, td, tr { \n" +
                "        display: block; \n" +
                "        /*float: right;*/\n" +
                "        /*text-align: left;*/ \n" +
                "    }\n" +
                "\n" +
                "    /* Hide table headers (but not display: none;, for accessibility) */\n" +
                "    #LogInTable thead tr { \n" +
                "        position: absolute;\n" +
                "        top: -9999px;\n" +
                "        left: -9999px;\n" +
                "    }\n" +
                "\n" +
                "    #LogInTable tr { \n" +
                "        /*border: 1px transparent;*/\n" +
                "    }\n" +
                "\n" +
                "    .searchButtonmini {\n" +
                "        position: absolute;  \n" +
                "        top: -9999px;\n" +
                "        left: -9999px;\n" +
                "    }\n" +
                "\n" +
                "    #LogInTable td { \n" +
                "        /* Behave  like a \"row\" */\n" +
                "        /*border: 1px solid;*/\n" +
                "        width : 0%;\n" +
                "        border-bottom: 1px transparent; \n" +
                "        position: relative;\n" +
                "        padding-left: 50%; \n" +
                "    }\n" +
                "\n" +
                "    #LogInTable td:before { \n" +
                "        /* Now like a table header */\n" +
                "        position: absolute;\n" +
                "        /* Top/left values mimic padding */\n" +
                "        top: 6px;\n" +
                "        left: 6px;\n" +
                "        width: 45%; \n" +
                "        padding-right: 10px; \n" +
                "        white-space: nowrap;\n" +
                "    }\n" +
                "\n" +
                "    #LogInTable td:nth-of-type(1):before { content: \"Username\"; }\n" +
                "    #LogInTable td:nth-of-type(2):before { content: \"Password\"; }\n" +
                "\n" +
                "    #managePosts table, thead, tbody, th, td, tr { \n" +
                "        display: block; \n" +
                "        float: left;\n" +
                "    }\n" +
                "\n" +
                "    /* Hide table headers (but not display: none;, for accessibility) */\n" +
                "    #managePosts thead tr { \n" +
                "        position: absolute;\n" +
                "        top: -9999px;\n" +
                "        left: -9999px;\n" +
                "    }\n" +
                "\n" +
                "    #managePosts tr { \n" +
                "        border: 1px transparent;\n" +
                "    }\n" +
                "\n" +
                "    #managePosts td { \n" +
                "        /* Behave  like a \"row\" */\n" +
                "        /*border: 1px solid;*/\n" +
                "        width : 49%;\n" +
                "        border-bottom: 1px transparent; \n" +
                "        position: relative;\n" +
                "        padding-left: 50%; \n" +
                "    }\n" +
                "\n" +
                "    #managePosts td:before { \n" +
                "        /* Now like a table header */\n" +
                "        position: absolute;\n" +
                "        /* Top/left values mimic padding */\n" +
                "        top: 6px;\n" +
                "        left: 6px;\n" +
                "        width: 45%; \n" +
                "        padding-right: 10px; \n" +
                "        white-space: nowrap;\n" +
                "    }\n" +
                "\n" +
                "    /*\n" +
                "    Label the data\n" +
                "    */\n" +
                "    #managePosts td:nth-of-type(1):before { content: \"Status\"; }\n" +
                "    #managePosts td:nth-of-type(2):before { content: \"Service Type\"; }\n" +
                "    #managePosts td:nth-of-type(3):before { content: \"Skills Required\"; }\n" +
                "    #managePosts td:nth-of-type(4):before { content: \"Location\"; }\n" +
                "    #managePosts td:nth-of-type(5):before { content: \"Reward on completion\"; }\n" +
                "    #managePosts td:nth-of-type(6):before { content: \"Image(s)\"; }\n" +
                "    #managePosts td:nth-of-type(7):before { content: \"Post Options\"; }\n" +
                "}\n" +
                "\n" +
                ".searchTermmini {\n" +
                "    width: 96%;\n" +
                "    border: 3px solid #606eb2;\n" +
                "    padding: 5px;\n" +
                "    height: 30px;\n" +
                "    border-radius: 5px;\n" +
                "    outline: none;\n" +
                "    color: #000000;\n" +
                "}\n" +
                "\n" +
                ".searchButtonmini {\n" +
                "    position: absolute;  \n" +
                "    right: 20px;\n" +
                "    width: 40px;\n" +
                "    height: 46px;\n" +
                "    border: 1px solid #606eb2;\n" +
                "    background: #606eb2;\n" +
                "    text-align: center;\n" +
                "    color: #fff;\n" +
                "    border-radius: 5px;\n" +
                "    cursor: pointer;\n" +
                "    font-size: 20px;\n" +
                "}\n" +
                "\n" +
                ".menu-con-logo {\n" +
                "    float: top;\n" +
                "    /*width: 50px;*/\n" +
                "    /*height: 60px*/\n" +
                "}\n" +
                "\n" +
                ".menu-ico-logo {\n" +
                "    width: 45px;\n" +
                "    height: 45px;\n" +
                "    border-bottom: 2px solid transparent;\n" +
                "    background: url(../favicon.png) no-repeat center;\n" +
                "    background-size: 32px\n" +
                "}\n" +
                "\n" +
                ".menu-ico-logo:hover {\n" +
                "    height: 45px;\n" +
                "    border-bottom: 2px solid #606eb2\n" +
                "}</style><input type=\"button\" value=\"UPDATE DATABASE\" onClick=\"myFunction()\"/>\n" +
                "     <script>\n" +
                "       function myFunction() {\n" +
                "         window.location.href=\"./update\";\n" +
                "       }\n" +
                "     </script> <br/>\n" +
                "put <a href=\"./trains\">/trains</a> to see a JSON of all trains <br/>\n" +
                "put <a href=\"./paths\">/paths</a> to see a json of all activity of all trains <br/>\n" +
                "put e.g. <a href=\"./bakerloo/204\">/bakerloo/204</a> to see a map of the route the train with the id: 204 line took! <br/>\n" +
                "\n" +
                "<SELECT id=\"s1\" NAME=\"section\" onChange=\"SelectRedirect();\">\n";

        Iterable<TrainsEntity> trains = findAll();

        for (TrainsEntity train : trains) {
            String id = String.valueOf(train.getTrainId());
            int idconcat;
            if (parseInt(id) < 10000) {
                idconcat = parseInt(id);
            } else {
                idconcat = parseInt(id.substring(2));
            }
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

    @GetMapping("/update")
    public void update() {
        try {
            int highestID = pathtrainService.getHighestPathId();
            for (int i = 0; i < trainlines.length; i++) {
                listTrain[i] = objectMapper.readValue(new URL("https://api.tfl.gov.uk/line/" + trainlines[i] + "/arrivals"), new TypeReference<List<TrainPOJO>>() {
                });
                if (listTrain[i] != null) {
                    for (TrainPOJO trainPOJO : listTrain[i]) {
                        TrainsEntity trainsEntity = new TrainsEntity();
                        try {
                            trainsEntity.setTrainId(parseInt(i + "0" + trainPOJO.getVehicleId()));
                            highestID++;
                        } catch (Exception e) {
                            continue;
                        }
                        trainsEntity.setLineId(trainPOJO.getLineId());
                        newTrain(trainsEntity);
                        PathtrainEntity pathtrainEntity = new PathtrainEntity();
                        pathtrainEntity.setPathId(highestID + 1);
                        pathtrainEntity.setTrainId(trainsEntity.getTrainId());
                        pathtrainEntity.setPlatformName(trainPOJO.getPlatformName());
                        pathtrainEntity.setCurrentLocation(trainPOJO.getCurrentLocation());
                        pathtrainEntity.setDirection(trainPOJO.getDirection());
                        pathtrainEntity.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                        pathtrainService.savePath(pathtrainEntity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        onLoad();
    }

    @GetMapping("/{name}/{id}")
    public String getTrainInfo(@PathVariable String name, @PathVariable String id) {
        String finalID;
        String hexColour;
        switch (name) {
            case "central":
                finalID = "10";
                hexColour = "#CC3333";
                break;
            case "circle":
                finalID = "20";
                hexColour = "#ffea00";
                break;
            case "district":
                finalID = "30";
                hexColour = " #00cc66";
                break;
            case "hammersmith-city":
                finalID = "40";
                hexColour = "#CC9999";
                break;
            case "jubilee":
                finalID = "50";
                hexColour = "#868F98";
                break;
            case "metropolitan":
                finalID = "60";
                hexColour = "#ff99ff";
                break;
            case "northern":
                finalID = "70";
                hexColour = "#FFFFFF";
                break;
            case "piccadilly":
                finalID = "80";
                hexColour = "#9999ff";
                break;
            case "tram":
                finalID = "90";
                hexColour = "#00BD19";
                break;
            case "victoria":
                finalID = "100";
                hexColour = "#0099CC";
                break;
            default:
                finalID = "00";
                hexColour = "#996633";
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

        result += "<style>\n" +
                "body {\n" +
                "  background-color: #e7e8e9;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "  color: #000000;\n" +
                "}\n" +
                "</style><table><tr><th></th>";

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
                        result += "<td style=\"background-color:" + hexColour + ";\">" + history.getCurrentLocation() + "</td>";
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
