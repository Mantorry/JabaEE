package com.example.demo2.Servlets;

import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.Entities.Chairs;
import com.example.demo2.Entities.Faculties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/chairs")
public class ChairsServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final FacultyDAO facultyDAO;
    private final ChairDAO chairDAO;
    public ChairsServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        chairDAO = new ChairDAO();
        facultyDAO = new FacultyDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculties> faculties;
        List<Chairs> chairs;
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
        try {
            String faculty = request.getParameter("faculty");
            int index1 = faculty.indexOf('=');
            int index2 = faculty.indexOf(",");
            String r1 = faculty.substring(index1+1, index2);
            long facultyId = Long.parseLong(r1.trim());
            Faculties faculties = facultyDAO.findById(facultyId);
            chairDAO.insert(new Chairs(facultyId, request.getParameter("fullName"), request.getParameter("shortName"), faculties));

        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}
