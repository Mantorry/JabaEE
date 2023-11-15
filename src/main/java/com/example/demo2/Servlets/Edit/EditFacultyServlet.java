package com.example.demo2.Servlets.Edit;

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

@WebServlet("/editfaculty")
public class EditFacultyServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final FacultyDAO dao;

    public EditFacultyServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new FacultyDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Faculties> faculties;
        try {
            faculties = dao.findAll();
            request.setAttribute("faculties", faculties);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editFacultyId = (strId != null) ? Long.parseLong(strId) : null;
        Faculties editFaculty;
        try {
            editFaculty = dao.findById(editFacultyId);
            request.setAttribute("facultyEdit", editFaculty);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editfaculty.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editFacultyId = (strId != null) ? Long.parseLong(strId) : null;
        Faculties updateFaculty = new Faculties(editFacultyId, request.getParameter("fullName"), request.getParameter("shortName"));
        try {
            dao.update(updateFaculty);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/faculty");
    }
}