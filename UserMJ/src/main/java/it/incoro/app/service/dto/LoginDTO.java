package it.incoro.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Login entity.
 */
public class LoginDTO implements Serializable {

   

    private String username;

    private String password;

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

   

    @Override
    public String toString() {
        return "LoginDTO{" +
           
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}
