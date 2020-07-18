import java.util.Random;

/* Simulated Annealing Minimum Vertex Cover */
public class SimulatedAnnealingMVC {
  int[][] matrix;
  int matrix_length;

  int solutions = 0;

  int vertices;
  int edges;

  double t_max;
  double k;
  double kt;
  double t_min;

  Random random = new Random();

  public void initialize(int[][] matrix, int vertices, int edges, double t_max, double k, double kt, double t_min) {
    this.matrix = new int[matrix.length][matrix.length];
    this.matrix_length = matrix.length;
    this.vertices = vertices;
    this.edges = edges;
    this.t_max = t_max;
    this.k = k;
    this.kt = kt;
    this.t_min = t_min;

    copyMatrix(matrix, this.matrix);
    simulate_anneal();
  }

  public void copyMatrix(int[][] matrix_copy, int[][] matrix_paste) {
    for (int i = 0; i < matrix_copy.length; i++) {
      for (int j = 0; j < matrix_copy.length; j++) {
        matrix_paste[i][j] = matrix_copy[i][j];
      }
    }
  }

  private void simulate_anneal() {
    double t = 0;
    double i = 0;

    double T = this.t_max;
    double T_min = this.t_min;
    String vertices_mvc_solution_VC = generate_course(this.matrix, this.edges);
    int aval_cover_VC = avaluate_cover(vertices_mvc_solution_VC);
    int aval_cover_VN = 0;

    while (T >= T_min) {
      while (i < this.kt) {
        String vertices_mvc_solution_VN = generate_course(this.matrix, this.edges);
        aval_cover_VN = avaluate_cover(vertices_mvc_solution_VN);

        if (aval_cover_VC < aval_cover_VN) {
          vertices_mvc_solution_VC = vertices_mvc_solution_VN;
        } else {
          double e = (aval_cover_VN - aval_cover_VC) / T;
          if (random.nextInt(1) < Math.exp(e)) {
            vertices_mvc_solution_VC = vertices_mvc_solution_VN;
          }
        }

        i = i + 1;
      }

      T = this.k * T;
      t = t + 1;
    }

    System.out.println("\n\nCobertura mínima encontrada é a solução: " + vertices_mvc_solution_VC);
    System.out.println("Número de polos da solução: " + aval_cover_VN + "\n\n");
  }

  public int avaluate_cover(String vertices_solution) {
    return vertices_solution.length();
  }

  private String generate_course(int[][] matrix, int edges) {
    String min_vertices_solution = "";
    int[][] course_matrix = new int[matrix.length][matrix.length];
    int course_matrix_length = course_matrix.length;
    int[] first_edge = new int[2];
    int first_vertex = 0;
    int edges_count = edges;
    int i = 0;
    int j = 0;
    int current_vertex = 0;
    int melhorLocal_i = 0;
    int melhorLocal_j = 0;

    copyMatrix(matrix, course_matrix);

    while (edges_count > 0) {
      /* Choose random edge */
      while (first_vertex == 0) {
        i = random.nextInt(course_matrix_length);
        j = random.nextInt(course_matrix_length);

        first_edge[0] = i;
        first_edge[1] = j;

        first_vertex = course_matrix[i][j];
      }
      first_vertex = 0;

      /* Choosing random vertex from first_edge */
      melhorLocal_i = 0;
      melhorLocal_j = 0;
      for (int a = 0; a < course_matrix_length; a++) {
        if (course_matrix[first_edge[0]][a] == 1) {
          melhorLocal_i = melhorLocal_i + 1;
        }
        if (course_matrix[first_edge[1]][a] == 1) {
          melhorLocal_j = melhorLocal_j + 1;
        }
      }

      if (melhorLocal_i >= melhorLocal_j) {
        current_vertex = first_edge[0];
      } else {
        current_vertex = first_edge[1];
      }

      /* Adding current vertex to MVC solution */
      min_vertices_solution = min_vertices_solution + (current_vertex + 1); // passando para index 1

      /* Choosing random edges from current_vertex */
      for (int k = 0; k < course_matrix_length; k++) {
        if (course_matrix[current_vertex][k] == 1) {
          course_matrix[current_vertex][k] = 0;
          edges_count = edges_count - 1;
        }

        if (course_matrix[k][current_vertex] == 1) {
          course_matrix[k][current_vertex] = 0;
        }
      }
    }

    solutions++;
    System.out.println("\nResultado parcial número " + solutions + ": " + min_vertices_solution + "\n");
    return min_vertices_solution;
  }

  public int index_1_to_0(int number, int matrix_length) {
    return number - 1;
  }
}