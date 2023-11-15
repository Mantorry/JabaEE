package com.example.demo2.Servlets.Delete;


import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletechair")
public class DeleteChairServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final ChairDAO dao;

    public DeleteChairServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new ChairDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/chairs");
    }
}
