package drivers;


import user.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class DriverUser {
    public static void options() {
        System.out.println("Choose an option with the necessary parameters needed:");
        System.out.println("\t 0) exit");
        System.out.println("\t 1) User(String username, Integer userID, String mail, String password)");
        System.out.println("\t 2) getUsername()");
        System.out.println("\t 3) getUserID()");
        System.out.println("\t 4) getMail()");
        System.out.println("\t 5) getPassword()");
        System.out.println("\t 6) setUsername(String username) ");
        System.out.println("\t 7) setUserID(Integer userID)");
        System.out.println("\t 8) setMail(String mail)");
        System.out.println("\t 9) setPassword(String password) ");
    }

    public static void main(String[] args) {
        System.out.println("Driver User class:");
        User user = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean finish = false;

            options();
            while (!finish) {

                String line;
                String[] param;
                String op;

                line = br.readLine();

                param = line.split(" ");
                op = param[0];
                try {
                    switch (op) {
                        case "0" : finish = true;
                            break;
                        case "1" :
                            user = new User(param[1], parseInt(param[2]), param[3], param[4]);
                            System.out.println("User created");
                            break;
                        case "2" :
                            System.out.println(user.getUsername());
                            break;
                        case "3" :
                            System.out.println(user.getUserID());
                            break;
                        case "4" :
                            System.out.println(user.getMail());
                            break;
                        case "5":
                            System.out.println(user.getPassword());
                            break;
                        case "6":
                            user.setUsername(param[1]);
                            System.out.println("Username changed");
                            break;
                        case "7":
                            user.setUserID(parseInt(param[1]));
                            System.out.println("User identifier changed");
                            break;
                        case "8":
                            user.setMail(param[1]);
                            System.out.println("Mail changed");
                            break;
                        case "9":
                            user.setPassword(param[1]);
                            System.out.println("Password changed");
                            break;
                        default : System.out.println("Choose another option");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}