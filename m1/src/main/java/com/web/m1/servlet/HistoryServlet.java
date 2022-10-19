package com.web.m1.servlet;


import com.web.m1.db.JdbcConnect;
import com.web.m1.data.HistoryData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JdbcConnect jdbcConnect = new JdbcConnect();
        List<HistoryData> histories = jdbcConnect.selectHistories();



        req.setAttribute("histories", histories);

        RequestDispatcher dispatcher = req.getRequestDispatcher("histories.jsp");
        dispatcher.forward(req,resp);
    }
}
