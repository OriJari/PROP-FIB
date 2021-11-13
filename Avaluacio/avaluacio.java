
import java.util.*;


public class avaluacio {

    Vector<Pair<Integer, Float>> recomanacio; //recomanacio a un usuari ordenada de forma decreixent per el valor de la valoracio(float)
    Map<Integer, Float> known; //valorcions de lusuari reals

    public static void avaluacio(){}

    float DCG(){
        int j = 1;
        float result = 0.0f;
        for(int i = 0; i < recomanacio.size(); ++i){
            if(known.containsKey(recomanacio.get(i).first)){
                result += (Math.pow(2, known.get(recomanacio.get(i).first))-1)/(Math.log(j + 1)/Math.log(2));
            }
            if(i == 0 or recomanacio.get(i).second != recomanacio.get(i-1).second){
                ++j;
            }
        }
        return result;
    }

}