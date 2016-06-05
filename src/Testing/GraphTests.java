package Testing;
import Graph.Graph;
import Graph.Vertex;
import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;
import java.util.List;

public class GraphTests {
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
    public final void getVertexNoExistTest() {
        Graph graph = new Graph();
        graph.addVertex("vertex_A");
        graph.getVertexByName("vertex_B");
    }



}
