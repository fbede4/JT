package Dtos;

import java.io.Serializable;

/**
 * This object is used to send the login data to the server
 */
public class LoginDto implements Serializable {
    private String email;
    private String password;

    public  LoginDto(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }
}
