package com.example.renancardoso.aspectscontrol.Models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by RenanCardoso on 06/09/2017.
 */

public class Aspects extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private int status;
    private String createdAt;
    private String finalizedAt;
    private RealmList<Grades> grades;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFinalizedAt() {
        return finalizedAt;
    }

    public void setFinalizedAt(String finalizedAt) {
        this.finalizedAt = finalizedAt;
    }

    public RealmList<Grades> getGrades() {
        return grades;
    }

    public void setGrades(RealmList<Grades> grades) {
        this.grades = grades;
    }
}
