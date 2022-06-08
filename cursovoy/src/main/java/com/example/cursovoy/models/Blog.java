package com.example.cursovoy.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(schema = "spring", name="blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameblog;
    private String textmaterial;

    public Blog(String nameblog, String textmaterial) {
        this.nameblog = nameblog;
        this.textmaterial = textmaterial;
    }

    public Blog() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameblog() {
        return nameblog;
    }

    public void setNameblog(String nameblog) {
        this.nameblog = nameblog;
    }

    public String getTextmaterial() {
        return textmaterial;
    }

    public void setTextmaterial(String textmaterial) {
        this.textmaterial = textmaterial;
    }
}

