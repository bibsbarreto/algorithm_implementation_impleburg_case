import java.util.Scanner;

/* Simulated Annealing Minimum Vertex Cover main class */
public class SAMVCMain {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String first_vertex;
    String second_vertex;

    /* Graph params */
    int vertices = Integer.parseInt(args[0]);
    int edges = Integer.parseInt(args[1]);

    /* Simulated annealing params */
    double t_max = Double.parseDouble(args[2]);
    double k = Double.parseDouble(args[3]);
    double kt = Double.parseDouble(args[4]);
    double t_min = Double.parseDouble(args[5]);

    int nodes = vertices;
    int matrix_input_lenght = edges;

    int matrix[][] = new int[nodes][nodes];

    int first_vertex_location;
    int second_vertex_location;

    /* Initializing simulated annealing */
    SimulatedAnnealingMVC simulated_annealing = new SimulatedAnnealingMVC();

    /* Initializing matrix */
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        matrix[i][j] = 0;
      }
    }
    System.out.println();

    try {
      /* Assigning matrix */
      for (int i = 0; i < matrix_input_lenght; i++) {
        first_vertex = sc.next();
        second_vertex = sc.next();

        first_vertex_location = simulated_annealing.index_1_to_0(Integer.parseInt(first_vertex), nodes);
        second_vertex_location = simulated_annealing.index_1_to_0(Integer.parseInt(second_vertex), nodes);

        matrix[first_vertex_location][second_vertex_location] = 1;
        matrix[second_vertex_location][first_vertex_location] = 1;
      }
    } catch (Exception e) {
      System.out.println("\n\nO algoritmo não suporta matrizes com index 0\n\n");
    }

    /* Reading matrix */
    System.out.println("\n-------------- Matriz lida --------------\n");
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("\n\n-------------- Arestas --------------\n");
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        if (matrix[i][j] == 1) {
          System.out.println(i + " " + j);
        }
      }
    }

    simulated_annealing.initialize(matrix, vertices, edges, t_max, k, kt, t_min);
    sc.close();
  }
}
