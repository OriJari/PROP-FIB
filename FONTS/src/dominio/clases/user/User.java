package dominio.clases.user;

/**
 * @class User
 * @brief Structure of the User (needed in a near future)
 * @author Miguel
 */

public class User{
    private String username;
    private Integer userID;
    private String mail;
    private String password;


    /**
     * @brief Default builder
     * @param username name of the dominio.controladores.clases.atribut.user account
     * @param userID identifier of the dominio.controladores.clases.atribut.user
     * @param mail email of the dominio.controladores.clases.atribut.user
     * @param password password of the dominio.controladores.clases.atribut.user account
     */
    public User(String username, Integer userID, String mail, String password){
        this.username = username;
        this.userID = userID;
        this.mail = mail;
        this.password = password;
    }

    /**
     * @brief Getter of the username
     * @return the name of the dominio.controladores.clases.atribut.user account
     */
    public String getUsername() {
        return username;
    }

    /**
     * @brief Getter of the dominio.controladores.clases.atribut.user identifier
     * @return the identification of the dominio.controladores.clases.atribut.user account
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @brief Getter of the mail
     * @return the email of the dominio.controladores.clases.atribut.user account
     */
    public String getMail() {
        return mail;
    }

    /**
     * @brief Getter of the password
     * @return the password of the dominio.controladores.clases.atribut.user account
     */
    public String getPassword() {
        return password;
    }

    /**
     * @brief Setter of the username
     * @param username to change into the account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @brief Setter of the userID
     * @param userID to change into the account
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * @brief Setter of the mail
     * @param mail to change into the account
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @brief Setter of the password
     * @param password to change into the account
     */
    public void setPassword(String password) {
        this.password = password;
    }
}