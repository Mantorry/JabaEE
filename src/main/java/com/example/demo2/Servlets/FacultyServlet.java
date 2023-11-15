package com.example.demo2.Servlets;

import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.Entities.Faculties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final FacultyDAO dao;
    public FacultyServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
        dao = new FacultyDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculties> faculties;
        try{
            faculties = dao.findAll();
            request.setAttribute("faculties", faculties);
        } catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/faculties.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            dao.insert(new Faculties(request.getParameter("fullName"), request.getParameter("shortName")));
        } catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}