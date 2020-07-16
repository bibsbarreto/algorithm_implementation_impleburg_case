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
    this.matrix = matrix;
    this.matrix_length = matrix.length;
    this.vertices = vertices;
    this.edges = edges;
    this.t_max = t_max;
    this.k = k;
    this.kt = kt;
    this.t_min = t_min;

    simulate_anneal();
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

    System.out.println("\n\nMelhor peso:    " + avaluate_cover(vertices_mvc_solution));
    // }
  }

  /*
   * código do visualgo:
   * 
   * Assign arbitrary order to the edges
   * 
   * V = {}
   * 
   * foreach (edge e in edgeList)
   * 
   * Take one endpoint at random
   * 
   * Remove edges incident to the chosen endpoint
   * 
   * VC = V
   */

  public int avaluate_cover(String vertices_solution) {
    return vertices_solution.length();
  }

  private String generate_course(int[][] matrix, int edges) {
    String min_vertices_solution = "";
    System.out.println("1 - min_vertices_solution: " + min_vertices_solution);
    int[] first_edge = new int[2];
    int first_vertex = 0;
    int edges_count = edges;
    int i = 0;
    int j = 0;
    int current_vertex = 0;

    do {
      /* Choose random edge */
      do {
        i = random.nextInt(matrix.length - 1); // 0
        j = random.nextInt(matrix.length - 1); // 2
        System.out.println("i: " + i);
        System.out.println("j: " + j);

        first_edge[0] = i; // 0
        first_edge[1] = j; // 2
        first_vertex = matrix[i][j];
        System.out.println("first_vertex: " + first_vertex);
      } while (first_vertex == 1);

      /* Choose random vertex from first_edge */
      current_vertex = first_edge[0]; // 0

      /* Adding current vertex to MVC solution */
      min_vertices_solution = min_vertices_solution + current_vertex;
      System.out.println("2 - min_vertices_solution: " + min_vertices_solution);

      /* Choose random vertex from first_edge */
      for (int k = 0; k < matrix.length; k++) {
        if (matrix[current_vertex][k] == 1) {
          matrix[current_vertex][k] = 0;
          matrix[k][current_vertex] = 0;

          edges_count = edges_count - 2; // Era isso o bug
        }
      }
    } while (edges_count > 0);

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