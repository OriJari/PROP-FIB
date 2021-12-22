package persistencia.preprocessat;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
/**
 *
 * @author Miguel Gutierrez Jariod
 */
public class RecommendationSave {

    private List<Integer> id_user;
    private List<List<Integer>> idItems;
    private List<List<Float>> values;
    private List<Integer> algorithm;
    private List<String> dates;

    public RecommendationSave(){
        this.id_user = new ArrayList<>();
        this.idItems = new ArrayList<>();
        this.values = new ArrayList<>();
        this.algorithm = new ArrayList<>();
        this.dates = new ArrayList<>();
    }

    public List<Integer> getId_user() {
        return id_user;
    }

    public List<List<Integer>> getIdItems() {
        return idItems;
    }

    public List<List<Float>> getValues() {
        return values;
    }

    public List<Integer> getAlgorithm() {
        return algorithm;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setId_user(List<Integer> id_user) {
        this.id_user = id_user;
    }

    public void setIdItems(List<List<Integer>> idItems) {
        this.idItems = idItems;
    }

    public void setValues(List<List<Float>> values) {
        this.values = values;
    }

    public void setAlgorithm(List<Integer> algorithm) {
        this.algorithm = algorithm;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public void addUserL (Integer user){
        this.id_user.add(user);
    }

    public void addlistiditem(List<Integer> iditems){
        this.idItems.add(iditems);
    }

    public void addlistvalues(List<Float> val){
        this.values.add(val);
    }

    public void addalgorithmlist(Integer i){
        this.algorithm.add(i);
    }

    public void adddatalist(String data){
        dates.add(data);
    }


    public String datasystem(Date data){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateToStr = dateFormat.format(date);
        return dateToStr;
    }

    public String selectalgorithm(Integer i){
        if (i.equals(0)) return "KNN";
        else if (i.equals(1)) return "CollaborativeF";
        else return "Hybrid";
    }


    public  void saveRecommendation(){
        File archivo = new File("DATA/" + "Recommedation" + ".csv");
        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            Date data = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateToStr = dateFormat.format(data);
            String dat = datasystem(data);
            for (int i = 0; i < id_user.size(); ++i){
                out.write("USERID:" + String.valueOf(id_user.get(i)) + "," + dateToStr + "," + "ALGORITHM:" + selectalgorithm(algorithm.get(i)) + "\n");
                List<Integer> li = idItems.get(i);
                List<Float> lf = values.get(i);
                out.write("ITEMS:" +  "," + "RATES:" + "\n");
                for(int j = 0; j < li.size() && j < lf.size(); ++j){
                    out.write( String.valueOf(li.get(j)) + "," + String.valueOf(lf.get(j)) + "\n");
                }
                out.write( "/--/" + "\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void reloadRecommendation(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(path + "Recommedation" + ".csv");
            Scanner sc = new Scanner(fis);
            //For each line
            List<Integer> idIt = new ArrayList<>();
            List<Float> val = new ArrayList<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                boolean header = line.contains("USERID");
                boolean patro = line.contains("/--/");
                if (patro){
                    idItems.add(idIt);
                    values.add(val);
                    idIt = new ArrayList<>();
                    val = new ArrayList<>();
                }
                if (line.contains("ITEMS:")) line = sc.nextLine();
                List<String> splitContent = Arrays.asList(line.split(","));
                if (header){
                    String s = splitContent.get(0);
                    String n = "";
                    char[] cadena = s.toCharArray();
                    for (int j = 0; j < cadena.length; ++j){
                        if (Character.isDigit(cadena[j])) n += cadena[j];
                    }
                    id_user.add(parseInt(n));

                    String s1 = splitContent.get(1);
                    dates.add(s1);

                    String s2 = splitContent.get(2);
                    if (s2.contains("KNN")) algorithm.add(0);
                    else if (s2.contains("Colla")) algorithm.add(1);
                    else algorithm.add(2);

                    //line = sc.nextLine();
                }
                else if(!header && !patro){
                    idIt.add(parseInt(splitContent.get(0)));
                    val.add(parseFloat(splitContent.get(1)));
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) {
        RecommendationSave r = new RecommendationSave();
        int user = 234;
        int user1 = 123;
        int user2 = 567;
        r.addUserL(user);
        r.addUserL(user1);
        r.addUserL(user2);

        List<Integer> ints = new ArrayList<>(3);
        List<Integer> ints1 = new ArrayList<>(3);
        List<Integer> ints2 = new ArrayList<>(3);

        ints.add(1);
        ints.add(2);
        ints.add(3);

        ints1.add(7);
        ints1.add(5);
        ints1.add(9);

        ints2.add(8);
        ints2.add(6);
        ints2.add(4);

        r.addlistiditem(ints);
        r.addlistiditem(ints1);
        r.addlistiditem(ints2);

        List<Float> floats = new ArrayList<>(3);
        List<Float> floats1 = new ArrayList<>(3);
        List<Float> floats2 = new ArrayList<>(3);

        floats.add(5.0F);
        floats.add(3.0F);
        floats.add(6.0F);

        floats1.add(9.0F);
        floats1.add(1.0F);
        floats1.add(3.5F);

        floats2.add(2.5F);
        floats2.add(5.5F);
        floats2.add(0.0F);

        r.addlistvalues(floats);
        r.addlistvalues(floats1);
        r.addlistvalues(floats2);

        r.addalgorithmlist(0);
        r.addalgorithmlist(1);
        r.addalgorithmlist(2);

        r.adddatalist("18-12-2021");
        r.adddatalist("19-12-2021");
        r.adddatalist("20-12-2021");

        r.saveRecommendation();
        out.println("hola");
        RecommendationSave s = new RecommendationSave();
        s.reloadRecommendation("DATA/");
        out.println("adios");
    }*/
}
