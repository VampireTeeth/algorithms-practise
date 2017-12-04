package dsl.graph;

public class GraphBuilder {

    public static GraphBuilder graph() {
        return new GraphBuilder();
    }

    private Graph graph;

    private GraphBuilder() {
        this.graph = new Graph();
    }

    public EdgeCalled edge() {
        GraphBuilder me = this;
        return from -> to -> weight -> {
            Edge edge = new Edge(new Vertex(from), new Vertex(to), weight);
            me.graph.addEdge(edge);
            return me;
        };
    }

    public void printGraph() {
        System.out.println(this.graph.toString());
    }

    public Graph get() {
        return this.graph;
    }

    static interface EdgeCalled {
        FromCalled from(String label);
    }

    static interface FromCalled {
        ToCalled to(String label);
    }

    static interface ToCalled {
        GraphBuilder weight(Double weight);
    }
}
