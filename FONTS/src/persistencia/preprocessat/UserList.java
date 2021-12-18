package persistencia.preprocessat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.System.out;

public class UserList {
    Set<Integer> users;
    Map<Integer, Map<Integer, Float>> mapRate;

    public UserList(){
        this.users = new TreeSet<>();
        this.mapRate = new TreeMap<>();
    }

    public Set<Integer> getUsers() {
        return users;
    }

    public Map<Integer, Map<Integer, Float>> getMapRate() {
        return mapRate;
    }

    public void setUsers(Set<Integer> users) {
        this.users = users;
    }

    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
    }

    public void initializeUsers( Map<Integer, Map<Integer, Float>> mapRate){
        for (Map.Entry<Integer, Map<Integer, Float>> entry : mapRate.entrySet()) {
            Integer first = entry.getKey();
            users.add(first);
        }
    }

    public void addUser(int id_user){
        if (!users.contains(id_user)) users.add(id_user);
    }

    public void delUser(int id_user){
        if (users.contains(id_user)){
            users.remove(id_user);
            if (mapRate.containsKey(id_user)) mapRate.remove(id_user);
        }
    }

    public void saveUsers(){
        File archivo = new File("DATA/" + "UserList" + ".csv");
        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            out.write("List of Users" + "\n");
            while (!users.isEmpty()){
                Iterator<Integer> it = users.iterator();
                String salida = String.valueOf(it.next());
                out.write(salida + "\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) {

    }
}
