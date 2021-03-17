package com.sparta.malik.tfltraintracker.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trains", schema = "tfltracker", catalog = "")
public class TrainsEntity {
//    private int id;
    private int trainId;
    private String lineId;

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
    @Column(name = "train_id")
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "line_id")
    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainsEntity that = (TrainsEntity) o;
        return trainId == that.trainId && Objects.equals(lineId, that.lineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, lineId);
    }
}
