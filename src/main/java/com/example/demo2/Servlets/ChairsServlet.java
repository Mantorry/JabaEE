package com.example.demo2.Servlets;

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

@WebServlet("/chairs")
public class ChairsServlet extends HttpServlet {

    public ChairsServlet() {super();}

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

        Chairs[] chairs = new Chairs[]{
                new Chairs(1L,1L,"Информационные системы и прикладная информатика","ИСиПИ", faculties[0]),
                new Chairs(2L,1L,"Фундаментальная и прикладная математика","ФиПМ", faculties[0]),
                new Chairs(3L,1L,"Информационные технологии и защита информации","ИТиЗИ", faculties[0]),
                new Chairs(3L,1L,"Физическое воспитание спорта и туризма","ФВСиТ", faculties[0])
        };
        request.setAttribute("faculties", faculties);
        request.setAttribute("chairs", chairs);
        request.getRequestDispatcher("views/chairs.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
