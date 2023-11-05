package com.example.demo2.Entities;

public class Chairs {
    private Long id, idFaculty;
    private String fullName, shortName;
    private Faculties faculties;



    public Chairs() {
    }

    public Chairs(Long id, Long idFaculty, String fullName, String shortName, Faculties faculties) {
        this.id = id;
        this.idFaculty = idFaculty;
        this.fullName = fullName;
        this.shortName = shortName;
        this.faculties = faculties;
    }

    public Chairs(Long idFaculty, String fullName, String shortName) {
        this.idFaculty = idFaculty;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public Chairs(String fullName, String shortName, Faculties faculties) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.faculties = faculties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Long idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Faculties getFaculties() {
        return faculties;
    }

    public void setFaculties(Faculties faculties) {
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        return "Chairs{" +
                "id=" + id +
                ", idFaculty=" + idFaculty +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", faculties=" + faculties +
                '}';
    }
}
