package user;

import java.util.*;
import java.io.*;

public class User{
    private String username;
    private Integer userID;
    private String mail;
    private String password;

    public User(String username, Integer userID, String mail, String password){
        this.username = username;
        this.userID = userID;
        this.mail = mail;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}