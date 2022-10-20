package com.web.m1.DB;

import com.web.m1.data.History;
import com.web.m1.data.Wifi;

import java.text.SimpleDateFormat;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class JdbcConnect {

    int count = 0;

    public JdbcConnect() {
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String url = "jdbc:sqlite:/Users/ejay/Ejay/zerobase/assignment/m1/info.db";

    // 연결 & 해제
    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    // INSERT
    public void insert(String mgrNum, String fc, String mainNum, String add1, String add2, String instlFloor, String instlTy, String instlMby, String svc, String cmcwr, String cstcYear, String inoutDoor, String remarS3, double lat2, double lnt2, String workDttm) {

        String sql = " INSERT INTO WIFI VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        //DB Connection
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            stmt.setString(1, mgrNum);
            stmt.setString(2, fc);
            stmt.setString(3, mainNum);
            stmt.setString(4, add1);
            stmt.setString(5, add2);
            stmt.setString(6, instlFloor);
            stmt.setString(7, instlTy);
            stmt.setString(8, instlMby);
            stmt.setString(9, svc);
            stmt.setString(10, cmcwr);
            stmt.setString(11, cstcYear);
            stmt.setString(12, inoutDoor);
            stmt.setString(13, remarS3);
            stmt.setDouble(14, lat2);
            stmt.setDouble(15, lnt2);
            stmt.setString(16, workDttm);

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void insertHistory(double lat1, double lnt1) {

        String date = "datetime('now')";
        String sql = " INSERT INTO HISTORY(lat1, lnt1, date) VALUES(?, ?, ?) ";
        Connection conn = getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lat1);
            stmt.setDouble(2, lnt1);
            stmt.setString(3, date);
            int count = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void insertDist(double dist, String mgrNum) {

        Connection conn = getConnection();
        PreparedStatement stmt = null;

        String sql = " INSERT INTO DISTANCE(dist, mgrNum) VALUES(?, ?) ";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, dist);
            stmt.setString(2, mgrNum);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Wifi> selectAll() {
        String sql = " SELECT * FROM WIFI";

        List<Wifi> dataList = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setMgrNum(rs.getString("mgrNum"));
                wifi.setFc(rs.getString("fc"));
                wifi.setMainNum(rs.getString("mainNum"));
                wifi.setAdd1(rs.getString("add1"));
                wifi.setAdd2(rs.getString("add2"));
                wifi.setInstlFloor(rs.getString("instlFloor"));
                wifi.setInstlTy(rs.getString("instlTy"));
                wifi.setInstlMby(rs.getString("instlMby"));
                wifi.setSvc(rs.getString("svc"));
                wifi.setCmcwr(rs.getString("cmcwr"));
                wifi.setCstcYear(rs.getString("cstcYear"));
                wifi.setInoutDoor(rs.getString("inoutDoor"));
                wifi.setRemarS3(rs.getString("remarS3"));
                wifi.setLat2(Double.parseDouble(rs.getString("lat2")));
                wifi.setLnt2(Double.parseDouble(rs.getString("lnt2")));
                wifi.setWorkDttm(rs.getString("workDttm"));
                dataList.add(wifi);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return dataList;

    }

    public List<History> selectHistories() {
        List<History> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "SELECT * FROM HISTORY";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setLat1(Double.parseDouble(rs.getString("lat1")));
                history.setLnt1(Double.parseDouble(rs.getString("lnt1")));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                history.setDate(sdf.format(timestamp));



                list.add(history);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;

    }

    public void removeHistory(int id) {

        String sql = "DELETE FROM HISTORY WHERE id = ?";

        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
