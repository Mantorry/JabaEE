package com.example.demo2.Servlets;

import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.Entities.Chairs;
import com.example.demo2.Entities.Faculties;
import com.example.demo2.Entities.Posts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/chairs")
public class ChairsServlet extends HttpServlet {

    ConnectionProperty prop;
    public ChairsServlet() throws IOException {super();prop = new ConnectionProperty();}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculties> faculties;
        List<Chairs> chairs;
        FacultyDAO facultyDAO = new FacultyDAO();
        ChairDAO chairDAO = new ChairDAO();
        try{
            faculties = facultyDAO.findAll();
            request.setAttribute("faculties", faculties);
            chairs = chairDAO.findAll();
            request.setAttribute("chairs", chairs);
            for (Chairs ch:chairs){
                ch.setFaculties(facultyDAO.FindById(ch.getIdFaculty(), faculties));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/chairs.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
