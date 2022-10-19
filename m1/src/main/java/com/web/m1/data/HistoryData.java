package com.web.m1.data;

public class HistoryData {
    int id;
    double lat1;
    double lnt1;
    String date;

    public int getId() {
        return id;
    }

    public double getLat1() {
        return lat1;
    }

    public double getLnt1() {
        return lnt1;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }


    public void setLnt1(double lnt1) {
        this.lnt1 = lnt1;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
