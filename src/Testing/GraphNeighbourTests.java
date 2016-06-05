package Testing;

import Graph.Graph;
import Graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphNeighbourTests {
    @Test
    public final void connectVertices() {
        Graph graph = new Graph();
        Vertex vertex_a = graph.addVertex("vertex_A");
        Vertex vertex_b = graph.addVertex("vertex_B");
        graph.connectVertices(graph.getVertexByName("vertex_A"), graph.getVertexByName("vertex_B"));
        List<Vertex> vectorList = new ArrayList<>();
        vectorList.add(vertex_b);
        Assert.assertArrayEquals(graph.getNeighbours(vertex_a).toArray(),vectorList.toArray());
        Assert.assertArrayEquals(graph.getNeighbours("vertex_A").toArray(),vectorList.toArray());
    }

    @Test
    public final void connectVerticesByName() {
        Graph graph = new Graph();
        Vertex vertex_a = graph.addVertex("vertex_A");
        Vertex vertex_b = graph.addVertex("vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        List<Vertex> vectorList = new ArrayList<>();
        vectorList.add(vertex_b);
        Assert.assertArrayEquals(graph.getNeighbours(vertex_a).toArray(),vectorList.toArray());
        Assert.assertArrayEquals(graph.getNeighbours("vertex_A").toArray(),vectorList.toArray());
    }

    @Test
    public final void getNeighboursNames() {
        Graph graph = new Graph();
        Vertex vertex_a = graph.addVertex("vertex_A");
        Vertex vertex_b = graph.addVertex("vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        List<String> vectorList = new ArrayList<>();
        vectorList.add("vertex_B");
        Assert.assertArrayEquals(graph.getNeighboursNames(vertex_a).toArray(),vectorList.toArray());
        Assert.assertArrayEquals(graph.getNeighboursNames("vertex_A").toArray(),vectorList.toArray());
    }

    @Test(expected = RuntimeException.class)
    public final void getNeighboursNamesFromNonExistingVertex() {
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.addVertex("vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        graph.getNeighboursNames("vertex_C");
    }

    @Test(expected = RuntimeException.class)
    public final void connectWithNonExistingNeighbour() {
        Graph graph1 = new Graph();
        Graph graph2 = new Graph();
        graph1.addVertex("vertex_A");
        graph2.addVertex("vertex_B");
        graph1.connectVertices("vertex_A", "vertex_B");
    }

    @Test
    public final void connectVertexTwice() {
        Graph graph = new Graph();
        Vertex vertex_a = graph.addVertex("vertex_A");
        Vertex vertex_b = graph.addVertex("vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        List<Vertex> vectorList = new ArrayList<>();
        vectorList.add(vertex_b);
        Assert.assertArrayEquals(graph.getNeighbours(vertex_a).toArray(),vectorList.toArray());
        Assert.assertArrayEquals(graph.getNeighbours("vertex_A").toArray(),vectorList.toArray());
    }

    @Test
    public final void noNeighboursTest() {
        Graph graph = new Graph();
        Vertex vertex_a = graph.addVertex("vertex_A");
        Vertex vertex_b = graph.addVertex("vertex_B");
        graph.connectVertices("vertex_A", "vertex_B");
        List<Vertex> vectorList = new ArrayList<>();
        Assert.assertArrayEquals(graph.getNeighbours(vertex_b).toArray(),vectorList.toArray());
        Assert.assertArrayEquals(graph.getNeighbours("vertex_B").toArray(),vectorList.toArray());
    }
}
