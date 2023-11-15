package com.example.demo2.Servlets;

import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.Entities.Posts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class PostsServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final PostDAO dao;
    public PostsServlet() throws FileNotFoundException, IOException{
        super();
        prop = new ConnectionProperty();
        dao = new PostDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Posts> posts;
        try{
            posts = dao.findAll();
            request.setAttribute("posts", posts);
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/posts.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            dao.insert(new Posts(request.getParameter("fullname")));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}