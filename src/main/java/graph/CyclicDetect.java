package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class CyclicDetect {

    private List<List<Vertex<String>>> find(Vertex<String> root) {
        List<List<Vertex<String>>> res = new ArrayList<>();
        Stack<Vertex<String>> stack = new Stack<>();
        Set<Vertex<String>> visited = new HashSet<>();
        Map<Vertex<String>, Vertex<String>> parentMap = new HashMap<>();
        Set<Vertex<String>> cyclics = new HashSet<>();
        stack.push(root);
        parentMap.put(root, null);
        while (!stack.isEmpty()) {
            Vertex<String> v = stack.pop();
            if (!visited.contains(v)) {
                visited.add(v);
            }
            for (Vertex<String> n : v.getNeighbours()) {
                if (!visited.contains(n)) {
                    parentMap.put(n, v);
                    stack.push(n);
                }
                if (n.equals(root) && !parentMap.get(v).equals(root)) {
                    cyclics.add(v);
                }
            }
        }

        for (Vertex<String> v : cyclics) {
            stack.clear();
            for (Vertex<String> cv = v; cv != null;cv = parentMap.get(cv)) {
                stack.push(cv);
            }
            res.add(new ArrayList<>(stack));
        }
        return res;
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
        CyclicDetect cyclicDetect = new CyclicDetect();
        System.out.println(cyclicDetect.find(a));
    }

}
