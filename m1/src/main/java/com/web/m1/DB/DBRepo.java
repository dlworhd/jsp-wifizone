package com.web.m1.DB;

import java.sql.*;

//        dbTest.insert(0.0, "ARI00001", "서대문구", "상수도사업본부", "서소문로51", "본관 1F", "", "공공행정", "서울시", "공공와이파이", "수도사업소자가망", "2014","실내" , "", "126.96675", "37.561924", "2022-10-17 10:58:03.0");
//        String sql = "INSERT INTO wifiinfo VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";

public class DBRepo {

    String url = "jdbc:sqlite:/Users/ejay/Ejay/zerobase/assignment/m1/info.db";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int recentlyAffectedRows;


    // 연결 & 해제
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            if (this.rs != null && !this.rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (this.stmt != null && !this.stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public int getRecentlyAffectedRows() {
        return recentlyAffectedRows;
    }



    // INSERT
    public void insert(double dist, String mgrNum, String fc, String mainNum, String add1, String add2, String instlFloor, String instlTy, String instlMby, String svc, String cmcwr, String cstcYear, String inoutDoor, String remarS3, String lat, String lnt, String workDttm) {

        connect();

        try {
            String sql = "INSERT INTO wifiinfo VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            stmt = this.conn.prepareStatement(sql);
            stmt.setDouble(1, dist);
            stmt.setString(2, mgrNum);
            stmt.setString(3, fc);
            stmt.setString(4, mainNum);
            stmt.setString(5, add1);
            stmt.setString(6, add2);
            stmt.setString(7, instlFloor);
            stmt.setString(8, instlTy);
            stmt.setString(9, instlMby);
            stmt.setString(10, svc);
            stmt.setString(11, cmcwr);
            stmt.setString(12, cstcYear);
            stmt.setString(13, inoutDoor);
            stmt.setString(14, remarS3);
            stmt.setString(15, lat);
            stmt.setString(16, lnt);
            stmt.setString(17, workDttm);
            this.recentlyAffectedRows += stmt.executeUpdate();

            double dist = rs.getDouble(1);
            String mgrNum = rs.getString(2);
            String fc = rs.getString(3);
            String mainNum = rs.getString(4);
            String add1 = rs.getString(5);
            String add2 = rs.getString(6);
            String instlFloor = rs.getString(7);
            String instlTy = rs.getString(8);
            String instlMby = rs.getString(9);
            String svc = rs.getString(10);
            String cmcwr = rs.getString(11);
            String cstcYear = rs.getString(12);
            String inoutDoor = rs.getString(13);
            String remarS3 = rs.getString(14);
            String lat = rs.getString(15);
            String lnt = rs.getString(16);
            String workDttm = rs.getString(17);




            if (this.recentlyAffectedRows > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }
    public void insertDist(double dist, String mgrNum) {
        connect();
        String sql = "UPDATE wifiinfo SET ? WHERE mrgNum = ?";

        try {
            stmt = this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}