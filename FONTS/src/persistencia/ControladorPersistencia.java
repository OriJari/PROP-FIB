package persistencia;

import dominio.clases.content.Content;
import dominio.clases.preprocessat.CSVparserItem;
import dominio.clases.preprocessat.CSVparserRate;
import persistencia.preprocessat.RecommendationSave;
import persistencia.preprocessat.UserList;

import java.util.*;

public class ControladorPersistencia {

    CSVparserItem CSVItem;
    CSVparserRate CSVRate;
    CSVparserRate CSVKnown;
    CSVparserRate CSVUnknown;
    RecommendationSave Recomm;
    UserList UserList;

    public ControladorPersistencia(){}

    public boolean inicializar(String path){
        CSVItem = new CSVparserItem(path + "items.csv");
        CSVRate = new CSVparserRate(path + "ratings.db.csv");
        CSVKnown = new CSVparserRate(path + "ratings.test.known.csv");
        CSVUnknown = new CSVparserRate(path + "ratings.test.unknown.csv");
        //Crea les classes que necessitis
        UserList = new UserList();
        return true;
    }

    public boolean addItem(int ID, List<String> tags){
        ///ATENCION FORMATO CONTENT !!!!!
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        List<Integer> iditems = CSVItem.getId_Items();
        if (iditems.contains(ID)) return false;
        else {
            //incremento fila
            int rows = CSVItem.getNumRows();
            rows += 1;
            CSVItem.setNumRows(rows);

            //Añadir los item y respectivos tags
            List<List<String>> l = CSVItem.getContent();
            //trato datos para crear lista a inserir
            List<String> res = new ArrayList<>();
            res.add(String.valueOf(ID));
            for (int i = 0; i < tags.size(); ++i) res.add(tags.get(i));
            l.add(res);

            //actualizo el nuevo
            CSVItem.setContent(l);

            //PREPROCESADO
            Map<Integer, List<Content>> map = CSVItem.getMapRatedata();
            List<Content> cont = CSVItem.linia_procesado(ID, tags);
            map.put(rows, cont);

            //ADDItem a la lista de items
            iditems.add(ID);
            CSVItem.setId_Items(iditems);
        }
        return true;
    }

    public boolean delItem(int ID) {
        ///ATENCION FORMATO CONTENT !!!!!
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        List<Integer> iditems = CSVItem.getId_Items();
        if (!iditems.contains(ID)) return false;
        else {
            //Posicion de id del item
            int pos = iditems.indexOf(ID);

            //realizo el delete
            List<List<String>> l = CSVItem.getContent();
            l.remove(pos);
            CSVItem.setContent(l);

            Map<Integer, List<Content>> aux = CSVItem.getMapRatedata();
            aux.remove(pos);
            CSVItem.setMapRatedata(aux);

            //actualizo filas
            int rows = CSVItem.getNumRows();
            rows -= 1;
            CSVItem.setNumRows(rows);

        }
        return true;
    }

