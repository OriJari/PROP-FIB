package persistencia.preprocessat;

import dominio.clases.recommendation.Recommendation;

import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class RecommendationSave {

    Integer id_user;
    List<Integer> intsratings;
    List<Float> floatsratings;

    public RecommendationSave(){
        this.id_user = null;
        this.intsratings = new ArrayList<>();
        this.floatsratings = new ArrayList<>();
    }

    public Integer getId_user() {
        return id_user;
    }

    public List<Integer> getIntsratings() {
        return intsratings;
    }

    public List<Float> getFloatsratings() {
        return floatsratings;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setIntsratings(ArrayList<Integer> intsratings) {
        this.intsratings = intsratings;
    }

    public void setFloatsratings(ArrayList<Float> floatsratings) {
        this.floatsratings = floatsratings;
    }

    public static void saveRecommendation(int id_user, ArrayList<Integer> intsrating, ArrayList<Float> floatsrating){
        File archivo = new File("DATA/" + "Recommedation" + id_user + ".csv");
        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            out.write("IDItem" + "," + "Rating" + "\n");
            int size_ints = intsrating.size();
            int size_floats = floatsrating.size();
            for (int i = 0; i < Math.max(size_ints,size_floats); ++i){
                out.write( intsrating.get(i) + "," + floatsrating.get(i) + "\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void reloadRecommendation(int id_user1){
        FileInputStream fis;
        try {
            fis = new FileInputStream("DATA/" + "Recommedation" + id_user1 + ".csv");
            Scanner sc = new Scanner(fis);
            //For each line
            sc.nextLine();
            id_user = id_user1;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> splitContent = Arrays.asList(line.split(","));
                intsratings.add(Integer.valueOf(splitContent.get(0)));
                floatsratings.add(Float.valueOf(splitContent.get(1)));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        int user = 234;
        ArrayList<Integer> ints = new ArrayList<>(5);
        ArrayList<Float> floats = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i){
            int randomint = (int) Math.random();
            float randomfloat = (float) Math.random();
            ints.add(randomint);
            floats.add(randomfloat);
        }
        saveRecommendation(user, ints, floats);
        RecommendationSave r = new RecommendationSave();
        r.reloadRecommendation(user);
        out.println("hola");
    }*/
}
