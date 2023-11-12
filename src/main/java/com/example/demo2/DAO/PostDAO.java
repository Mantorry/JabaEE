package com.example.demo2.DAO;

import com.example.demo2.Entities.Posts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostDAO implements RepositoryDAO<Posts> {
    public PostDAO(){}

    private static final String select_all = "SELECT posts_id, name_post FROM posts";
    private static final String select_post_ById = "SELECT posts_id, name_post FROM posts WHERE posts_id =?";
    private static final String insert_post = "INSERT INTO posts(name_post) VALUES(?)";
    private static final String edit_post = "UPDATE posts SET name_post = ? WHERE posts_id = ? ";
    private static final String delete_post = "DELETE FROM posts WHERE posts_id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    // Добавление новой должности
    @Override
    public Long insert (Posts posts) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(insert_post, new String[] { "posts_id" })) {
            long Id = -1L;
            pst.setString(1, posts.getNamePost());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                Id = gk.getLong("posts_id");
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
    public void update(Posts posts) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(edit_post)) {
            pst.setString(1, posts.getNamePost());
            pst.setLong(2, posts.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Удаление должности
    @Override
    public void delete(Long Id) {
        try (Connection con = getConnection(); PreparedStatement pst
                = con.prepareStatement(delete_post)) {
            pst.setLong(1, Id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Поиск должности по Id
    @Override
    public Posts findById(Long Id) {
        Posts posts = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst =
                    con.prepareStatement(select_post_ById);
            pst.setLong(1, Id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                posts = fillposts(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return posts;
    }
    // Формирование списка всех должностей
    @Override
    public List<Posts> findAll(){
        List<Posts> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst =
                     con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillposts(rs));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    // Формирование класса Должности по результатам запроса к БД
    private Posts fillposts(ResultSet rs) throws SQLException {
        Posts posts = new Posts();
        posts.setId(rs.getLong("posts_id"));
        posts.setNamePost(rs.getString("name_post"));
        return posts;
    }
    // Поиск должности по id из списка должностей
    public Posts FindById(Long id, List<Posts> posts) {
        if (posts != null) {
            for (Posts r : posts) {
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

