/**
 * Valeria Hernández 25086
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementación de un grafo dirigido usando la matriz de adyacencia
 * Usa el algoritmo de Floyd para calcular rutas mínimas y el centro del grafo
 */
public class Graph {

    private static final int INF = 999999; //valor para representar infinito

    private ArrayList<String> cities;
    private HashMap<String, Integer> cityIndex;
    private int [][] adjacencyMatrix;
    private int [][] distanceMatrix; //distancia mínima
    private int [][] pathMatrix; //nodos intermedios
    private int size;

    /**
     * Constructor del grafo
     * 
     * @param maxCities cantidad inicial de ciudades
     */
    public Graph(int maxCities) {
        cities = new ArrayList<>();
        cityIndex = new HashMap<>();

        adjacencyMatrix = new int[maxCities][maxCities];
        
        size = 0;

        matrixInit(maxCities);
    }

     /**
     * Inicializa la matriz de adyacencia
     */
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
            if (size >= adjacencyMatrix.length) { //si es necesario, redimensiona la matriz
                resize();
            }
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

    public void printMatrix() { //imprime la matriz de adyacencia
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

    /**
     * Inicializa las matrices utilizadas por Floyd
     */
    private void floydInit() {
        distanceMatrix = new int[size][size];
        pathMatrix = new int[size][size];

        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                distanceMatrix[i][j] = adjacencyMatrix[i][j];
                pathMatrix[i][j] = -1; //-1 si no hay nodos intermedios
            }
        }
    }

    public void floyd() {
        floydInit();
        for (int k = 0; k < size; k ++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {

                    if (distanceMatrix[i][k] != INF && distanceMatrix[k][j] != INF && 
                        distanceMatrix[i][j] > distanceMatrix[i][k] + distanceMatrix[k][j]) {
                            
                            distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                            pathMatrix[i][j] = k;
                    } 
                }
            }
        }
    }

    public void distanceMatrix() { 

        System.out.println("\nMatriz de distancias mínimas");

        System.out.printf("%15s", "");

        for (String city : cities) {
            System.out.printf("%15s", city);
        }

        System.out.println();

        for (int i = 0; i < size; i++) {

            System.out.printf("%15s", cities.get(i));

            for (int j = 0; j < size; j++) {

                if (distanceMatrix[i][j] == INF) {
                    System.out.printf("%15s", "INF");
                } else {
                    System.out.printf("%15d", distanceMatrix[i][j]);
                }
            }

            System.out.println();
        }
    }

    /**
     * Construye la ruta más corta entre dos ciudades usando la matriz de nodos intermedios de forma recursiva
     * @param i
     * @param j
     * @param path
     */
    private void path(int i, int j, ArrayList<String> path) {
        int intermediate = pathMatrix[i][j];
        if (intermediate == -1) {
            return;
        }
        path(i, intermediate, path);
        path.add(cities.get(intermediate));
        path(intermediate, j, path);
    }

    public void shortestPath(String from, String to) {
        if (!cityIndex.containsKey(from) || !cityIndex.containsKey(to)) {
            System.out.println("Ciudad no encontrada");
            return;
        } 

        int i = cityIndex.get(from);
        int j = cityIndex.get(to);

        if(distanceMatrix[i][j] == INF) {
            System.out.println("No existe la ruta");
            return;
        }

        ArrayList<String> path = new ArrayList<>();
        path.add(from);
        path(i, j, path);
        path.add(to);

        System.out.println("\nRuta más corta: ");

        for (int k = 0; k < path.size(); k ++) {
            System.out.print(path.get(k));

            if (k < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nDistancia total: " + distanceMatrix[i][j] + " km");

    }

    /**
     * Calcula el centro del grafo.
     * 
     * @return ciudad considerada centro del grafo
     */
    public String graphCenter() {
        int minEccentricity = INF;
        String center = "";

        for (int i = 0; i < size; i ++) {
            int eccentricity = 0;

            for (int j = 0; j < size; j ++) {
                if (distanceMatrix[i][j] != INF && distanceMatrix[i][j] > eccentricity) {
                    eccentricity = distanceMatrix[i][j];
                }
            }

            if (eccentricity < minEccentricity) {
                minEccentricity = eccentricity;
                center = cities.get(i);
            }
        }
        return center;
    }
    
    /**
     * Redimensiona dinámicamente la matriz del grafo si se necesita más espacio
     */
    private void resize() {
        int newSize = adjacencyMatrix.length * 2;
        int[][] newMatrix = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                if (i == j) {
                    newMatrix[i][j] = 0;
                } else {
                    newMatrix[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                newMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        
        adjacencyMatrix = newMatrix;
    }
    
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public int getCityIndex(String city) {
        return cityIndex.get(city);
    }
}