    public boolean modTag(int IDitem, String atribute, String newtag){
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        List<Integer> iditems = CSVItem.getId_Items();
        if (!iditems.contains(IDitem)) return false;
        else{
            //obtener posicion del atributo en el header y pos del item en la lista de items
            List<String> head = CSVItem.getHeader();
            int poscol = head.indexOf(atribute);
            int posrow = iditems.indexOf(IDitem);

            //Modifico tag pertinente del content
            List<List<String>> l = CSVItem.getContent();
            List<String> aux = l.get(posrow);
            aux.set(poscol, newtag);
            l.set(posrow, aux);
            CSVItem.setContent(l);

            //preporcesado datos del nuevo tag
            Map<Integer, List<Content>> map = CSVItem.getMapRatedata();
            List<Content> cont = CSVItem.linia_procesado(IDitem, aux);
            map.remove(posrow);
            map.put(posrow, cont);
        }
        return true;
    }
    public boolean delTag(int IDitem, String atribute){
        CSVItem.readLoadItem();
        CSVItem.MapItemData(CSVItem.getContent());
        List<Integer> iditems = CSVItem.getId_Items();
        if (!iditems.contains(IDitem)) return false;
        else{
            List<String> head = CSVItem.getHeader();
            int poscol = head.indexOf(atribute);
            int posrow = iditems.indexOf(IDitem);

            List<List<String>> l = CSVItem.getContent();
            List<String> aux = l.get(posrow);
            aux.set(poscol, "null");
            l.set(posrow, aux);
            CSVItem.setContent(l);

            //PREPROCESADO lo ponemos a null a dicho tag
            Map<Integer, List<Content>> map = CSVItem.getMapRatedata();
            List<Content> cont = CSVItem.linia_procesado(IDitem, aux);
            map.remove(posrow);
            map.put(posrow, cont);
        }
        return true;
    }
    public boolean addUser(int ID) {

        //CREO QUE NO TIENE SENTIDO AÑADIR UN USER SIN UNA VALORACION AL DATASET
        //SOLO DEBERIAMOS AÑADIRLO A LA LISTA USERS

        //QUE CSVS A AÑADIR USER?
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());
        Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
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
            l.add(row, aux);
            CSVKnown.setContent(l);
        }
        Set<Integer> s = UserList.getUsers();
        if(s.contains(ID)) return false;
        else{
            s.add(ID);
            UserList.setUsers(s);
        }
        return true;
    }

    public boolean delUser(int ID) {
        //QUE CSVS A ELIMINAR USER?
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());
        Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        if(!auxK.containsKey(ID)) return false;
        else{
            auxK.remove(ID);
            List<String> head = CSVKnown.getHeader();
            int pos = head.indexOf("userID");
            List<List<String>> l = CSVKnown.getContent();
            for (int i = 0; i < l.size(); ++i){
                int rows = CSVKnown.getNumRows();
                List<String> aux = l.get(i);
                if (aux.get(pos).equals(String.valueOf(ID))) {
                    l.remove(i);
                    rows -= 1;
                    CSVKnown.setNumRows(rows);
                }
            }
        }
        return true;
    }

    public boolean addRating(int IDuser, int IDitem, float valor){
        //QUE CSVS A AÑADIR RATING?
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());
        Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        Map<Integer, Float> m = new TreeMap<>();
        m.put(IDitem, valor);
        //NO PUEDE VALORAR UN MISMO ITEM CON UNA MISMA RATE
        if(auxK.containsValue(m)) return false;
        else{
            auxK.put(IDuser, m);
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
            l.add(row, aux);
            CSVKnown.setContent(l);
        }
        return true;
    }

    public boolean modRating(int IDuser, int IDitem, float new_rate) {
        //QUE CSVS A MODIFICAR RATING?
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());
        Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        if(auxK.containsKey(IDuser)){
            Map<Integer, Float> m = auxK.get(IDuser);
            if(m.containsKey(IDitem)) {
                m.remove(IDitem);
                m.put(IDitem, new_rate);
                CSVKnown.setMapRate(auxK);

                List<List<String>> l = CSVKnown.getContent();
                List<String> aux = new ArrayList<>();
                List<String> head = CSVKnown.getHeader();
                int pos = -1;
                for (int i = 0; i < l.size(); ++i){
                    List<String> miro = l.get(i);
                    int posu = head.indexOf("userId");
                    int posi = head.indexOf("itemId");
                    if (miro.get(posu).equals(String.valueOf(IDuser))) {
                        if (miro.get(posi).equals(String.valueOf(IDitem))) {
                            pos = i;
                        }
                    }
                }
                if (pos != -1) {
                    l.remove(pos);
                    int posuser = head.indexOf("userId");
                    int positem = head.indexOf("itemId");
                    int posrate = head.indexOf("rating");
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
                    l.add(pos, aux);
                    CSVKnown.setContent(l);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean delRating(int IDuser, int IDitem) {
        //QUE CSVS A ELIMINAR RATING?
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());
        Map<Integer, Map<Integer, Float>> auxR = CSVRate.getMapRate();
        Map<Integer, Map<Integer, Float>> auxK = CSVKnown.getMapRate();
        if(auxK.containsKey(IDuser)){
            Map<Integer, Float> m = auxK.get(IDuser);
            if(m.containsKey(IDitem)) {
                m.remove(IDitem);
                CSVKnown.setMapRate(auxK);

                List<List<String>> l = CSVKnown.getContent();
                List<String> head = CSVKnown.getHeader();
                int posu = head.indexOf("userID");
                int posi = head.indexOf("itemID");
                for (int i = 0; i < l.size(); ++i){
                    int rows = CSVKnown.getNumRows();
                    List<String> aux = l.get(i);
                    if (aux.get(posu).equals(String.valueOf(IDuser))) {
                        if (aux.get(posi).equals(String.valueOf(IDitem))) {
                            l.remove(i);
                            rows -= 1;
                            CSVKnown.setNumRows(rows);
                        }
                    }
                }
                CSVKnown.setContent(l);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<List<String>> getMapRate(int a){
        List<List<String>> result = new ArrayList<>();
        return result;
    }

    public List<List<String>> getMapItem(){
        List<List<String>> result = new ArrayList<>();
        return result;
    }
}