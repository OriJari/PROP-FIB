package dominio.clases.preprocessat;

import persistencia.preprocessat.UserList;

import java.io.*;
import java.util.*;

import static java.lang.System.out;

/**
 * @class CSVparserRate
 * @brief Implements the lecture and the proces of data set of the csv
 * @author Miguel
 */

public class CSVparserRate {

    private Integer numCols, numRows;
    private String path;
    private List<List<String>> content;
    private List<String> header;
    private Map<Integer, Map<Integer, Float>> mapRate;

    /**
     * @brief Default builder.
     * @param path where is located the csv document
     * \pre the path of the file needs to be existent and the document csv needs to be from type ratings.csv
     *
     * \post It creates a <em>CSVparserRate</em> object with its attribute <em>path</em> with the values:
     *  <em>numCols</em>  initialized as 0, <em>numRows</em>  initialized as 0, <em>dominio.controladores.clases.atribut.content</em> empty, <em>mapRate</em> empty and
     *   <em>header</em> empty.
     */
    public CSVparserRate(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        this.content = new ArrayList<>();
        this.mapRate = new TreeMap<>();
        this.header = new ArrayList<>();
    }

    /**
     * @brief Getter of the num rows
     * \pre the csv document needs to be created and read.
     * \post obtain the number of the rows of the dominio.controladores.clases.atribut.content attribute.
     * @return the number of rows of the csv
     */
    public Integer getNumRows() {
        return numRows;
    }

    /**
     * @brief Getter of the num columns
     * \pre the csv document needs to be created and read.
     * \post obtain the number of the columns of the dominio.controladores.clases.atribut.content attribute.
     * @return the number of columns of the csv
     */
    public Integer getNumCols() {
        return numCols;
    }

    /**
     * @brief Getter of the header
     * \pre the csv document needs to be created and read.
     * \post obtain the list of the header in the csv.
     * @return the header of the csv as a List
     */
    public List<String> getHeader() {
        return header;
    }

    /**
     * @brief Getter of the path of the location of the csv
     * \pre needs to be a existent path.
     * \post obtain the path where is located the csv.
     * @return the path as String
     */
    public String getPath() {
        return path;
    }

    /**
     * @brief Getter of the class, gets the dominio.controladores.clases.atribut.content
     * \pre the document needs to be read to obtain the dominio.controladores.clases.atribut.content
     * \post obtain the lecture of the csv ready to manipulate
     * @return the dominio.controladores.clases.atribut.content value
     */
    public List<List<String>> getContent() {
        return content;
    }

    /**
     * @brief Getter of the class, gets the mapRate
     * \pre the document needs to be read to obtain the dominio.controladores.clases.atribut.content
     * \post obtain the data set manipulated to make easy the operation in algorithms
     * @return set of data of the ratings dominio.controladores.clases.atribut.content manipulated
     */
    public Map<Integer, Map<Integer, Float>> getMapRate() {
        return mapRate;
    }

    /**
     * @brief Setter of the class, modified the number of rows
     * \pre true
     * \post modify the attribute numRows of the class
     * @param numRows, new size of the number of rows
     */
    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    /**
     * @brief Setter of the class, modified the number of columns
     * \pre true
     * \post modify the attribute numCols of the class
     * @param numCols, new size of the number of columns
     */
    public void setNumCols(Integer numCols) {
        this.numCols = numCols;
    }

    /**
     * @brief Setter of the class, modified the elements of the header
     * \pre true
     * \post modify the attribute header of the class
     * @param header, header of the csv as a list
     */
    public void setHeader(List<String> header) {
        this.header = header;
    }

    /**
     * @brief Setter of the class, modified the path where is located ths csv
     * \pre the path needs to be corresponded with the location file
     * \post modify the attribute path, changing to a new document.
     * @param path, where the document is located, as string
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @brief Setter of the class, gets the dominio.controladores.clases.atribut.content
     * \pre true
     * \post modify the attribute dominio.controladores.clases.atribut.content, changing the lecture.
     * @param content, lecture of the csv document to attribute
     */
    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    /**
     * @brief Setter of the class, sets a new mapRate
     * \pre true
     * \post modify the attribute mapRate, changing the dominio.controladores.clases.atribut.content of it.
     * @param mapRate , map to redefine the new one
     */
    public void setMapRate(Map<Integer, Map<Integer, Float>> mapRate) {
        this.mapRate = mapRate;
    }


