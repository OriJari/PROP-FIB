package algorithm.contentbasedflitering;
import java.util.*;

public class Pair implements Comparable<Pair> {
    int id;
    double similarity;

    /**
     * Default builder, with initializers
     * @param id            id of the item
     * @param similarity    similarity with another item (unknown to this class)
     */
    public Pair(int id, double similarity) {
        this.id = id;
        this.similarity = similarity;
    }

    /**
     * Getter of the similarity
     * @return      similarity
     */
    public double getSimilarity() {
        return this.similarity;
    }

    /**
     * Getter of the ID
     * @return      ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets a way to compare two different Pair objects.
     * This is done to create a PriorityQueue with Pair objects, which
     * orders depending on the similarity of the Pair objects.
     * @param p     another Pair object
     * @return      returns which Pair object goes first
     */
    @Override
    public int compareTo(Pair p) {
        if (similarity < p.similarity) return -1;
        else if (similarity > p.similarity) return 1;
        else return 0;
    }
}
