package com.example.demo2;

public class Faculties {
    private Long id;
    private String FullName, ShortName;

    public Faculties() {
    }

    public Faculties(Long id, String fullName, String shortName) {
        this.id = id;
        FullName = fullName;
        ShortName = shortName;
    }

    public Faculties(String fullName, String shortName) {
        FullName = fullName;
        ShortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    @Override
    public String toString() {
        return "Faculties{" +
                "id=" + id +
                ", FullName='" + FullName + '\'' +
                ", ShortName='" + ShortName + '\'' +
                '}';
    }
}
