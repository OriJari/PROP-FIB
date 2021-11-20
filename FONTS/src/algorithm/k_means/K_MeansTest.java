package algorithm.k_means;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class K_MeansTest {
    private static Scanner sc;

    public static Map<Integer, Float> leerUsuario(){
        Map<Integer, Float> user = new TreeMap<>();
        int numItems = sc.nextInt();
        for(int j = 0; j < numItems; ++j){
            int itemID = sc.nextInt();
            float itemVal = sc.nextFloat();
            user.put(itemID, itemVal);
        }
        return user;
    }

    @org.junit.Test
    public void shootcosineSquaredSimilBasic() throws FileNotFoundException {
        sc = new Scanner(new File(""));
        K_Means km = new K_Means();
        Map<Integer, Float> u1 = leerUsuario();
        Map<Integer, Float> u2 = leerUsuario();
        float simil = km.cosineSquaredSimil(u1, u2);

        float delta = 0.001f;

        double expected = sc.nextFloat();

        assertEquals(expected, simil, delta);
    }
}