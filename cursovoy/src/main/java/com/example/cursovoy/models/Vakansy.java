package com.example.cursovoy.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "spring", name="vakansy")
public class Vakansy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Наименование не может быть пустым")
    private String namevakansy;
    @NotEmpty(message = "Наименование компании не может быть пустым")
    private String company;
    @Min(value = 10000, message = "Сумма должна быть не меньше 10000 рублей")
    private Integer salary;
    @NotEmpty(message = "Опыт работы не может быть пустым")
    private String experience;
    @NotEmpty(message = "Занятость не может быть пустым")
    private String busyness;
    @NotEmpty(message = "Навыки не может быть пустым")
    private String skills;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamevakansy() {
        return namevakansy;
    }

    public void setNamevakansy(String namevakansy) {
        this.namevakansy = namevakansy;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }



    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getBusyness() {
        return busyness;
    }

    public void setBusyness(String busyness) {
        this.busyness = busyness;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Vakansy(String namevakansy, String company, Integer salary, String experience, String busyness, String skills) {
        this.namevakansy = namevakansy;
        this.company = company;
        this.salary = salary;
        this.experience = experience;
        this.busyness = busyness;
        this.skills = skills;
    }

    public Vakansy() {
    }

}
