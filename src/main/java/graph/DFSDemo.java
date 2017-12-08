package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFSDemo {

    public void traversal(Vertex<String> graph) {
        Stack<Vertex<String>> stack = new Stack<>();
        Set<Vertex<String>> visited = new HashSet<>();
        stack.push(graph);
        while (!stack.isEmpty()) {
            visit(stack, visited);
        }
    }

    private void visit(Stack<Vertex<String>> stack, Set<Vertex<String>> visited) {
        Vertex<String> v = stack.pop();
        System.out.println(v);
        visited.add(v);
        Set<Vertex<String>> neighbours = v.getNeighbours();
        for (Vertex<String> n : neighbours) {
            if (!visited.contains(n)) {
                stack.push(n);
                visit(stack, visited);
            }
        }
    }

    public void nonRecurTraversal(Vertex<String> root) {
        Stack<Vertex<String>> stack = new Stack<>();
        Set<Vertex<String>> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Vertex<String> v = stack.pop();
            if (!visited.contains(v)) {
                System.out.println(v);
                visited.add(v);
            }
            for (Vertex<String> n : v.getNeighbours()) {
                if (!visited.contains(n)) stack.push(n);
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
        DFSDemo dfs = new DFSDemo();
        dfs.traversal(a);
        System.out.println("---------------------------");
        dfs.nonRecurTraversal(a);
    }
}
