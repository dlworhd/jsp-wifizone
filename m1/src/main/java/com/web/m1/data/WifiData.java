package com.web.m1.data;


public class WifiData implements Comparable<WifiData> {

    private double dist;
    private String mgrNum;
    private String fc;
    private String mainNum;
    private String add1;
    private String add2;
    private String instlFloor;
    private String instlTy;
    private String instlMby;
    private String svc;
    private String cmcwr;
    private String cstcYear;
    private String inoutDoor;
    private String remarS3;
    private double lat2;
    private double lnt2;
    private String workDttm;

    public WifiData() {
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public String getMgrNum() {
        return mgrNum;
    }

    public void setMgrNum(String mgrNum) {
        this.mgrNum = mgrNum;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getMainNum() {
        return mainNum;
    }

    public void setMainNum(String mainNum) {
        this.mainNum = mainNum;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getInstlFloor() {
        return instlFloor;
    }

    public void setInstlFloor(String instlFloor) {
        this.instlFloor = instlFloor;
    }

    public String getInstlTy() {
        return instlTy;
    }

    public void setInstlTy(String instlTy) {
        this.instlTy = instlTy;
    }

    public String getInstlMby() {
        return instlMby;
    }

    public void setInstlMby(String instlMby) {
        this.instlMby = instlMby;
    }

    public String getSvc() {
        return svc;
    }

    public void setSvc(String svc) {
        this.svc = svc;
    }

    public String getCmcwr() {
        return cmcwr;
    }

    public void setCmcwr(String cmcwr) {
        this.cmcwr = cmcwr;
    }

    public String getCstcYear() {
        return cstcYear;
    }

    public void setCstcYear(String cstcYear) {
        this.cstcYear = cstcYear;
    }

    public String getInoutDoor() {
        return inoutDoor;
    }

    public void setInoutDoor(String inoutDoor) {
        this.inoutDoor = inoutDoor;
    }

    public String getRemarS3() {
        return remarS3;
    }

    public void setRemarS3(String remarS3) {
        this.remarS3 = remarS3;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public double getLnt2() {
        return lnt2;
    }

    public void setLnt2(double lnt2) {
        this.lnt2 = lnt2;
    }

    public String getWorkDttm() {
        return workDttm;
    }

    public void setWorkDttm(String workDttm) {
        this.workDttm = workDttm;
    }

    @Override
    public String toString() {
        return "DataDAO{" +
                "dist=" + dist +
                ", mgrNum='" + mgrNum + '\'' +
                ", fc='" + fc + '\'' +
                ", mainNum='" + mainNum + '\'' +
                ", add1='" + add1 + '\'' +
                ", add2='" + add2 + '\'' +
                ", instlFloor='" + instlFloor + '\'' +
                ", instlTy='" + instlTy + '\'' +
                ", instlMby='" + instlMby + '\'' +
                ", svc='" + svc + '\'' +
                ", cmcwr='" + cmcwr + '\'' +
                ", cstcYear='" + cstcYear + '\'' +
                ", inoutDoor='" + inoutDoor + '\'' +
                ", remarS3='" + remarS3 + '\'' +
                ", lat2='" + lat2 + '\'' +
                ", lnt2='" + lnt2 + '\'' +
                ", workDttm='" + workDttm + '\'' +
                '}';
    }

    @Override
    public int compareTo(WifiData o) {
        if (this.getDist() > o.getDist()) {
            return 1;
        } else if (this.getDist() == o.getDist()) {
            return 0;
        } else {
            return -1;
        }
    }
}
