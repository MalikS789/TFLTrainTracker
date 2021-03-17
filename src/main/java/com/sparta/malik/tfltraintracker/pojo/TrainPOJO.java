package com.sparta.malik.tfltraintracker.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "$type",
        "id",
        "operationType",
        "vehicleId",
        "naptanId",
        "stationName",
        "lineId",
        "lineName",
        "platformName",
        "direction",
        "bearing",
        "destinationNaptanId",
        "destinationName",
        "timestamp",
        "timeToStation",
        "currentLocation",
        "towards",
        "expectedArrival",
        "timeToLive",
        "modeName",
        "timing"
})
public class TrainPOJO {

    @JsonProperty("$type")
    private String $type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("operationType")
    private Integer operationType;
    @JsonProperty("vehicleId")
    private String vehicleId;
    @JsonProperty("naptanId")
    private String naptanId;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("lineId")
    private String lineId;
    @JsonProperty("lineName")
    private String lineName;
    @JsonProperty("platformName")
    private String platformName;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("bearing")
    private String bearing;
    @JsonProperty("destinationNaptanId")
    private String destinationNaptanId;
    @JsonProperty("destinationName")
    private String destinationName;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("timeToStation")
    private Integer timeToStation;
    @JsonProperty("currentLocation")
    private String currentLocation;
    @JsonProperty("towards")
    private String towards;
    @JsonProperty("expectedArrival")
    private String expectedArrival;
    @JsonProperty("timeToLive")
    private String timeToLive;
    @JsonProperty("modeName")
    private String modeName;
    @JsonProperty("timing")
    private Timing timing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("$type")
    public String get$type() {
        return $type;
    }

    @JsonProperty("$type")
    public void set$type(String $type) {
        this.$type = $type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("operationType")
    public Integer getOperationType() {
        return operationType;
    }

    @JsonProperty("operationType")
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    @JsonProperty("vehicleId")
    public String getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicleId")
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("naptanId")
    public String getNaptanId() {
        return naptanId;
    }

    @JsonProperty("naptanId")
    public void setNaptanId(String naptanId) {
        this.naptanId = naptanId;
    }

    @JsonProperty("stationName")
    public String getStationName() {
        return stationName;
    }

    @JsonProperty("stationName")
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @JsonProperty("lineId")
    public String getLineId() {
        return lineId;
    }

    @JsonProperty("lineId")
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    @JsonProperty("lineName")
    public String getLineName() {
        return lineName;
    }

    @JsonProperty("lineName")
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @JsonProperty("platformName")
    public String getPlatformName() {
        return platformName;
    }

    @JsonProperty("platformName")
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("bearing")
    public String getBearing() {
        return bearing;
    }

    @JsonProperty("bearing")
    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    @JsonProperty("destinationNaptanId")
    public String getDestinationNaptanId() {
        return destinationNaptanId;
    }

    @JsonProperty("destinationNaptanId")
    public void setDestinationNaptanId(String destinationNaptanId) {
        this.destinationNaptanId = destinationNaptanId;
    }

    @JsonProperty("destinationName")
    public String getDestinationName() {
        return destinationName;
    }

    @JsonProperty("destinationName")
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("timeToStation")
    public Integer getTimeToStation() {
        return timeToStation;
    }

    @JsonProperty("timeToStation")
    public void setTimeToStation(Integer timeToStation) {
        this.timeToStation = timeToStation;
    }

    @JsonProperty("currentLocation")
    public String getCurrentLocation() {
        return currentLocation;
    }

    @JsonProperty("currentLocation")
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @JsonProperty("towards")
    public String getTowards() {
        return towards;
    }

    @JsonProperty("towards")
    public void setTowards(String towards) {
        this.towards = towards;
    }

    @JsonProperty("expectedArrival")
    public String getExpectedArrival() {
        return expectedArrival;
    }

    @JsonProperty("expectedArrival")
    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    @JsonProperty("timeToLive")
    public String getTimeToLive() {
        return timeToLive;
    }

    @JsonProperty("timeToLive")
    public void setTimeToLive(String timeToLive) {
        this.timeToLive = timeToLive;
    }

    @JsonProperty("modeName")
    public String getModeName() {
        return modeName;
    }

    @JsonProperty("modeName")
    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    @JsonProperty("timing")
    public Timing getTiming() {
        return timing;
    }

    @JsonProperty("timing")
    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}