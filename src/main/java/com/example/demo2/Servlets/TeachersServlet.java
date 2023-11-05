package com.example.demo2.Servlets;

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

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

    public TeachersServlet() {super();}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Faculties[] faculties = new Faculties[]{
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
        Posts[] posts = new Posts[]{
                new Posts(1L, "Зав.Кафедры"),
                new Posts(2L, "Доцент Кафедры"),
                new Posts(3L, "Профессор Кафедры"),
                new Posts(4L, "Старший преподаватель"),
                new Posts(5L, "Ассистент")
        };
        Teachers[] teachers = new Teachers[]{
                new Teachers(1L, 1L, 1L, "Щербаков", "Сергей", "Михайлович", "89284445566", "sergwood@mail.ru",chairs[0], posts[0]),
                new Teachers(2L, 2L, 2L, "Алексейчик", "Тамара", "Васильевна", "89554324543", "alekseychik48@mail.ru",chairs[1], posts[1])
        };
        request.setAttribute("faculties", faculties);
        request.setAttribute("chairs", chairs);
        request.setAttribute("posts", posts);
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("views/teachers.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
