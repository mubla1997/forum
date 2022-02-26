package com.esliceu.forum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "Topic")
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "views")
    int views;

    @Column(name = "createdAt")
    Date createdAt;

    @ManyToOne
    Cuenta user;

    @Column(name = "category_id",insertable = false,updatable = false)
    int categoryId;

    @ManyToOne
    Categoria categoria;

}
