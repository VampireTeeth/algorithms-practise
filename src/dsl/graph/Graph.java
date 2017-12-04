package dsl.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Graph {

    private final Set<Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
        this.vertices = new TreeSet<>();
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        this.vertices.add(edge.getFrom());
        this.vertices.add(edge.getTo());
        this.edges.add(edge);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vertices[");
        for (Vertex v : vertices) {
            sb.append(v);
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("], Edges[");
        for (Edge e : edges) {
            sb.append(e);
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
