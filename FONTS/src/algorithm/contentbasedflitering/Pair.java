package algorithm.contentbasedflitering;

public class Pair<I extends Number, D extends Number> implements Comparable<Pair<Number, Number>> {
        Integer key;
        Double value;

    public Pair(Integer key, Double value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<Number, Number> o) {
            return (int)(value - o.value);
        }
    }
