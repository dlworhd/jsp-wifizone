package com.web.m1.data;

import java.time.LocalDateTime;
import java.util.Date;

public class History {
    long no = 0;
    double LAT;
    double LNT;
    LocalDateTime date;

    public long getNo() {
        return no;
    }

    public History(double LAT, double LNT, LocalDateTime date) {
        this.no = no++;
        this.LAT = LAT;
        this.LNT = LNT;
        this.date = date;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public double getLNT() {
        return LNT;
    }

    public void setLNT(double LNT) {
        this.LNT = LNT;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
