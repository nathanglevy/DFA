package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private List<Vertex> neighbours;
    Vertex(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    void connectTo(Vertex vertexToConnectTo) {
        if (!neighbours.contains(vertexToConnectTo)) {
            neighbours.add(vertexToConnectTo);
        }
    }

    List<Vertex> getNeighbours() {
        return neighbours;
    }

    public String toString() {
        return this.name;
    }
}
