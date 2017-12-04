package dsl.graph;

import static dsl.graph.GraphBuilder.graph;
import static dsl.graph.NestedFuncEdgeGraphBuider.from;
import static dsl.graph.NestedFuncEdgeGraphBuider.nfeGraph;

public class GraphDslDemo {

    public static void main(String[] args) {
        graph()
                .edge().from("A").to("B").weight(10.25)
                .edge().from("C").to("D").weight(25.43)
                .edge().from("A").to("D").weight(12.30)
                .printGraph();

        nfeGraph()
                .edge(from("A").to("B").weight(5.5))
                .edge(from("C").to("D").weight(7.5))
                .edge(from("A").to("D").weight(8.5))
                .printGraph();
    }
}
