package com.sparta.malik.tfltraintracker.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "$type",
        "countdownServerAdjustment",
        "source",
        "insert",
        "read",
        "sent",
        "received"
})
public class Timing {

    @JsonProperty("$type")
    private String $type;
    @JsonProperty("countdownServerAdjustment")
    private String countdownServerAdjustment;
    @JsonProperty("source")
    private String source;
    @JsonProperty("insert")
    private String insert;
    @JsonProperty("read")
    private String read;
    @JsonProperty("sent")
    private String sent;
    @JsonProperty("received")
    private String received;
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

    @JsonProperty("countdownServerAdjustment")
    public String getCountdownServerAdjustment() {
        return countdownServerAdjustment;
    }

    @JsonProperty("countdownServerAdjustment")
    public void setCountdownServerAdjustment(String countdownServerAdjustment) {
        this.countdownServerAdjustment = countdownServerAdjustment;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("insert")
    public String getInsert() {
        return insert;
    }

    @JsonProperty("insert")
    public void setInsert(String insert) {
        this.insert = insert;
    }

    @JsonProperty("read")
    public String getRead() {
        return read;
    }

    @JsonProperty("read")
    public void setRead(String read) {
        this.read = read;
    }

    @JsonProperty("sent")
    public String getSent() {
        return sent;
    }

    @JsonProperty("sent")
    public void setSent(String sent) {
        this.sent = sent;
    }

    @JsonProperty("received")
    public String getReceived() {
        return received;
    }

    @JsonProperty("received")
    public void setReceived(String received) {
        this.received = received;
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