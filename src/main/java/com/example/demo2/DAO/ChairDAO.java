package com.example.demo2.DAO;

import com.example.demo2.Entities.Chairs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChairDAO implements RepositoryDAO<Chairs> {
    public ChairDAO(){}

    private static final String select_all = "SELECT chair_id, faculty_id, full_name, short_name FROM chair";
    private static final String select_chair_ById = "SELECT chair_id, faculty_id, full_name, short_name FROM chair WHERE chair_id = ?";
    private static final String insert_chair = "INSERT INTO chair(faculty_id, full_name, short_name) VALUES(?, ?, ?)";
    private static final String edit_chair = "UPDATE chair SET facult_id = ?, full_name = ?, short_name = ? WHERE chair_id = ? ";
    private static final String delete_chair = "DELETE FROM chair WHERE chair_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Chairs chairs) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_chair, new String[] { "id" })) {
            long Id = -1L;
            pst.setLong(1, chairs.getIdFaculty());
            pst.setString(2, chairs.getFullName());
            pst.setString(3, chairs.getShortName());
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
    public void update(Chairs chairs) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_chair)) {
            pst.setLong(1, chairs.getIdFaculty());
            pst.setString(2, chairs.getFullName());
            pst.setString(3, chairs.getShortName());
            pst.setLong(4, chairs.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_chair)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Chairs findById(Long Id) {
        Chairs chairs = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_chair_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                chairs = fillchairs(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return chairs;
    }
    // Формирование списка всех должностей
    @Override
    public List<Chairs> findAll(){
        List<Chairs> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillchairs(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Chairs fillchairs(ResultSet rs) throws SQLException {
        Chairs chairs = new Chairs();
        chairs.setId(rs.getLong("chair_id"));
        chairs.setIdFaculty(rs.getLong("faculty_id"));
        chairs.setFullName(rs.getString("full_name"));
        chairs.setShortName(rs.getString("short_name"));
        return chairs;
    }
    // Поиск должности по id из списка должностей
    public Chairs FindById(Long id, List<Chairs> chairs) {
        if (chairs != null) {
            for (Chairs r : chairs) {
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

