package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSDemo {

    public void traversal(Vertex<String> root) {
        Queue<Vertex<String>> queue = new LinkedList<>();
        Set<Vertex<String>> visited = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Vertex<String> v = queue.remove();
            if (!visited.contains(v)) {
                System.out.println(v);
                visited.add(v);
            }
            for (Vertex<String> n : v.getNeighbours()) {
                if (!visited.contains(n)) {
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Vertex<String> a = new Vertex<String>("A");
        Vertex<String> b = new Vertex<String>("B");
        Vertex<String> c = new Vertex<String>("C");
        Vertex<String> d = new Vertex<String>("D");
        Vertex<String> e = new Vertex<String>("E");
        Vertex<String> f = new Vertex<String>("F");
        Vertex<String> g = new Vertex<String>("G");
        Vertex<String> h = new Vertex<String>("H");
        a.connect(b).connect(d).connect(e);
        b.connect(c);
        c.connect(e);
        d.connect(f).connect(g);
        e.connect(g);
        d.connect(f);
        g.connect(h);
        BFSDemo bfs = new BFSDemo();
        bfs.traversal(a);
    }
}
