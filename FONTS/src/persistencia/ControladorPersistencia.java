package persistencia;

import dominio.clases.content.Content;
import dominio.clases.preprocessat.CSVparserItem;
import dominio.clases.preprocessat.CSVparserRate;
import persistencia.preprocessat.RecommendationSave;
import persistencia.preprocessat.UserList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorPersistencia {

    CSVparserItem CSVItem;
    CSVparserRate CSVRate;
    CSVparserRate CSVKnown;
    CSVparserRate CSVUnknown;
    RecommendationSave Recomm;
    UserList UserList;

    public ControladorPersistencia(){};

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

            ///ATENCION FORMATO CONTENT !!!!!
            ///FALTA ACTUALIZAR PREPROCESADO
        }
        return true;
    }

    public boolean delItem(int ID) {
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
            List<String> head = CSVItem.getHeader();
            int poscol = head.indexOf(atribute);
            int posrow = iditems.indexOf(IDitem);

            List<List<String>> l = CSVItem.getContent();
            List<String> aux = l.get(posrow);
            aux.set(poscol, newtag);
            l.set(posrow, aux);

            //PENDIENTE PREPROCESADO CSV

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

            //PENDIENTE PREPROCESADO CSV

        }
        return true;
    }
    public boolean addUser(int ID) {
        CSVRate.readLoadRate();
        CSVKnown.readLoadRate();
        CSVRate.LoadRate(CSVRate.getContent());
        CSVKnown.LoadRate(CSVKnown.getContent());

        return true;
    }
    public boolean delUser(int ID) {

        return true;
    }
    public boolean addRating(int IDuser, int IDitem, float valor){
        return true;
    }
    public boolean modRating(int IDuser, int IDitem, float new_rate) {
        return true;
    }
    public boolean delRating(int IDuser, int IDitem) {
        return true;
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