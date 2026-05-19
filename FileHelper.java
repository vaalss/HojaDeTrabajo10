import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class FileHelper {
    public static Graph loadGraph(String fileName) {
        HashSet<String> cities = new HashSet<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

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

            Graph graph = new Graph(cities.size());
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
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
