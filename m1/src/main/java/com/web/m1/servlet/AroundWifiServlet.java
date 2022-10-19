package com.web.m1.servlet;

import com.web.m1.DB.JdbcConnect;
import com.web.m1.data.Calculator;
import com.web.m1.data.WifiData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@WebServlet("/aroundwifi")
public class AroundWifiServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<WifiData> wifiDataList = new ArrayList<>();
        JdbcConnect jdbcConnect = new JdbcConnect();

        //JSP에서 넘어온 Parameters
        double lat1 = Double.parseDouble(req.getParameter("lat1"));
        double lnt1 = Double.parseDouble(req.getParameter("lnt1"));

        //히스토리 데이터 DB에 저장
        jdbcConnect.insertHistory(lat1, lnt1);



        wifiDataList = jdbcConnect.selectAll();



        for (int i = 0; i < wifiDataList.size(); i++) {
            double lat2 = wifiDataList.get(i).getLat2();
            double lnt2 = wifiDataList.get(i).getLnt2();

            double dist = Calculator.distance(lat1, lnt1, lat2, lnt2);
            wifiDataList.get(i).setDist(dist);
            String m = wifiDataList.get(i).getMgrNum();
            double g = wifiDataList.get(i).getDist();

            jdbcConnect.insertDist(g, m);
        }

        Collections.sort(wifiDataList);

        List<WifiData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(wifiDataList.get(i));
        }



        req.setAttribute("datalist", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);


    }
}
