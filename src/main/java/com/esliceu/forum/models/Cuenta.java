package com.esliceu.forum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Table(name = "cuenta")
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
    byte[] avatar;

    @JsonIgnore
    @OneToMany
    Set <Topic> topics;

    @JsonIgnore
    @OneToMany
    Set <Reply> replies;

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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Set <Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set <Topic> topics) {
        this.topics = topics;
    }

    public Set <Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set <Reply> replies) {
        this.replies = replies;
    }

    public Map <String, Object> ObtainJson() {

        Map <String, Object> user = new HashMap <>();
        user.put("id", getId());
        user.put("email", getEmail());
        user.put("name", getName());
        user.put("avatar", getAvatar());

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
