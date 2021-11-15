package user;

public class User{
    String username;
    Integer userID;
    String mail;
    String password;

    /**
     * Default builder.
     */
    public User(){
        this.username = null;
        this.userID = null;
        this.mail = null;
        this.password = null;
    }

    /**
     * Getter of the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Getter of the getMail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Getter of the Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter of the userID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Setter of the setMail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Setter of the Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}