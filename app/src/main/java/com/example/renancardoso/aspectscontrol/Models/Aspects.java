package com.example.renancardoso.aspectscontrol.Models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by RenanCardoso on 06/09/2017.
 */

public class Aspects extends RealmObject {

    @PrimaryKey
    private String name;
    private String tag;
    private int status;
    private Date createdAt;
    private Date finalizedAt;
    private RealmList<Grades> grades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getFinalizedAt() {
        return finalizedAt;
    }

    public void setFinalizedAt(Date finalizedAt) {
        this.finalizedAt = finalizedAt;
    }

    public RealmList<Grades> getGrades() {
        return grades;
    }

    public void setGrades(RealmList<Grades> grades) {
        this.grades = grades;
    }

    public double meanGrades() {
        if (grades == null) return 0;

        return grades.average("grade");
    }
}
