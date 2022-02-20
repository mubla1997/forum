package com.esliceu.forum.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String passwd;
    @NotNull
    private String rol;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}