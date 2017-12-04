package dsl.graph;

public class Edge {

    private final Vertex from;
    private final Vertex to;
    private final Double weight;

    public Edge(Vertex from, Vertex to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public final Vertex getFrom() {
        return from;
    }

    public final Vertex getTo() {
        return to;
    }

    public final Double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("From %s to %s with weight %.2f", from, to, weight);
    }
}
