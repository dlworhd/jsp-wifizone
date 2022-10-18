package com.web.m1.servlet;

import com.web.m1.DB.DBRepo;
import com.web.m1.data.DataDAO;
import com.web.m1.data.DataRepository;
import com.web.m1.data.History;
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
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/load-wifi")
public class LoadWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBRepo dbRepo = new DBRepo();

        //Json parsing 로직
        DataRepository dataRepository = new DataRepository();
        String mainService = dataRepository.getMAIN_SERVICE();

        String str = dataRepository.importData("json", mainService, "1", "20");

        JSONObject jsonObject = null;
        try {
            jsonObject = dataRepository.JSONObjectByService(dataRepository.parsingToJSONObject(str), mainService);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = dataRepository.JSONArrayByService(jsonObject, dataRepository.getSUB_SERVICE());


        //데이터를 받아와서 저장하고 정렬하는 로직
        String LAT = "";
        String LNT = "";
        double LAT1 = 0.0;
        double LNT1 = 0.0;
        if (req.getAttribute("LAT") != null && req.getAttribute("LNT") != null) {


            LAT += (String) req.getAttribute("LAT");
            LNT += (String) req.getAttribute("LNT");

            LAT1 = Double.parseDouble(LAT);
            LNT1 = Double.parseDouble(LNT);

            History historyData = new History(LAT1, LNT1, LocalDateTime.now());

        }
        List<DataDAO> list = dataRepository.JSONArrayConvertToArrayList(jsonArray);







        for (int i = 0; i < list.size(); i++) {
            dbRepo.insert(0.0,
                    list.get(i).getMgrNum(),
                    list.get(i).getFc(),
                    list.get(i).getMainNum(),
                    list.get(i).getAdd1(),
                    list.get(i).getAdd2(),
                    list.get(i).getInstlFloor(),
                    list.get(i).getInstlTy(),
                    list.get(i).getInstlMby(),
                    list.get(i).getSvc(),
                    list.get(i).getCmcwr(),
                    list.get(i).getCstcYear(),
                    list.get(i).getInoutDoor(),
                    list.get(i).getRemarS3(),
                    list.get(i).getLat(),
                    list.get(i).getLnt(),
                    list.get(i).getWorkDttm());
        }



        req.setAttribute("dataNum", dbRepo.getRecentlyAffectedRows());
        RequestDispatcher dispatcher = req.getRequestDispatcher("load-wifi.jsp");
        dispatcher.forward(req,resp);

    }
}
