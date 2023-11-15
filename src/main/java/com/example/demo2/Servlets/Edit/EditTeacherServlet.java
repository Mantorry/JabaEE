package com.example.demo2.Servlets.Edit;

import com.example.demo2.DAO.ChairDAO;
import com.example.demo2.DAO.Connection.ConnectionProperty;
import com.example.demo2.DAO.FacultyDAO;
import com.example.demo2.DAO.PostDAO;
import com.example.demo2.DAO.TeacherDAO;
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
import java.util.List;

@WebServlet("/editteacher")
public class EditTeacherServlet extends HttpServlet {
    private final ConnectionProperty prop;
    private final PostDAO postDAO;
    private final ChairDAO chairDAO;
    private final TeacherDAO teacherDAO;

    public EditTeacherServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
        postDAO = new PostDAO();
        chairDAO = new ChairDAO();
        teacherDAO = new TeacherDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Chairs> chairs;
        List<Posts> posts;
        List<Teachers> teachers;

        String strId = request.getParameter("id");
        Long editTeacherId = (strId != null) ? Long.parseLong(strId) : null;
        Teachers editTeacher;
        try {
            chairs = chairDAO.findAll();
            posts = postDAO.findAll();
            teachers = teacherDAO.findAll();
            for (Teachers teach:teachers){
                teach.setChairs(chairDAO.FindById(teach.getIdChairs(), chairs));
                teach.setPosts(postDAO.FindById(teach.getIdPosts(), posts));
            }

            editTeacher = teacherDAO.findById(editTeacherId);
            editTeacher.setChairs(chairDAO.findById(editTeacher.getIdChairs()));
            chairs.removeIf(chairs1 -> chairs1.getId() == editTeacher.getIdChairs());
            editTeacher.setPosts(postDAO.findById(editTeacher.getIdPosts()));
            posts.removeIf(posts1 -> posts1.getId() == editTeacher.getIdPosts());
            request.setAttribute("chairs", chairs);
            request.setAttribute("posts", posts);
            request.setAttribute("teachers", teachers);
            request.setAttribute("teacherEdit", editTeacher);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("/views/editteacher.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            Long editTeacherId = (strId != null) ? Long.parseLong(strId) : null;

            String chair = request.getParameter("chairField");
            int index1 = chair.indexOf('=');
            int index2 = chair.indexOf(",");
            String r1 = chair.substring(index1+1, index2);
            long chairId = Long.parseLong(r1.trim());
            Chairs chairs = chairDAO.findById(chairId);

            String post = request.getParameter("postField");
            int index3 = post.indexOf('=');
            int index4 = post.indexOf(",");
            String r2 = post.substring(index3+1, index4);
            long postId = Long.parseLong(r2.trim());
            Posts posts = postDAO.findById(postId);

            teacherDAO.update(new Teachers(editTeacherId, chairId, postId, request.getParameter("secondName"), request.getParameter("firstName"),
                    request.getParameter("lastName"), request.getParameter("phone"), request.getParameter("email"), chairs, posts));
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("/demo2_war_exploded/teachers");
    }
}