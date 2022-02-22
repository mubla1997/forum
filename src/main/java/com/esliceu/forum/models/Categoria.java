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

    @OneToMany (mappedBy = "categoria")
    Set<Cuenta> moderators;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set <Cuenta> getModerators() {
        return moderators;
    }

    public void setModerators(Set <Cuenta> moderators) {
        this.moderators = moderators;
    }
}
