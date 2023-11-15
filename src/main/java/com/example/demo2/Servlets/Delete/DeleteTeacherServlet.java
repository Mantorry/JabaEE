package com.example.demo2.Servlets.Delete;

import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.DAO.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteteacher")
public class DeleteTeacherServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final TeacherDAO dao;

    public DeleteTeacherServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        dao = new TeacherDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long deleteid = (strId != null) ? Long.parseLong(strId) : null;
        try {
            dao.delete(deleteid);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/teachers");
    }
}
