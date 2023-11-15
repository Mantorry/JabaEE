package com.example.demo2.Servlets.Edit;

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

@WebServlet("/editchair")
public class EditChairServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final FacultyDAO facultyDAO;
    private final ChairDAO chairDAO;

    public EditChairServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        facultyDAO = new FacultyDAO();
        chairDAO = new ChairDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculties> faculties;
        List<Chairs> chairs;
        String strId = request.getParameter("id");
        Long editChairId = (strId != null) ? Long.parseLong(strId) : null;
        Chairs editChair;
        try {
            faculties = facultyDAO.findAll();
            chairs = chairDAO.findAll();
            for (Chairs ch:chairs){
                ch.setFaculties(facultyDAO.FindById(ch.getIdFaculty(), faculties));
            }
            editChair = chairDAO.findById(editChairId);
            editChair.setFaculties(facultyDAO.findById(editChair.getIdFaculty()));
            faculties.removeIf(faculties1 -> faculties1.getId() == editChair.getIdFaculty());
            request.setAttribute("faculties", faculties);
            request.setAttribute("chairs", chairs);
            request.setAttribute("chairEdit", editChair);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editchair.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editChairId = (strId != null) ? Long.parseLong(strId) : null;
            String faculty = request.getParameter("facultyField");
            int index1 = faculty.indexOf('=');
            int index2 = faculty.indexOf(",");
            String r1 = faculty.substring(index1+1, index2);
            long facultyId = Long.parseLong(r1.trim());
            Faculties faculties = facultyDAO.findById(facultyId);
            chairDAO.update(new Chairs(editChairId, facultyId, request.getParameter("fullName"), request.getParameter("shortName"), faculties));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/chairs");
    }
}
