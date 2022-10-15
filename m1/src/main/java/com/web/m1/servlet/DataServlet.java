package com.web.m1.servlet;
/* Java 1.8 샘플 코드 */


import com.web.m1.data.DataDAO;
import com.web.m1.data.DataRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*1. JSP에서 넘어온 데이터를 변수에 담기
         *2. 변수를 이용해서 최단 거리 구해서 정렬하기
         *3. 다시 JSP로 쏴주기
         *
         * */

        //Json parsing 로직
        DataRepository dataRepository = new DataRepository();
        String mainService = dataRepository.getMAIN_SERVICE();

        String str = dataRepository.importData("json", mainService , "1", "100");

        JSONObject jsonObject = null;
        try {
            jsonObject = dataRepository.JSONObjectByService(dataRepository.parsingToJSONObject(str), mainService);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = dataRepository.JSONArrayByService(jsonObject, dataRepository.getSUB_SERVICE());


        String LAT = "";
        String LNT = "";
        double LAT1 = 0.0;
        double LNT1 = 0.0;
        if (req.getAttribute("LAT") != null && req.getAttribute("LNT") != null) {
            LAT += (String) req.getAttribute("LAT");
            LNT += (String) req.getAttribute("LNT");

            LAT1 = Double.parseDouble(LAT);
            LNT1 = Double.parseDouble(LNT);
        }


        List<DataDAO> list = dataRepository.JSONArrayConvertToArrayList(jsonArray);

        dataRepository.setDist(LAT1, LNT1);


//        Collections.sort(list);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getDist());
//        }

        resp.sendRedirect("1.html");

//        req.setAttribute("datalist", dataRepository.getDataList());

//        String viewPath = "/WEB-INF/views/hello.jsp";
//        RequestDispatcher dispatcher = req.getRequestDispatcher("hello.jsp");
//
//        dispatcher.forward(req, resp);


    }
}



