/**
 * Valeria Hernández 25086
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Clase auxiliar para cargar un grafo desde un archivo de texto.
 */
public class FileHelper {
    
    /**
     * Lee un archivo y construye un grafo dirigido.
     * 
     * Formato esperado:
     * CiudadOrigen CiudadDestino Distancia
     * 
     * @param fileName nombre del archivo a leer
     * @return grafo construido a partir del archivo
     */
    public static Graph loadGraph(String fileName) {
        HashSet<String> cities = new HashSet<>(); // almacena ciudades únicas para determinar el tamaño incicial del grafo

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file); //primera lectura para obtener las ciudades únicas

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String parts[] = line.split(" ");

                cities.add(parts[0]);
                cities.add(parts[1]);
                
            }
            scanner.close();

            Graph graph = new Graph(cities.size()); //se crea el grafo con el número de ciudades únicas
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) { //segunda lectura para llenar el grafo con las conexiones
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ");

                String from = parts[0];
                String to = parts[1];
                int distance = Integer.parseInt(parts[2]);

                graph.addEdge(from, to, distance);
            }
            scanner.close();
            return graph;

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        
        return null;
    }
}
