package com.example.cursovoy.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "spring", name="resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "ФИО не может быть пустым")
    private String fio;
    @NotEmpty(message = "Должность не может быть пустым")
    private String post;
    @Min(value = 10000, message = "Сумма должна быть не меньше 10000 рублей")
    private Integer dessalary;
    private String schedule;
    @NotEmpty(message = "Город не может быть пустым")
    private String city;
    @NotEmpty(message = "Наименование учебного заведения не может быть пустым")
    private String nameinstitution;
    @NotEmpty(message = "Поле специальность не может быть пустым")
    private String speciality;
    @NotEmpty(message = "Опыт работы не может быть пустым")
    private String workexperience;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getDessalary() {
        return dessalary;
    }

    public void setDessalary(Integer dessalary) {
        this.dessalary = dessalary;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNameinstitution() {
        return nameinstitution;
    }

    public void setNameinstitution(String nameinstitution) {
        this.nameinstitution = nameinstitution;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWorkexperience() {
        return workexperience;
    }

    public void setWorkexperience(String workexperience) {
        this.workexperience = workexperience;
    }
    public Resume(String fio, String post, Integer dessalary, String schedule, String city,
                  String nameinstitution, String speciality, String workexperience) {
        this.fio = fio;
        this.post = post;
        this.dessalary = dessalary;
        this.schedule = schedule;
        this.city = city;
        this.nameinstitution = nameinstitution;
        this.speciality = speciality;
        this.workexperience = workexperience;
        //this.filename = filename;
    }
    public Resume() {
    }
}
