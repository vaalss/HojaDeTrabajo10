import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private static final int INF = 999999;

    private ArrayList<String> cities;
    private HashMap<String, Integer> cityIndex;
    private int [][] adjacencyMatrix;
    private int size;

    public Graph(int maxCities) {
        cities = new ArrayList<>();
        cityIndex = new HashMap<>();

        adjacencyMatrix = new int[maxCities][maxCities];
        
        size = 0;

        matrixInit(maxCities);
    }

    private void matrixInit(int maxCities) {
        for (int i = 0; i < maxCities; i++) {
            for (int j = 0; j < maxCities; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    adjacencyMatrix[i][j]= INF;
                }
            }
        }
    }

    public void addCity(String city) {
        if (!cityIndex.containsKey(city)) {
            cities.add(city);
            cityIndex.put(city, size);

            size ++;
        }
    }

    public void addEdge(String from, String to, int distance) {
        addCity(from);
        addCity(to);

        int i = cityIndex.get(from);
        int j = cityIndex.get(to);

        adjacencyMatrix[i][j] = distance;
    }

    public void removeEdge(String from, String to) {
        if (cityIndex.containsKey(from) && cityIndex.containsKey(to)) {
            int i = cityIndex.get(from);
            int j = cityIndex.get(to);

            adjacencyMatrix[i][j] = INF;
        }
    }

    public void printMatrix() {
        System.out.printf("%15s", "");

        for (String city : cities) {
            System.out.printf("%15s", city);
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.printf("%15s", cities.get(i));

            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] == INF) {
                    System.out.printf("%15s", "INF");
                } else {
                    System.out.printf("%15d", adjacencyMatrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    
}