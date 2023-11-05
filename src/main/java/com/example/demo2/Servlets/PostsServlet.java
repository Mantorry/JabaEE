package com.example.demo2.Servlets;

import com.example.demo2.Entities.Faculties;
import com.example.demo2.Entities.Posts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {

    public PostsServlet() {super();}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Posts[] posts = new Posts[]{
                new Posts(1L, "Зав.Кафедры"),
                new Posts(2L, "Доцент Кафедры"),
                new Posts(3L, "Профессор Кафедры"),
                new Posts(4L, "Старший преподаватель"),
                new Posts(5L, "Ассистент")
        };
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("views/posts.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}