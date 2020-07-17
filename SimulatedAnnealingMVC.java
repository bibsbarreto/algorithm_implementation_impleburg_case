import java.util.Random;

/* Simulated Annealing Minimum Vertex Cover */
public class SimulatedAnnealingMVC {
  int[][] matrix;
  int matrix_length;

  int vertices;
  int edges;

  double t_max;
  double k;
  double kt;
  double t_min;

  Random random = new Random();

  public void initialize(int[][] matrix, int vertices, int edges, double t_max, double k, double kt, double t_min) {
    this.matrix = new int[matrix.length][matrix.length];
    copyMatrix(matrix, this.matrix);
    this.matrix_length = matrix.length;
    this.vertices = vertices;
    this.edges = edges;
    this.t_max = t_max;
    this.k = k;
    this.kt = kt;
    this.t_min = t_min;

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
    String vertices_mvc_solution = generate_course(this.matrix, this.edges);
    int aval_cover = avaluate_cover(vertices_mvc_solution);

    // while (T >= T_min) {
    // while (i < this.kt) {
    // int[] neighboorhood = generate_neighboor(course);
    // double aval_vn = avaluate_cover(neighboorhood, this.matrix_length);

    // if (aval_vn < aval_cover) {
    // course = neighboorhood;
    // } else {
    // double e = (aval_cover - aval_vn) / T;
    // if (random.nextInt(1) < Math.exp(e)) {
    // course = neighboorhood;
    // }
    // }

    // i = i + 1;
    // }

    // T = this.k * T;
    // t = t + 1;

    System.out.println("\n\nCobertura mínima encontrada: " + avaluate_cover(vertices_mvc_solution));
    // }
  }

  public int avaluate_cover(String vertices_solution) {
    return vertices_solution.length();
  }

  private String generate_course(int[][] matrix, int edges) { // 8
    String min_vertices_solution = "";
    int[][] course_matrix = new int[matrix.length][matrix.length];
    int course_matrix_length = course_matrix.length;
    int[] first_edge = new int[2];
    int first_vertex = 0;
    int edges_count = edges; // 8
    System.out.println("edges_count: " + edges_count + "\n\n");
    int i = 0;
    int j = 0;
    int current_vertex = 0;

    copyMatrix(matrix, course_matrix);

    while (edges_count > 0) {
      /* Choose random edge */
      while (first_vertex == 0) {
        i = random.nextInt(course_matrix_length - 1);
        j = random.nextInt(course_matrix_length - 1);
        System.out.println("course_matrix_length: " + course_matrix_length);
        // System.out.println("i: " + i);
        // System.out.println("j: " + j);

        first_edge[0] = i;
        System.out.println("first_edge[0: " + first_edge[0]);
        first_edge[1] = j;
        System.out.println("first_edge[1: " + first_edge[1]);

        first_vertex = course_matrix[i][j];
        System.out.println("first_vertex: " + first_vertex);
        for (int x = 0; x < course_matrix_length; x++) {
          for (int y = 0; y < course_matrix_length; y++) {
            System.out.print(matrix[i][j] + " ");
          }
          System.out.println();
        }
        System.out.println("first_vertex: " + first_vertex);
      }
      first_vertex = 0;
      /* Choosing random vertex from first_edge */
      current_vertex = first_edge[random.nextInt(1)];
      System.out.println("\nfirst_edge[0]: " + first_edge[0]);
      System.out.println("\nfirst_edge[1]: " + first_edge[1]);

      /* Adding current vertex to MVC solution */
      min_vertices_solution = min_vertices_solution + (current_vertex + 1); // passando para index 1
      System.out.println("2 - min_vertices_solution: " + min_vertices_solution);

      /* Choosing random edges from current_vertex */
      for (int k = 0; k < course_matrix_length; k++) {
        if (course_matrix[current_vertex][k] == 1) {
          course_matrix[current_vertex][k] = 0;
          edges_count = edges_count - 1;
          System.out.println("\nREMOVENDO current_vertex: " + current_vertex);
          System.out.println("REMOVENDO k: " + k + "\n");
          System.out.println("edges_count: " + edges_count + "\n");
        }

        if (course_matrix[k][current_vertex] == 1) {
          course_matrix[k][current_vertex] = 0;
          System.out.println("REMOVENDO k: " + k);
          System.out.println("REMOVENDO current_vertex: " + current_vertex + "\n");
          System.out.println("edges_count: " + edges_count + "\n");
        }
      }
      System.out.println("FINAL edges_count: " + edges_count + "\n");
    }

    System.out.println("3 - min_vertices_solution: " + min_vertices_solution);
    return min_vertices_solution;
  }

  // TODO
  public int[] generate_neighboor(int[] course) {
    return course;
  }

  // TODO
  public int[] two_opt(int[] route, int i, int j) {
    int[] vn = new int[route.length];

    for (int c = 0; c < route.length; c++) {
      vn[c] = route[c];
    }

    int aux = vn[i];
    vn[i] = vn[j];
    vn[j] = aux;

    for (int b = 0; b < vn.length; b++) {
      System.out.print(vn[b] + " ");
    }
    System.out.print(vn[0]);

    return vn;
  }

  public int index_1_to_0(int number, int matrix_length) {
    return number - 1;
  }
}