    /**
     * @brief Get the header of the csv.
     * \pre needs a previous lecture of the csv
     * \post obtain the set of ids of the document.
     * @param path where the document csv is located
     */
    public void obtainHeader(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            String h = sc.nextLine();
            header = Arrays.asList(h.split(","));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Read the dominio.controladores.clases.atribut.content of the rates csvs into memory.
     * \pre needs a document csv to read
     * \post obtains the lecture of the document manipulated into dominio.controladores.clases.atribut.content.
     */
    public void readLoadRate(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            obtainHeader(this.path);
            sc.nextLine();  // without header
            //For each line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                //[:space]
                List<String> splitContent = Arrays.asList(line.split(","));
                content.add(splitContent);
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Read the preproces dataset of ratings.
     * @param path "PENDIENTE A MODIFICAR"
     * \pre needs a document csv to read
     * \post obtains the preprocess data into the pertinent map.
     */
    public void reload_map_preporcess(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            obtainHeader(path);
            sc.nextLine();  // without header
            Map<Integer, Map<Integer, Float>> m = new TreeMap<>();
            //For each line
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                //[:space]
                List<String> splitContent = Arrays.asList(line.split(","));
                int userID = Integer.parseInt(splitContent.get(0));
                int itemID = Integer.parseInt(splitContent.get(1));
                float rateID = Float.parseFloat(splitContent.get(2));
                if(m.containsKey(userID)){
                    m.get(userID).put(itemID,rateID);
                }
                else{
                    Map<Integer, Float> aux = new TreeMap<>();
                    aux.put(itemID, rateID);
                    m.put(userID, aux);
                }
            }
            this.mapRate = m;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Change a String value to an Integer one
     * \pre true
     * \post change the value of the string to a integer.
     * @param s String to obtain the corresponding value
     * @return string s converted to integer
     */
    public Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }

    /**
     * @brief Change a String value to a Float one
     * \pre true
     * \post change the value of the string to a float.
     * @param s String to obtain the corresponding value
     * @return string s converted to float
     */
    public Float String_to_Float(String s){
        return Float.parseFloat(s);
    }

    /**
     * @brief Change List<List<String>> to a Map<Integer, Map<Integer,Float>> structure
     * \pre needs to have a dominio.controladores.clases.atribut.content of the lecture
     * \post change the dominio.controladores.clases.atribut.content to a estructure where the data is processed
     * @param rate_content dominio.controladores.clases.atribut.content of the csv document
     */
    public void LoadRate(List<List<String>> rate_content){
        //for each line take UserID, ItemID and Rate and add to the mapRate
        for (List<String> strings : rate_content) {
            Integer userID = String_to_Int(strings.get(header.indexOf("userId")));
            Integer itemID = String_to_Int(strings.get(header.indexOf("itemId")));
            Float rateID = String_to_Float(strings.get(header.indexOf("rating")));
            if(mapRate.containsKey(userID)){
                mapRate.get(userID).put(itemID,rateID);
            }
            else{
                Map<Integer, Float> map = new TreeMap<>();
                map.put(itemID, rateID);
                mapRate.put(userID, map);
            }
        }
    }

    /**
     * @brief Lecture of the List<List<String>> rows
     * \pre needs to have a dominio.controladores.clases.atribut.content of the lecture
     * \post obtain a row of the dominio.controladores.clases.atribut.content read.
     * @param i number of the row to obtain
     */
    public String getRow(int i) {
        return String.valueOf(this.content.get(i));
    }

    public void guardar_datos_preproces(Map<Integer, Map<Integer, Float>> mapRate, String namefile){
        //Scanner sc = new Scanner(System.in);
        //String nuevoFichero = sc.nextLine();
        File archivo = new File("FONTS/src/persistencia/" + namefile + ".csv");

        try{
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            out.write("userId" + "," + "itemId" + "," + "rating" + "\n");
            for (Map.Entry<Integer, Map<Integer, Float>> entry : mapRate.entrySet()) {
                Integer first = entry.getKey();
                Map<Integer, Float> m = entry.getValue();
                for (Map.Entry<Integer, Float> entry1 : m.entrySet()){
                        out.write( String.valueOf(first) + "," + String.valueOf(entry1.getKey()) + "," + String.valueOf(entry1.getValue()) + "\n");
                }
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void guardar_datos(List<List<String>> content, List<String> header, String namefile) {

        //Scanner sc = new Scanner(System.in);
        //String nuevoFichero = sc.nextLine();
        File archivo = new File("FONTS/src/persistencia/" + namefile + ".csv");

        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            out.write(header.get(0) + "," + header.get(1) + "," + header.get(2) + "\n");
            for (int i = 0; i < content.size(); ++i) {
                List<String> l = content.get(i);
                boolean p = true;
                for (String s : l) {
                    if (p) {
                        out.write(s);
                        p = false;
                    } else out.write("," + s);
                }
                out.write("\n");
            }
            out.write("\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
    /*public  boolean addUser(int ID) {

        //CREO QUE NO TIENE SENTIDO AÑADIR UN USER SIN UNA VALORACION AL DATASET
        //SOLO DEBERIAMOS AÑADIRLO A LA LISTA USERS

        //QUE CSVS A AÑADIR USER?
        /*Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        if(auxK.containsKey(ID)) return false;
        else{
            Map<Integer, Float> m = new TreeMap<>();
            m.put(0, 0.0F);
            auxK.put(ID, m);
            CSVKnown.setMapRate(auxK);

            List<List<String>> l = CSVKnown.getContent();
            int row = CSVKnown.getNumRows();
            row += 1;
            CSVKnown.setNumRows(row);
            List<String> aux = new ArrayList<>();
            List<String> head = CSVKnown.getHeader();
            int posuser = head.indexOf("userId");
            int positem = head.indexOf("itemId");
            int posrate = head.indexOf("rating");
            if (posuser < positem && posuser < posrate){
                aux.add(String.valueOf(ID));
                if(positem < posrate) {
                    aux.add("0");
                    aux.add("0.0");
                }
                else{
                    aux.add("0.0");
                    aux.add("0");
                }
            } else if (positem < posuser && positem < posrate) {
                aux.add("0");
                if(posuser < posrate){
                    aux.add(String.valueOf(ID));
                    aux.add("0.0");
                }else{
                    aux.add("0.0");
                    aux.add(String.valueOf(ID));
                }
            }
            else if (posrate < posuser && posrate < positem){
                aux.add("0.0");
                if(posuser < positem){
                    aux.add(String.valueOf(ID));
                    aux.add("0");
                }
                else{
                    aux.add("0");
                    aux.add(String.valueOf(ID));
                }
            }
            l.add(aux);
            CSVKnown.setContent(l);
        }
        return true;
    }*/

    public boolean delUserCSV(int ID) {
        //Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        if(!mapRate.containsKey(ID)) return false;
        else{
            mapRate.remove(ID);
            //List<String> head = CSVKnown.getHeader();
            int pos = header.indexOf("userId");
            //List<List<String>> l = CSVKnown.getContent();
            List<Integer> pos_delete = new ArrayList<>();
            //int rows = CSVKnown.getNumRows();
            for (int i = content.size()-1 ; i >= 0; --i){
                List<String> aux = content.get(i);
                if (aux.get(pos).equals(String.valueOf(ID))) {
                    content.remove(i);
                    numRows -= 1;
                    //CSVKnown.setNumRows(rows);
                }
            }
            //CSVKnown.setContent(l);
        }
        return true;
    }

    public boolean addRatingCSV(int IDuser, int IDitem, float valor){
        //QUE CSVS A AÑADIR RATING?
        //VALOR DENTRO DE LOS PARAMETROS MIN Y MAX
        //Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        //Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        Map<Integer, Float> m = new TreeMap<>();
        m.put(IDitem, valor);
        //NO PUEDE VALORAR UN MISMO ITEM CON UNA MISMA RATE
        if(mapRate.containsValue(m)) return false;
        else{
            //PREPROCESADO
            if(mapRate.containsKey(IDuser)){
                mapRate.get(IDuser).put(IDitem,valor);
            }
            else{
                Map<Integer, Float> aux = new TreeMap<>();
                aux.put(IDitem, valor);
                mapRate.put(IDuser, aux);
            }

            //CONTENT
            //CSVKnown.setMapRate(auxK);
            //List<List<String>> l = CSVKnown.getContent();
            List<String> aux = new ArrayList<>();
            //List<String> head = CSVKnown.getHeader();
            int posuser = header.indexOf("userId");
            int positem = header.indexOf("itemId");
            int posrate = header.indexOf("rating");
            if (posuser < positem && posuser < posrate){
                aux.add(String.valueOf(IDuser));
                if(positem < posrate) {
                    aux.add(String.valueOf(IDitem));
                    aux.add(String.valueOf(valor));
                }
                else{
                    aux.add(String.valueOf(valor));
                    aux.add(String.valueOf(IDitem));
                }
            } else if (positem < posuser && positem < posrate) {
                aux.add(String.valueOf(IDitem));
                if(posuser < posrate){
                    aux.add(String.valueOf(IDuser));
                    aux.add(String.valueOf(valor));
                }else{
                    aux.add(String.valueOf(valor));
                    aux.add(String.valueOf(IDuser));
                }
            }
            else if (posrate < posuser && posrate < positem){
                aux.add(String.valueOf(valor));
                if(posuser < positem){
                    aux.add(String.valueOf(IDuser));
                    aux.add(String.valueOf(IDitem));
                }
                else{
                    aux.add(String.valueOf(IDitem));
                    aux.add(String.valueOf(IDuser));
                }
            }
            content.add(aux);
            //int row = CSVKnown.getNumRows();
            numRows += 1;
            //CSVKnown.setNumRows(row);
            //CSVKnown.setContent(l);
        }
        return true;
    }

    public boolean modRatingCSV(int IDuser, int IDitem, float new_rate) {
        //QUE CSVS A MODIFICAR RATING?
        //VALOR DENTRO DE LOS PARAMETROS MIN Y MAX
        //Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = mapRate;
        if(auxK.containsKey(IDuser)){
            Map<Integer, Float> m = auxK.get(IDuser);
            if(m.containsKey(IDitem)) {
                m.remove(IDitem);
                m.put(IDitem, new_rate);
                setMapRate(auxK);

                //List<List<String>> l = CSVKnown.getContent();
                List<String> aux = new ArrayList<>();
                //List<String> head = CSVKnown.getHeader();
                int pos = -1;
                for (int i = 0; i < content.size(); ++i){
                    List<String> miro = content.get(i);
                    int posu = header.indexOf("userId");
                    int posi = header.indexOf("itemId");
                    if (miro.get(posu).equals(String.valueOf(IDuser))) {
                        if (miro.get(posi).equals(String.valueOf(IDitem))) {
                            pos = i;
                        }
                    }
                }
                if (pos != -1) {
                    content.remove(pos);
                    int posuser = header.indexOf("userId");
                    int positem = header.indexOf("itemId");
                    int posrate = header.indexOf("rating");
                    if (posuser < positem && posuser < posrate) {
                        aux.add(String.valueOf(IDuser));
                        if (positem < posrate) {
                            aux.add(String.valueOf(IDitem));
                            aux.add(String.valueOf(new_rate));
                        } else {
                            aux.add(String.valueOf(new_rate));
                            aux.add(String.valueOf(IDitem));
                        }
                    } else if (positem < posuser && positem < posrate) {
                        aux.add(String.valueOf(IDitem));
                        if (posuser < posrate) {
                            aux.add(String.valueOf(IDuser));
                            aux.add(String.valueOf(new_rate));
                        } else {
                            aux.add(String.valueOf(new_rate));
                            aux.add(String.valueOf(IDuser));
                        }
                    } else if (posrate < posuser && posrate < positem) {
                        aux.add(String.valueOf(new_rate));
                        if (posuser < positem) {
                            aux.add(String.valueOf(IDuser));
                            aux.add(String.valueOf(IDitem));
                        } else {
                            aux.add(String.valueOf(IDitem));
                            aux.add(String.valueOf(IDuser));
                        }
                    }
                    content.add(pos, aux);
                    //CSVKnown.setContent(l);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean delRatingCSV(int IDuser, int IDitem) {
        //QUE CSVS A ELIMINAR RATING?
        Map<Integer, Map<Integer, Float>> auxK = mapRate;
        if(auxK.containsKey(IDuser)){
            Map<Integer, Float> m = auxK.get(IDuser);
            if(m.containsKey(IDitem)) {
                m.remove(IDitem);
                setMapRate(auxK);

                //List<List<String>> l = CSVKnown.getContent();
                //List<String> head = CSVKnown.getHeader();
                int posu = header.indexOf("userId");
                int posi = header.indexOf("itemId");
                //int rows = CSVKnown.getNumRows();
                for (int i = content.size()-1; i >= 0; --i){
                    List<String> aux = content.get(i);
                    if (aux.get(posu).equals(String.valueOf(IDuser))) {
                        if (aux.get(posi).equals(String.valueOf(IDitem))) {
                            content.remove(i);
                            numRows -= 1;
                            //CSVKnown.setNumRows(rows);
                        }
                    }
                }
                //CSVKnown.setContent(l);
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        CSVparserRate CSVRate = new CSVparserRate("FONTS/src/persistencia/series/250/ratings.db.csv");
        CSVRate.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());




        String s = "rating";
        String s1 = "rating_prepo";
        CSVRate.guardar_datos(CSVRate.getContent(), CSVRate.getHeader(), s);
        CSVRate.guardar_datos_preproces(CSVRate.getMapRate(), s1);
        CSVRate.reload_map_preporcess("FONTS/src/persistencia/rating_prepo.csv");
        out.println("Hola");
    }
}
