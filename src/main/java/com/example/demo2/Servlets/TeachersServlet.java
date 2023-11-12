package com.example.demo2.Servlets;

import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.ConnectionProperty;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.DAO.TeacherDAO;
import com.example.demo2.Entities.Chairs;
import com.example.demo2.Entities.Faculties;
import com.example.demo2.Entities.Posts;
import com.example.demo2.Entities.Teachers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

    ConnectionProperty prop;
    public TeachersServlet() throws IOException {super();prop = new ConnectionProperty();}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Chairs> chairs;
        List<Posts> posts;
        List<Teachers> teachers;
        ChairDAO chairDAO = new ChairDAO();
        PostDAO postDAO = new PostDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        try{
            chairs = chairDAO.findAll();
            request.setAttribute("chairs", chairs);
            posts = postDAO.findAll();
            request.setAttribute("posts", posts);
            teachers = teacherDAO.findAll();
            request.setAttribute("teachers", teachers);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/teachers.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
