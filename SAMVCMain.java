import java.util.Scanner;

// Simulated Annealing Minimum Vertex Cover main class
public class SAMVCMain {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String first_vertex;
    String second_vertex;

    // Graph params
    int vertices = Integer.parseInt(args[0]);
    int edges = Integer.parseInt(args[1]);

    // Simulated annealing params
    double t_max = Double.parseDouble(args[2]);
    double k = Double.parseDouble(args[3]);
    double kt = Double.parseDouble(args[4]);
    double t_min = Double.parseDouble(args[5]);

    int nodes = vertices;
    int matrix_input_lenght = edges;

    int matrix[][] = new int[nodes][nodes];

    int first_vertex_location;
    int second_vertex_location;

    // Initializing simulated annealing
    SimulatedAnnealingMVC simulated_annealing = new SimulatedAnnealingMVC();

    // Initializing matrix
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        matrix[i][j] = 0;
      }
    }
    System.out.println();
    int edges_count = 0;

    // Assigning matrix
    for (int i = 0; i < matrix_input_lenght; i++) {
      first_vertex = sc.next();
      second_vertex = sc.next();

      first_vertex_location = simulated_annealing.get_valid_matix_number(Integer.parseInt(first_vertex), nodes);
      System.out.println("first_vertex:" + first_vertex);
      System.out.println("first_vertex_location:" + first_vertex_location);

      second_vertex_location = simulated_annealing.get_valid_matix_number(Integer.parseInt(second_vertex), nodes);
      System.out.println("second_vertex:" + second_vertex);
      System.out.println("second_vertex_location:" + second_vertex_location);

      System.out.println("--------------");
      matrix[first_vertex_location][second_vertex_location] = 1;
      matrix[second_vertex_location][first_vertex_location] = 1;
    }

    // Reading matrix
    System.out.println("\n--------------Matriz lida --------------\n");
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        System.out.print(matrix[i][j] + " ");

        if (matrix[i][j] == 1) {
          edges_count++;
        }
      }
      System.out.println();
    }

    System.out.println("\n\nTESTE\n" + edges_count + "\n\n"); // TODO: remove

    simulated_annealing.initialize(matrix, t_max, k, kt, t_min);
    sc.close();
  }
}
