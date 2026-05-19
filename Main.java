public class Main {

    public static void main(String[] args) {

        Graph graph = FileHelper.loadGraph("guategrafo.txt");

        if (graph != null) {

            System.out.println("Grafo cargado desde archivo");
            graph.printMatrix();

        } else {

            System.out.println("No se pudo cargar el grafo.");
        }
    }
}