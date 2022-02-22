package com.esliceu.forum.models;

import javax.persistence.*;
import java.util.Set;

@Table(name = "Categoria")
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "slug")
    String slug;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description;
    @Column(name = "column")
    String color;

    //@OneToMany (mappedBy = "categoria")
    //Set<Cuenta> moderators;

}
