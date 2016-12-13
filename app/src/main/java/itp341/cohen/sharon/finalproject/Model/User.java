package itp341.cohen.sharon.finalproject.Model;

import java.io.Serializable;

/**
 * Created by Sharon on 12/11/2016.
 */
public class User implements Serializable{

    private String email;

    public User(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }
}
