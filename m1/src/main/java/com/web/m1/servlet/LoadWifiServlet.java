package com.web.m1.servlet;

import com.web.m1.data.WifiData;
import com.web.m1.data.JSONParsing;
import com.web.m1.DB.JdbcConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/load-wifi")
public class LoadWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcConnect jdbcConnect = new JdbcConnect();

        //Json parsing 로직
        JSONParsing jsonparser = new JSONParsing();
        String mainService = jsonparser.getMAIN_SERVICE();
        String str = jsonparser.importData("json", mainService, "1", "1000");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonparser.JSONObjectByService(jsonparser.parsingToJSONObject(str), mainService);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = jsonparser.JSONArrayByService(jsonObject, jsonparser.getSUB_SERVICE());

        List<JSONObject> list = jsonparser.JSONArrayConvertToArrayList(jsonArray);
        List<WifiData> dataList = jsonparser.JSONToDBdataArray(list);


        for (int i = 0; i < dataList.size(); i++) {
            jdbcConnect.insert(
                    dataList.get(i).getMgrNum(),
                    dataList.get(i).getFc(),
                    dataList.get(i).getMainNum(),
                    dataList.get(i).getAdd1(),
                    dataList.get(i).getAdd2(),
                    dataList.get(i).getInstlFloor(),
                    dataList.get(i).getInstlTy(),
                    dataList.get(i).getInstlMby(),
                    dataList.get(i).getSvc(),
                    dataList.get(i).getCmcwr(),
                    dataList.get(i).getCstcYear(),
                    dataList.get(i).getInoutDoor(),
                    dataList.get(i).getRemarS3(),
                    dataList.get(i).getLat2(),
                    dataList.get(i).getLnt2(),
                    dataList.get(i).getWorkDttm());
        }

        req.setAttribute("dataNum", dataList.size());
        RequestDispatcher dispatcher = req.getRequestDispatcher("load-wifi.jsp");
        dispatcher.forward(req, resp);

    }
}
