package dsl.graph;

public class NestedFuncEdgeGraphBuider {

    private Graph graph;

    private NestedFuncEdgeGraphBuider() {
        this.graph = new Graph();
    }

    public NestedFuncEdgeGraphBuider edge(Edge edge) {
        this.graph.addEdge(edge);
        return this;
    }

    public void printGraph() {
        System.out.println(this.graph.toString());
    }

    public static NestedFuncEdgeGraphBuider nfeGraph() {
        return new NestedFuncEdgeGraphBuider();
    }

    public static FromDone from(String from) {
        return to -> weight -> {
            return new Edge(new Vertex(from), new Vertex(to), weight);
        };
    }

    static interface FromDone {
        ToDone to(String label);
    }

    static interface ToDone {
        Edge weight(Double weight);
    }

}
