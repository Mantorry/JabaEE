package com.example.demo2.Entities;

public class Teachers {
    private Long id, idChairs, idPosts;
    private String firstName, secondName, lastName, phone, email;
    private Chairs chairs;
    private Posts posts;

    public Teachers() {
    }

    public Teachers(Long id, Long idChairs, Long idPosts, String secondName, String firstName, String lastName, String phone, String email, Chairs chairs, Posts posts) {
        this.id = id;
        this.idChairs = idChairs;
        this.idPosts = idPosts;
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.chairs = chairs;
        this.posts = posts;
    }

    public Teachers(Long idChairs, Long idPosts, String firstName, String secondName, String lastName, String phone, String email) {
        this.idChairs = idChairs;
        this.idPosts = idPosts;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Teachers(String firstName, String secondName, String lastName, String phone, String email, Chairs chairs, Posts posts) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.chairs = chairs;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdChairs() {
        return idChairs;
    }

    public void setIdChairs(Long idChairs) {
        this.idChairs = idChairs;
    }

    public Long getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(Long idPosts) {
        this.idPosts = idPosts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Chairs getChairs() {
        return chairs;
    }

    public void setChairs(Chairs chairs) {
        this.chairs = chairs;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", idChairs=" + idChairs +
                ", idPosts=" + idPosts +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", chairs=" + chairs +
                ", posts=" + posts +
                '}';
    }
}
