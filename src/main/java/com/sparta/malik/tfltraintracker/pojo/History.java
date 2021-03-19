package com.sparta.malik.tfltraintracker.pojo;

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;

public class History implements Serializable {

    private String platform;
    private String currentLocation;
    private Timestamp timestamp;

    public History(String platform, String currentLocation, Timestamp timestamp) {
        this.platform = platform;
        this.currentLocation = currentLocation;
        this.timestamp = timestamp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
