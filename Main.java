public class Main {

    public static void main(String[] args) {

        Graph graph = FileHelper.loadGraph("guategrafo.txt");

        graph.floyd();

        graph.distanceMatrix();

        System.out.println(
            "\nCentro del grafo: " +
            graph.graphCenter()
        );
    }
}