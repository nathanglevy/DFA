package Testing;
import Graph.Graph;
import Graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphTests {
    @Test
    public final void makeCyclicGraph() {
        Graph graph = new Graph();
        graph.addVertex("VertexA");
        graph.addVertex("VertexB");
        graph.addVertex("VertexC");
        graph.connectVertices("VertexA", "VertexB");
        graph.connectVertices("VertexB", "VertexC");
        graph.connectVertices("VertexC", "VertexA");
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        List<String> listC = new ArrayList<>();
        listA.add("VertexB");
        listB.add("VertexC");
        listC.add("VertexA");
        Assert.assertArrayEquals(graph.getNeighboursNames("VertexA").toArray(), listA.toArray());
        Assert.assertArrayEquals(graph.getNeighboursNames("VertexB").toArray(), listB.toArray());
        Assert.assertArrayEquals(graph.getNeighboursNames("VertexC").toArray(), listC.toArray());
    }

    @Test
    public final void isConnectedTest() {
        Graph graph = new Graph();
        graph.addVertex("VertexA");
        graph.addVertex("VertexB");
        graph.addVertex("VertexC");
        graph.addVertex("VertexD");
        graph.connectVertices("VertexA", "VertexB");
        graph.connectVertices("VertexB", "VertexC");
        graph.connectVertices("VertexC", "VertexA");
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexB"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexB", "VertexC"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexC", "VertexA"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexC"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexB", "VertexA"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexC", "VertexB"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexD"), false);
        Assert.assertEquals(graph.areVerticesConnected("VertexD", "VertexA"), false);
    }

    @Test
    public final void isConnectedTest2() {
        Graph graph = new Graph();
        graph.addVertex("VertexA");
        graph.addVertex("VertexB");
        graph.addVertex("VertexC");
        graph.addVertex("VertexD");
        graph.addVertex("VertexE");
        graph.addVertex("VertexF");
        graph.connectVertices("VertexA", "VertexB");
        graph.connectVertices("VertexB", "VertexA");
        graph.connectVertices("VertexB", "VertexC");
        graph.connectVertices("VertexC", "VertexF");
        graph.connectVertices("VertexC", "VertexD");
        graph.connectVertices("VertexD", "VertexE");
        graph.connectVertices("VertexE", "VertexE");
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexB"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexB", "VertexA"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexC", "VertexA"), false);
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexC"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexF"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexA", "VertexD"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexD", "VertexF"), false);
        Assert.assertEquals(graph.areVerticesConnected("VertexE", "VertexE"), true);
        Assert.assertEquals(graph.areVerticesConnected("VertexE", "VertexB"), false);
        Assert.assertEquals(graph.areVerticesConnected("VertexE", "VertexF"), false);
    }


}
