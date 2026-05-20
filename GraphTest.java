import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private Graph graph;

    @BeforeEach
    public void setUp() {

        graph = new Graph(5);

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 3);
        graph.addEdge("A", "C", 20);
    }

    @Test
    public void testAddEdge() {

        int[][] matrix = graph.getAdjacencyMatrix();

        int i = graph.getCityIndex("A");
        int j = graph.getCityIndex("B");

        assertEquals(5, matrix[i][j]);
    }

    @Test
    public void testRemoveEdge() {

        graph.removeEdge("A", "B");

        int[][] matrix = graph.getAdjacencyMatrix();

        int i = graph.getCityIndex("A");
        int j = graph.getCityIndex("B");

        assertEquals(999999, matrix[i][j]);
    }

    @Test
    public void testFloyd() {

        graph.floyd();

        int[][] distances = graph.getDistanceMatrix();

        int i = graph.getCityIndex("A");
        int j = graph.getCityIndex("C");

        assertEquals(8, distances[i][j]);
    }

    @Test
    public void testGraphCenter() {

        graph.floyd();

        String center = graph.graphCenter();

        assertNotNull(center);
    }
}