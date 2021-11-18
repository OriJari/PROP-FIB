package user;

import java.util.*;
import java.io.*;

public class User{
    private String username;
    private Integer userID;
    private String mail;
    private String password;


    /**
     * Default builder
     * @param username name of the user account
     * @param userID identifier of the user
     * @param mail email of the user
     * @param password password of the user account
     */
    public User(String username, Integer userID, String mail, String password){
        this.username = username;
        this.userID = userID;
        this.mail = mail;
        this.password = password;
    }

    /**
     * Getter of the username
     * @return the name of the user account
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of the user identifier
     * @return the identification of the user account
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Getter of the mail
     * @return the email of the user account
     */
    public String getMail() {
        return mail;
    }

    /**
     * Getter of the password
     * @return the password of the user account
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the username
     * @param username to change into the account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter of the userID
     * @param userID to change into the account
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Setter of the mail
     * @param mail to change into the account
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Setter of the password
     * @param password to change into the account
     */
    public void setPassword(String password) {
        this.password = password;
    }
}