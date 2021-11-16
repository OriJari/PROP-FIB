package algorithm.contentbasedflitering;
import java.util.*;

public class Pair implements Comparable<Pair> {
    int id;
    double similarity;

    public Pair(int id, double similarity) {
        this.id = id;
        this.similarity = similarity;
    }

    public double getSimilarity() {
        return this.similarity;
    }
    public int getId() {
        return this.id;
    }

    @Override
    public int compareTo(Pair p) {
        if (similarity < p.similarity) return -1;
        else if (similarity > p.similarity) return 1;
        else return 0;
    }
}
