package com.example.demo2.Entities;

public class Posts {
    private Long id;
    private String namePost;

    public Posts() {
    }

    public Posts(Long id, String namePost) {
        this.id = id;
        this.namePost = namePost;
    }

    public Posts(String namePost) {
        this.namePost = namePost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", namePost='" + namePost + '\'' +
                '}';
    }
}
