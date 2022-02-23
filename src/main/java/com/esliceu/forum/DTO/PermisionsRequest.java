package com.esliceu.forum.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PermisionsRequest {
    @NotNull
    private String email;
    @NotNull
    private int id;
    @NotNull
    private String avatar;
    @NotNull
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
