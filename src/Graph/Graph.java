package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public boolean doesVertexExist(Vertex v) {
        for (Vertex vertex : vertexList) {
            if (vertex.equals(v)) {
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

    public void connectVertices(String vertexNameA, String vertexNameB) {
        if (!this.doesVertexExist(vertexNameA)) {
            throw new RuntimeException("Vertex: " + vertexNameA + " does not exist");
        }
        if (!this.doesVertexExist(vertexNameB)) {
            throw new RuntimeException("Vertex: " + vertexNameB + " does not exist");
        }
        connectVertices(this.getVertexByName(vertexNameA), this.getVertexByName(vertexNameB));
    }

    public void connectVertices(Vertex vertexA, Vertex vertexB) {
        if (!this.doesVertexExist(vertexA)) {
            throw new RuntimeException("Vertex: " + vertexA + " does not exist");
        }
        if (!this.doesVertexExist(vertexB)) {
            throw new RuntimeException("Vertex: " + vertexB + " does not exist");
        }
        vertexA.connectTo(vertexB);
    }

    public List<Vertex> getNeighbours(String vertexName) {
        if (!this.doesVertexExist(vertexName)) {
            throw new RuntimeException("Vertex: " + vertexName + " does not exist");
        }
        return getNeighbours(getVertexByName(vertexName));
    }

    public List<Vertex> getNeighbours(Vertex vertex) {
        if (!this.doesVertexExist(vertex)) {
            throw new RuntimeException("Vertex: " + vertex + " does not exist");
        }
        return vertex.getNeighbours();
    }

    public List<String> getNeighboursNames(String vertexName) {
        if (!this.doesVertexExist(vertexName)) {
            throw new RuntimeException("Vertex: " + vertexName + " does not exist");
        }
        return getNeighboursNames(getVertexByName(vertexName));
    }

    public List<String> getNeighboursNames(Vertex vertex) {
        List<Vertex> vertexList = getNeighbours(vertex);
        List<String> nameList = new ArrayList<>();
        for (Vertex vertexItr : vertexList) {
            nameList.add(vertexItr.getName());
        }
        return nameList;
    }

    public boolean areVerticesConnected(String vertexNameA, String vertexNameB) {
        if (!this.doesVertexExist(vertexNameA)) {
            throw new RuntimeException("Vertex: " + vertexNameA + " does not exist");
        }
        if (!this.doesVertexExist(vertexNameB)) {
            throw new RuntimeException("Vertex: " + vertexNameB + " does not exist");
        }
        return areVerticesConnected(this.getVertexByName(vertexNameA), this.getVertexByName(vertexNameB));
    }

    public boolean areVerticesConnected(Vertex vertex, Vertex target) {
        if (!this.doesVertexExist(vertex)) {
            throw new RuntimeException("Vertex: " + vertex + " does not exist");
        }
        if (!this.doesVertexExist(target)) {
            throw new RuntimeException("Vertex: " + target + " does not exist");
        }

        System.out.println("Currently beginning Vertex Connect");

        List<Vertex> toVisit = new ArrayList<>();
        toVisit.add(vertex);
        List<Vertex> visited = new ArrayList<>();
        boolean done = false;
        while (toVisit.size() > 0) {
            System.out.println("Visited: " + visited + " to Visit: " + toVisit);
            List<Vertex> currentVisit = new ArrayList<>(toVisit);
            toVisit.clear();
            for (Vertex neighbor : currentVisit) {
                System.out.println("Currently visiting: " + neighbor.getName());
                if (neighbor.equals(target)) {
                    return true;
                }
                if (visited.contains(neighbor)) {
                    continue;
                } else {
                    for (Vertex neighborsChildren : neighbor.getNeighbours()) {
                        System.out.println("Children are: " + neighborsChildren.getName());
                        if ((!toVisit.contains(neighborsChildren)) && (!visited.contains(neighborsChildren))) {
                            toVisit.add(neighborsChildren);
                        }
                    }
                }
                visited.add(neighbor);
            }
        }
        return false;
    }
}
