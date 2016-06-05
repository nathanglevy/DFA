package Testing;

import Graph.Graph;
import Graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphVerticesTests {
    @Test
    public final void correctCountTest(){
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        Assert.assertEquals(graph.getVertexCount(), 1);
    }

    @Test
    public final void getVertexListTest(){
        Graph graph = new Graph();
        Vertex vertex1 = graph.addVertex("vertex_A");
        Vertex vertex2 = graph.addVertex("vertex_B");
        List<Vertex> testList = new ArrayList<>();
        testList.add(vertex1);
        testList.add(vertex2);
        List<Vertex> vertexList = graph.getVertexList();
        Assert.assertArrayEquals(vertexList.toArray(), testList.toArray());
    }

    @Test
    public final void getVertexNameListTest(){
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.addVertex("vertex_B");
        List<String> testList = new ArrayList<>();
        testList.add("vertex_A");
        testList.add("vertex_B");
        List<String> vertexList = graph.getVertexNameList();
        Assert.assertArrayEquals(vertexList.toArray(), testList.toArray());
    }

    @Test
    public final void getVertexByNameTest() {
        Graph graph = new Graph();
        Vertex vertex1 = graph.addVertex("vertex_A");
        Vertex vertex2 = graph.addVertex("vertex_B");
        Assert.assertEquals(graph.getVertexByName("vertex_A"),vertex1);
        Assert.assertEquals(graph.getVertexByName("vertex_B"),vertex2);
    }

    @Test
    public final void checkIfVertexExistsTest() {
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.addVertex("vertex_B");
        Assert.assertEquals(graph.doesVertexExist("vertex_A"),true);
        Assert.assertEquals(graph.doesVertexExist("vertex_B"),true);
        Assert.assertEquals(graph.doesVertexExist("vertex_C"),false);
    }

    @Test(expected = RuntimeException.class)
    public final void addTwoVertexesWithSameNameTest() {
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.addVertex("vertex_A");
    }

    @Test(expected = RuntimeException.class)
    public final void getVertexNameNoExistTest() {
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.getVertexByName("vertex_B");
    }

    @Test
    public final void getVertexNoExistTest() {
        Graph graph1 = new Graph();
        Graph graph2 = new Graph();
        Vertex v1 = graph1.addVertex("vertex_A");
        Vertex v2 = graph2.addVertex("vertex_B");

        Assert.assertEquals(graph1.doesVertexExist("vertex_B"),false);
        Assert.assertEquals(graph1.doesVertexExist(v2),false);
        Assert.assertEquals(graph2.doesVertexExist("vertex_A"),false);
        Assert.assertEquals(graph2.doesVertexExist(v1),false);

        Assert.assertEquals(graph1.doesVertexExist("vertex_A"),true);
        Assert.assertEquals(graph1.doesVertexExist(v1),true);
        Assert.assertEquals(graph2.doesVertexExist("vertex_B"),true);
        Assert.assertEquals(graph2.doesVertexExist(v2),true);
    }

    @Test
    public final void getVertexExistTest() {
        Graph graph = new Graph();
        Vertex v = graph.addVertex("vertex_A");
        Assert.assertEquals(graph.doesVertexExist(v), true);
    }
}
