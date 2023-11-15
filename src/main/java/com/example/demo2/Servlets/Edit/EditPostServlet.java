package com.example.demo2.Servlets.Edit;

import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.Entities.Faculties;
import com.example.demo2.Entities.Posts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet("/editpost")
public class EditPostServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final PostDAO dao;

    public EditPostServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
        dao = new PostDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        List<Posts> posts;
        try {
            posts = dao.findAll();
            request.setAttribute("posts", posts);
        } catch (Exception e) {
            System.out.println(e);
        }
        String strId = request.getParameter("id");
        Long editPostsId = (strId != null) ? Long.parseLong(strId) : null;
        Posts editPost;
        try {
            editPost = dao.findById(editPostsId);
            request.setAttribute("postEdit", editPost);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editpost.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Long editPostsId = (strId != null) ? Long.parseLong(strId) : null;
        Posts updatePost = new Posts(editPostsId, request.getParameter("namepost"));
        try {
            dao.update(updatePost);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/posts");
    }
}
