package dsl.graph;

public class Vertex implements Comparable<Vertex>{

    private final String label;

    public Vertex(String label) {
        this.label = label;
    }

    public final String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return  this.label;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.label.compareTo(o.label);
    }
}
