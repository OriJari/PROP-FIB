package dominio.clases.preprocessat;

import dominio.clases.content.*;

import java.io.*;
import java.util.*;

import static java.lang.System.out;


/**
 * @class CSVparserItem
 * @brief Implements the lecture and the proces of data of the csv
 * @author Miguel
 */

public class CSVparserItem {

    private Integer numCols, numRows;
    private String path;
    private List<List<String>> content;
    private List<String> header;
    private Map<Integer, List<Content>> mapRatedata;
    private List<Integer> id_Items;

    /**
     * @brief Default builder.
     * @param path where is located the csv document
     * \pre the path of the file needs to be existent and the document csv needs to be from type items.csv
     *
     * \post It creates a <em>CSVparserItem</em> object with its attribute <em>path</em> with the values:
     * <em>numCols</em>  initialized as 0, <em>numRows</em>  initialized as 0, <em>dominio.controladores.clases.atribut.content</em> empty, <em>header</em> empty,
     *  <em>mapRatedata</em> empty and <em>id_Items</em>.
     */
    public CSVparserItem(String path){
        this.path = path;
        this.numCols = 0;
        this.numRows = 0;
        this.content = new ArrayList<>();
        this.mapRatedata = new TreeMap<>();
        this.header = new ArrayList<>();
        this.id_Items = new ArrayList<>();
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
     * @return the lecture of the document csv
     */
    public List<List<String>> getContent() {
        return content;
    }

    /**
     * @brief Getter of the class, gets the mapRatedata
     * \pre the document needs to be read to obtain the dominio.controladores.clases.atribut.content
     * \post obtain the data set manipulated to make easy the operation in algorithms
     * @return set of data structured
     */
    public Map<Integer, List<Content>> getMapRatedata() {
        return mapRatedata;
    }

    /**
     * @brief Getter of the class, gets the set of id items
     * \pre needs to have a mapRatedata to obtain the set of id items
     * \post obtain the set of id items of the csv
     * @return the array list of ths different items of the csv
     */
    public List<Integer> getId_Items() {
        return id_Items;
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
     * @brief Setter of the class, gets the mapRatedata
     * \pre true
     * \post modify the attribute mapRatedata, changing the dominio.controladores.clases.atribut.content of it.
     * @param mapRatedata, set of data to redefine a previous one
     */
    public void setMapRatedata(Map<Integer, List<Content>> mapRatedata) {
        this.mapRatedata = mapRatedata;
    }

    /**
     * @brief Setter of the class, set the id of the items
     * \pre true
     * \post modify the attribute id_Items, changing the dominio.controladores.clases.atribut.content of it.
     * @param id_Items, set of data to redefine a previous one
     */
    public void setId_Items(List<Integer> id_Items) {
        this.id_Items = id_Items;
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
     * @brief obtains the position where is located the id in csv
     * @param header of the csv
     * \pre needs a dominio.controladores.clases.atribut.content to read.
     * \post obtain the position of the id dominio.controladores.clases.atribut.item in a list.
     * @return the position where are located the id in the header, -1 otherwise.
     */
    public Integer obten_id_header(List<String> header){
        if (header.contains("id")){
            return header.indexOf("id");
        }
        return -1;
    }

    /**
     * @brief Read the dominio.controladores.clases.atribut.content of the dominio.controladores.clases.atribut.item csv into memory.
     * \pre needs a document csv to read
     * \post obtains the lecture of the document manipulated into dominio.controladores.clases.atribut.content.
     */
    public void readLoadItem(){
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.path);
            Scanner sc = new Scanner(fis);
            obtainHeader(this.path);
            sc.nextLine(); //without header
            //For each line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                List<String> splitContent = new ArrayList<>(Arrays.asList(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
                if (splitContent.size() < header.size()){
                    String res = splitContent.get(0);
                    for (int i = 1; i < splitContent.size(); ++i){
                        String s = splitContent.get(i);
                        res += s;
                    }
                    line = sc.nextLine();
                    res += line;
                    splitContent = new ArrayList<>(Arrays.asList(res.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
                }
                content.add(splitContent);
                //Update cols&rows
                this.numRows += 1;
                this.numCols = Math.max(splitContent.size(), numCols);
            }
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
    public  Integer String_to_Int(String s){
        return Integer.parseInt(s);
    }


    /**
     * @brief Change a String value to a Double one
     * \pre true
     * \post change the value of the string to a float.
     * @param s String to obtain the corresponding value
     * @return string s converted to double
     */
    public Double String_to_Double(String s){
        return Double.parseDouble(s);
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

    /**
     * @brief Obtains the appropriate preprocess of data set for the kk-neight dominio.controladores.clases.atribut.algorithm
     * \pre needs to have a dominio.controladores.clases.atribut.content of the lecture
     * \post change the dominio.controladores.clases.atribut.content to a estructure where the data is processed
     * @param rate_content data set read from the csv
     */
    public void MapItemData(List<List<String>> rate_content){
        int index = 0;
        int id_pos = obten_id_header(this.header);
        for (List<String> aux : rate_content) {
            int pos = 0;
            List<Content> newtagform = new ArrayList<>();
            for (String s : aux) {
                if (pos == id_pos){
                    id_Items.add(String_to_Int(s));
                }
                boolean act = true;
                Content t = new Content();
                if (s.equals("False")) {
                    t.setTag("b");
                    t.setTag_numi(0);
                    t.setTag_numd(-1.0);
                    t.setCategorics(null);
                    act = false;
                }
                if (s.equals("True")) {
                    t.setTag("b");
                    t.setTag_numi(1);
                    t.setTag_numd(-1.0);
                    t.setCategorics(null);
                    act = false;
                }
                Double valD = -1.0;
                boolean bd = true;
                boolean bi = true;
                try {
                    valD = String_to_Double(s);
                } catch (NumberFormatException e) {
                    bd = false;
                    bi = false;
                }
                Integer valI = -1;
                try {
                    valI = String_to_Int(s);
                } catch (NumberFormatException e) {
                    bi = false;
                }
                if (bd) {
                    t.setTag("d");
                    t.setTag_numi(valI);
                    t.setTag_numd(valD);
                    t.setCategorics(null);
                    act = false;
                }
                if (bi) {
                    t.setTag("i");
                    t.setTag_numi(valI);
                    t.setTag_numd(valD);
                    t.setCategorics(null);
                    act = false;
                }
                if (s.contains(";")){
                    if(!s.contains("\"")) {
                        t.setTag("c");
                        t.setTag_numi(valI);
                        t.setTag_numd(valD);
                        List<String> orderlist = Arrays.asList(s.split(";"));
                        Collections.sort(orderlist);
                        t.setCategorics(orderlist);
                        act = false;
                    }
                }
                if (act) t.setTag(s);
                newtagform.add(t);
                ++pos;
            }
            mapRatedata.put(index, newtagform);
            ++index;
        }
    }

    public void guardar_datos(List<List<String>> content, List<String> header) {

        //Scanner sc = new Scanner(System.in);
        //String nuevoFichero = sc.nextLine();
        //PENDIENTE NOMBRE FICHERO
        File archivo = new File("FONTS/src/persistencia/" + "items" + ".csv");

        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            int size_header = header.size();
            for (int i = 0; i < size_header; ++i) {
                if (i == 0) out.write(header.get(0));
                else out.write("," + header.get(i));
            }
            out.write("\n");
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

    public void guardar_datos_prepros(Map<Integer, List<Content>> mapRatedata, List<String> header) {

        //Scanner sc = new Scanner(System.in);
        //String nuevoFichero = sc.nextLine();
        //PENDIENTE NOMBRE FICHERO
        File archivo = new File("FONTS/src/persistencia/" + "itemsporcesdata" + ".csv");

        try {
            FileWriter doc = new FileWriter(archivo);
            PrintWriter out = new PrintWriter(doc);
            int size_header = header.size();
            for (int i = 0; i < size_header; ++i) {
                if (i == 0) out.write(header.get(0));
                else out.write("," + header.get(i));
            }
            out.write("\n");
            for (Map.Entry<Integer, List<Content>> entry : mapRatedata.entrySet()) {
                Integer first = entry.getKey();
                List<Content> l = entry.getValue();
                for (int i = 0; i < l.size(); ++i){
                    Content c = l.get(i);
                    boolean primer = true;
                    out.write( c.getTag() + "," + String.valueOf(c.getTag_numi()) + "," +String.valueOf(c.getTag_numd()) + ',');
                    if (c.getCategorics() != null){
                        List<String> li = c.getCategorics();
                        for (int j = 0; j < li.size(); ++j){
                            if (j == 0)  out.write(li.get(j));
                            else out.write(";" + li.get(j));
                        }
                    }
                    else if (c.getCategorics() == null) out.write( "null");
                    if (primer) primer = false;
                    if (!primer) out.write(",");
                }
                out.write( "\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void reload_map_preporcess(String path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            obtainHeader(path);
            sc.nextLine();  // without header
            Map<Integer, List<Content>> m = new TreeMap<>();
            //For each line
            int filas = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> splitContent = Arrays.asList(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
                List<Content> aux = new ArrayList<>();
                int size = splitContent.size();
                int i0 = 0;
                int i1 = 1;
                int i2 = 2;
                int i3 = 3;
                while(i3 < size){
                    String tag = splitContent.get(i0);
                    String tagi = splitContent.get(i1);
                    String tagd = splitContent.get(i2);
                    String categorics = splitContent.get(i3);
                    Content c = new Content();
                    c.setTag(tag);
                    if (!tagi.equals("null")) c.setTag_numi(String_to_Int(tagi));
                    if (!tagd.equals("null")) c.setTag_numd(String_to_Double(tagd));
                    /*float f = Float.parseFloat(tagd);
                    double tagdd = (double) f;*/
                    if (categorics.contains(";")){
                        List<String> cat = Arrays.asList(categorics.split(";"));
                        c.setCategorics(cat);
                    }
                    aux.add(c);
                    i0 += 4;
                    i1 += 4;
                    i2 += 4;
                    i3 += 4;
                }
                m.put(filas, aux);
                ++filas;
            }
            this.mapRatedata = m;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        CSVparserItem CSVItem = new CSVparserItem("FONTS/src/persistencia/itemP.csv");
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        CSVItem.guardar_datos(CSVItem.getContent(), CSVItem.getHeader());
        CSVItem.guardar_datos_prepros(CSVItem.getMapRatedata(), CSVItem.getHeader());
        CSVItem.reload_map_preporcess("FONTS/src/persistencia/itemsporcesdata.csv");
        out.println("hola");
    }*/

}
