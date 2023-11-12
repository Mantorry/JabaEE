package com.example.demo2.Servlets;

import com.example.demo2.DAO.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.Entities.Faculties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    ConnectionProperty prop;
    public FacultyServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculties> faculties;
        FacultyDAO dao = new FacultyDAO();
        try{
            faculties = dao.findAll();
            request.setAttribute("faculties", faculties);
        } catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/faculties.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}