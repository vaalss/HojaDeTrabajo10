public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph(10);

        graph.addEdge("Mixco", "Antigua", 30);
        graph.addEdge("Antigua", "Escuintla", 25);
        graph.addEdge("Escuintla", "SantaLucia", 15);

        System.out.println("Matriz original: ");
        graph.printMatrix();

        System.out.println();

        graph.removeEdge("Antigua", "Escuintla");

        System.out.println("Matriz después de la eliminación: ");
        graph.printMatrix();
    }
}
