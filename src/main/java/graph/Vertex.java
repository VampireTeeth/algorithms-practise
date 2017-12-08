package graph;

import java.util.HashSet;
import java.util.Set;

public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>>{
    private final T value;
    private final Set<Vertex<T>> neighbours;

    public Vertex(T value) {
        this.value = value;
        this.neighbours = new HashSet<>();
    }

    public Vertex<T> connect(Vertex<T> other) {
        other.neighbours.add(this);
        this.neighbours.add(other);
        return this;
    }

    public Set<Vertex<T>> getNeighbours() {
        return this.neighbours;
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public T getValue() {
        return this.value;
    }
}
