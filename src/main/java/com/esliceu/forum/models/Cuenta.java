package com.esliceu.forum.models;

import javax.persistence.*;
import java.util.*;

@Table(name = "Cuenta")
@Entity
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "email")
    String email;
    @Column(name = "name")
    String name;
    @Column(name = "password")
    String password;
    @Column(name = "role")
    String role;
    @Column(name = "avatar")
    String avatar;

    public enum ROLE{ User,Moderator,admin}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Map <String,Object> ObtainJson() {

        Map <String, Object> user = new HashMap <>();
        user.put("avatar", getAvatar());
        user.put("email", getEmail());
        user.put("id", getId());
        user.put("name", getName());

        Map <String, Object> permissions = new HashMap <>();
        List <String> root = new ArrayList <>();
        root.add("categories:write");
        root.add("categories:delete");
        root.add("own_topics:write");
        root.add("own_topics:delete");
        root.add("own_replies:write");
        root.add("own_replies:delete");
        permissions.put("root", root);

        user.put("permissions", permissions);

        return user;
    }
}
