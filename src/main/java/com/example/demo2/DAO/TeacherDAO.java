package com.example.demo2.DAO;

import com.example.demo2.DAO.Connection.ConnectionBuilder;
import com.example.demo2.DAO.Connection.DbConnectionBuilder;
import com.example.demo2.Entities.Teachers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeacherDAO implements RepositoryDAO<Teachers> {
    public TeacherDAO(){}

    private static final String select_all = "SELECT teachers_id, chair_id, post_id, second_name, first_name, " +
            "last_Name, phone, email FROM teachers";
    private static final String select_teacher_ById = "SELECT teachers_id, chair_id, post_id, second_name, " +
            "first_name, last_Name, phone, email  FROM teachers WHERE teachers_id = ?";
    private static final String insert_teacher = "INSERT INTO teachers(chair_id, post_id, second_name, first_name, " +
            "last_Name, phone, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String edit_teacher = "UPDATE teachers SET chair_id = ?, post_id = ?, second_name = ?, " +
            "first_name = ?, last_Name = ?, phone = ?, email = ? WHERE teachers_id = ? ";
    private static final String delete_teacher = "DELETE FROM teachers WHERE teachers_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Teachers teachers) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_teacher, new String[] { "teachers_id" })) {
            long Id = -1L;
            pst.setLong(1, teachers.getIdChairs());
            pst.setLong(2, teachers.getIdPosts());
            pst.setString(3, teachers.getSecondName());
            pst.setString(4, teachers.getFirstName());
            pst.setString(5, teachers.getLastName());
            pst.setString(6, teachers.getPhone());
            pst.setString(7, teachers.getEmail());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("teachers_id");
            }
            gk.close();
            return Id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1L;
    }
    // Редактирование должности
    @Override
    public void update(Teachers teachers) {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_teacher)) {
            pst.setLong(1, teachers.getIdChairs());
            pst.setLong(2, teachers.getIdPosts());
            pst.setString(3, teachers.getSecondName());
            pst.setString(4, teachers.getFirstName());
            pst.setString(5, teachers.getLastName());
            pst.setString(6, teachers.getPhone());
            pst.setString(7, teachers.getEmail());
            pst.setLong(8, teachers.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_teacher)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Teachers findById(Long Id) {
        Teachers teachers = null;
        try (Connection con = getConnection()) {PreparedStatement pst = con.prepareStatement(select_teacher_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                teachers = fillteachers(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return teachers;
    }
    // Формирование списка всех должностей
    @Override
    public List<Teachers> findAll(){
        List<Teachers> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillteachers(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Teachers fillteachers(ResultSet rs) throws SQLException {
        Teachers teachers = new Teachers();
        teachers.setId(rs.getLong("teachers_id"));
        teachers.setIdChairs(rs.getLong("chair_id"));
        teachers.setIdPosts(rs.getLong("post_id"));
        teachers.setSecondName(rs.getString("second_name"));
        teachers.setFirstName(rs.getString("first_name"));
        teachers.setLastName(rs.getString("last_Name"));
        teachers.setPhone(rs.getString("phone"));
        teachers.setEmail(rs.getString("email"));
        return teachers;
    }
    // Поиск должности по id из списка должностей
    public Teachers FindById(Long id, List<Teachers> teachers) {
        if (teachers != null) {
            for (Teachers r : teachers) {
                if ((r.getId()).equals(id)) {
                    return r;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}

