package com.example.renancardoso.aspectscontrol.Models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by RenanCardoso on 20/09/2017.
 */

public class RoutineDates extends RealmObject {
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
