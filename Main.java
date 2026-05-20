/**
 * Valeria Hernández 25086
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = FileHelper.loadGraph("guategrafo.txt");

        if (graph == null) {
            System.out.println("Grafo nulo");
            return;
        }

        graph.floyd();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenú:");
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar conexiones");
            System.out.println("4. Mostrar matriz de adyacencia");
            System.out.println("5. Mostrar matriz de Floyd");
            System.err.println("6. Salir");

            System.out.print("Selecciona la opciones que deseas realizar: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    System.out.print("\nCiudad origen: ");
                    String from = scanner.nextLine().trim();
                    System.out.print("Ciudad destino: ");
                    String to = scanner.nextLine();

                    graph.shortestPath(from, to);
                    break;
                case 2:
                    System.out.println("\nCentro del grafo: " + graph.graphCenter());
                    break;
                case 3:
                    System.out.println("\n\t1. Eliminar conexión");
                    System.out.println("\t2. Agregar conexión");
                    System.out.print("\tSelecciona la modificación que deseas realizar: ");
                    int modify_option = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (modify_option == 1) {
                        System.out.print("\n\tCiudad origen: ");
                        String from_ = scanner.nextLine().trim();
                        System.out.print("\tCiudad destino: ");
                        String to_ = scanner.nextLine().trim();

                        graph.removeEdge(from_, to_);
                        graph.floyd();
                        System.out.println("\tConexión eliminada.");
                    } else if (modify_option == 2) {
                        System.out.print("\n\tCiudad origen: ");
                        String _from = scanner.nextLine().trim();
                        System.out.print("\tCiudad destino: ");
                        String _to = scanner.nextLine().trim();

                        System.out.print("\tDistancia: ");
                        int distance = scanner.nextInt();
                        scanner.nextLine();

                        graph.addEdge(_from, _to, distance);
                        graph.floyd();
                        System.out.println("\tConexión agregada.");
                    }
                    break;
                case 4:
                    System.out.println("\nMatriz de adyacencia:");
                    graph.printMatrix();
                    break;
                case 5:
                    System.out.println("\nMatriz de Floyd:");
                    graph.distanceMatrix();
                    break;
                case 6:
                    exit = true;
                    break;
                default: 
                System.out.println("Opción inválida");
            }
        }
    }
}