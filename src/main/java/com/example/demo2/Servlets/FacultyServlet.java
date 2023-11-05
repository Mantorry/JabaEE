package com.example.demo2.Servlets;

import com.example.demo2.Entities.Faculties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    public FacultyServlet() {super();}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Faculties[] faculties = new Faculties[] {
                new Faculties(1L, "Факультет Компьютерных технологий и информационной безопасности", "КТиИБ"),
                new Faculties(2L, "Факультет Торговое дело", "ТД"),
                new Faculties(3L, "Факультет Экономики и финансов", "ЭиФ"),
                new Faculties(4L, "Учетно-экономический факультет", "УЭФ"),
                new Faculties(5L, "Факультет Менеджмента и предпринимательства", "МиП"),
                new Faculties(6L, "Юридический факультет", "ЮрФак"),
                new Faculties(7L, "Факультет Лингвистики и Журналистики", "ЛиЖ"),
                new Faculties(8L, "Институт Магистратуры", "ИМ")
        };
        request.setAttribute("faculties", faculties);
        request.getRequestDispatcher("views/faculties.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}