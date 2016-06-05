package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertexList;
    public Graph() {
        vertexList = new ArrayList<Vertex>();
    }
    public Vertex addVertex(String name) {
        if (doesVertexExist(name)) {
            throw new RuntimeException("Can't add new vertex - Vertex: " + name + " already exists!");
        }
        Vertex vertex = new Vertex(name);
        vertexList.add(vertex);
        return vertex;
    }

    public int getVertexCount() {
        return vertexList.size();
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public boolean doesVertexExist(String name) {
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getVertexNameList() {
        List<String> newList = new ArrayList<>();
        for (Vertex vertex : vertexList) {
            newList.add(vertex.getName());
        }
        return newList;
    }

    public Vertex getVertexByName(String name) {
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals(name)) {
                return vertex;
            }
        }
        throw new RuntimeException("Vertex: " + name + " doesn't exist in graph");
    }
}
