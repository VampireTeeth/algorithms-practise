package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestPath {

    public List<Vertex<String>> find(Vertex<String> src, Vertex<String> dst) {
        HashMap<Vertex<String>, Vertex<String>> parentMap = new HashMap<>();
        bfsSearch(src, parentMap);
        List<Vertex<String>> res = new LinkedList<>();
        for (Vertex<String> v = dst;v != null;v = parentMap.get(v)) {
            res.add(v);
        }
        Collections.reverse(res);
        return res;
    }

    private void bfsSearch(Vertex<String> src, Map<Vertex<String>, Vertex<String>> parentMap) {
        Queue<Vertex<String>> queue = new LinkedList<>();
        Set<Vertex<String>> visited = new HashSet<>();
        queue.add(src);
        parentMap.put(src, null);
        while (!queue.isEmpty()) {
            Vertex<String> v = queue.remove();
            if (!visited.contains(v)) visited.add(v);
            for (Vertex<String> n : v.getNeighbours()) {
                if (!visited.contains(n)) {
                    queue.add(n);
                    parentMap.put(n, v);
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

        ShortestPath shortestPath = new ShortestPath();
        System.out.println(shortestPath.find(a, f));
        System.out.println(shortestPath.find(a, h));
    }
}
