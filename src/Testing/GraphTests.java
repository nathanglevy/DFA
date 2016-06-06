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

    @Test
    public final void getReverseGraph() {
        Graph graph = new Graph();
        graph.addVertex("VertexA");
        graph.addVertex("VertexB");
        graph.addVertex("VertexC");
        graph.connectVertices("VertexA", "VertexB");
        graph.connectVertices("VertexB", "VertexC");
        graph.connectVertices("VertexC", "VertexA");
        Graph reversed = graph.getReverseGraph();
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        List<String> listC = new ArrayList<>();
        listA.add("VertexC");
        listB.add("VertexA");
        listC.add("VertexB");
        Assert.assertArrayEquals(reversed.getNeighboursNames("VertexA").toArray(), listA.toArray());
        Assert.assertArrayEquals(reversed.getNeighboursNames("VertexB").toArray(), listB.toArray());
        Assert.assertArrayEquals(reversed.getNeighboursNames("VertexC").toArray(), listC.toArray());
    }

    @Test
    public final void getConnectedVerticesNames() {
        Graph graph = new Graph();
        graph.addVertex("VertexA");
        graph.addVertex("VertexB");
        graph.addVertex("VertexC");
        graph.addVertex("VertexD");
        graph.connectVertices("VertexA", "VertexB");
        graph.connectVertices("VertexB", "VertexC");
        graph.connectVertices("VertexC", "VertexA");
        List<String> connected = graph.getConnectedVerticesNames("VertexA");
        List<String> testList = new ArrayList<>();
        testList.add("VertexA");
        testList.add("VertexB");
        testList.add("VertexC");
        Assert.assertEquals(connected.containsAll(testList),true);
        Assert.assertEquals(testList.containsAll(connected),true);
    }

    @Test
    public final void compareGraphs() {
        Graph graph1 = new Graph();
        graph1.addVertex("VertexA");
        graph1.addVertex("VertexB");
        graph1.addVertex("VertexC");
        graph1.addVertex("VertexD");
        graph1.connectVertices("VertexA", "VertexB");
        graph1.connectVertices("VertexB", "VertexC");
        graph1.connectVertices("VertexC", "VertexA");

        Graph graph2 = new Graph();
        graph2.addVertex("VertexC");
        graph2.addVertex("VertexB");
        graph2.addVertex("VertexD");
        graph2.addVertex("VertexA");
        graph2.connectVertices("VertexA", "VertexB");
        graph2.connectVertices("VertexB", "VertexC");

        Assert.assertEquals(graph1.isIdenticalTo(graph2), false);
        graph2.connectVertices("VertexC", "VertexA");
        Assert.assertEquals(graph1.isIdenticalTo(graph2), true);
        graph2.connectVertices("VertexD", "VertexA");
        Assert.assertEquals(graph1.isIdenticalTo(graph2), false);


    }

}
