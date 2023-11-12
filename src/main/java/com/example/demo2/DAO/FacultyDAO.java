package com.example.demo2.DAO;

import com.example.demo2.Entities.Faculties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FacultyDAO implements RepositoryDAO<Faculties> {
    public FacultyDAO(){}

    private static final String select_all = "SELECT faculty_id, full_name, short_name FROM faculty";
    private static final String select_faculty_ById = "SELECT faculty_id, full_name, short_name FROM faculty WHERE faculty_id =?";
    private static final String insert_faculty = "INSERT INTO faculty(full_name, short_name) VALUES(?, ?)";
    private static final String edit_faculty = "UPDATE faculty SET full_name = ?, short_name = ? WHERE faculty_id = ? ";
    private static final String delete_faculty = "DELETE FROM faculty WHERE faculty_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Faculties faculty) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_faculty, new String[] { "id" })) {
            long Id = -1L;
            pst.setString(1, faculty.getFullName());
            pst.setString(2, faculty.getShortName());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("id");
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
    public void update(Faculties faculty) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_faculty)) {
            pst.setString(1, faculty.getFullName());
            pst.setString(2, faculty.getShortName());
            pst.setLong(3, faculty.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_faculty)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Faculties findById(Long Id) {
        Faculties faculties = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_faculty_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                faculties = fillfaculties(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return faculties;
    }
    // Формирование списка всех должностей
    @Override
    public List<Faculties> findAll(){
        List<Faculties> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillfaculties(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Faculties fillfaculties(ResultSet rs) throws SQLException {
        Faculties faculties = new Faculties();
        faculties.setId(rs.getLong("faculty_id"));
        faculties.setFullName(rs.getString("full_name"));
        faculties.setShortName(rs.getString("short_name"));
        return faculties;
    }
    // Поиск должности по id из списка должностей
    public Faculties FindById(Long id, List<Faculties> faculties) {
        if (faculties != null) {
            for (Faculties r : faculties) {
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

