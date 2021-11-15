package avaluacio;

import java.util.*;


public class avaluacio {
    List<valoracio> recomanacio; //recomanacio a un usuari ordenada de forma decreixent per el valor de la valoracio(float)
    Map<Integer, Float> known; //valorcions de lusuari reals

    public class valoracio{
        int id;
        float valor;

        public valoracio(Integer id, Float valor){
            this.id = id;
            this.valor = valor;
        }
    }

    class SortByVal implements Comparator<valoracio>{
        public int compare(valoracio a, valoracio b){
            if(a.valor > b.valor){
                return -1;
            }
            else if(a.valor < b.valor){
                return 1;
            }
            else{
                return 0;
            }
        }
    }

    public void avaluacio(Map<Integer, Float> known, List<valoracio> recomanacio) {
        this.known = known;
        this.recomanacio = recomanacio;
        Collections.sort(this.recomanacio, new SortByVal());
    }

    float DCG(){
        int j = 1;
        float result = 0.0f;
        for(int i = 0; i < recomanacio.size(); ++i){
            if(known.containsKey(recomanacio.get(i).id)){
                result += (Math.pow(2, known.get(recomanacio.get(i).id))-1)/(Math.log(j + 1)/Math.log(2));
            }
            if(i == 0 || recomanacio.get(i).valor != recomanacio.get(i-1).valor){
                ++j;
            }
        }
        return result;
    }
}