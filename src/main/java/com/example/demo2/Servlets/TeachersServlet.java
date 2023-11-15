package com.example.demo2.Servlets;

import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.DAO.TeacherDAO;
import com.example.demo2.Entities.Chairs;
import com.example.demo2.Entities.Posts;
import com.example.demo2.Entities.Teachers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

    private final ConnectionProperty prop;
    private final ChairDAO chairDAO;
    private final PostDAO postDAO;
    private final TeacherDAO teacherDAO;
    public TeachersServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        chairDAO = new ChairDAO();
        postDAO = new PostDAO();
        teacherDAO = new TeacherDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Chairs> chairs;
        List<Posts> posts;
        List<Teachers> teachers;
        try{
            chairs = chairDAO.findAll();
            request.setAttribute("chairs", chairs);
            posts = postDAO.findAll();
            request.setAttribute("posts", posts);
            teachers = teacherDAO.findAll();
            request.setAttribute("teachers", teachers);
            for (Teachers teach:teachers){
                teach.setChairs(chairDAO.FindById(teach.getIdChairs(), chairs));
                teach.setPosts(postDAO.FindById(teach.getIdPosts(), posts));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        request.getRequestDispatcher("views/teachers.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String chair = request.getParameter("chairs");
            int index1 = chair.indexOf('=');
            int index2 = chair.indexOf(",");
            String r1 = chair.substring(index1+1, index2);
            long chairId = Long.parseLong(r1.trim());
            Chairs chairs = chairDAO.findById(chairId);

            String post = request.getParameter("posts");
            int index3 = post.indexOf('=');
            int index4 = post.indexOf(",");
            String r2 = post.substring(index3+1, index4);
            long postId = Long.parseLong(r2.trim());
            Posts posts = postDAO.findById(postId);
            teacherDAO.insert(new Teachers(chairId, postId, request.getParameter("secondName"), request.getParameter("firstName"),
                    request.getParameter("lastName"), request.getParameter("phone"),
                            request.getParameter("email"), chairs, posts));
        }catch (Exception e){
            System.out.println(e);
        }
        doGet(request, response);
    }
}
