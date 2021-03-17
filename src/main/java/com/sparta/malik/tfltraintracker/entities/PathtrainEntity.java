package com.sparta.malik.tfltraintracker.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "pathtrain", schema = "tfltracker", catalog = "")
public class PathtrainEntity {
//    private int id;
    private int pathId;
    private Integer trainId;
    private String platformName;
    private String currentLocation;
    private String direction;
    private Timestamp timestamp;

//    @Id
//    @GeneratedValue
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    @Id
    @Column(name = "path_id")
    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    @Basic
    @Column(name = "train_id")
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "platform_name")
    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @Basic
    @Column(name = "current_location")
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Basic
    @Column(name = "direction")
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathtrainEntity that = (PathtrainEntity) o;
        return pathId == that.pathId && Objects.equals(trainId, that.trainId) && Objects.equals(platformName, that.platformName) && Objects.equals(currentLocation, that.currentLocation) && Objects.equals(direction, that.direction) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathId, trainId, platformName, currentLocation, direction, timestamp);
    }
}
