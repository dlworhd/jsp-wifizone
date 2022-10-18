package com.web.m1.servlet;

import com.web.m1.DB.DBRepo;
import com.web.m1.data.DataDAO;
import com.web.m1.data.DataRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/aroundwifi")
public class AroundWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBRepo dbtest = new DBRepo();
        DataRepository dr = new DataRepository();

        double LAT = 0;
        double LNT = 0;

        if (req.getAttribute("LAT") != null && req.getAttribute("LNT") != null) {
            LAT = (double) req.getAttribute("LAT");
            LNT = (double) req.getAttribute("LNT");


        }



        for (int i = 0; i < dataList.size(); i++) {
            double LAT2 = Double.parseDouble(dataList.get(i).getLat());
            double LNT2 = Double.parseDouble(dataList.get(i).getLnt());
            double d = distance(LAT, LNT, LAT2, LNT2);
            dataList.get(i).setDist(d);
        }





        List<DataDAO> datalist = dbtest.dataSortByDist();

        req.setAttribute("datalist", datalist);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);


    }
}
