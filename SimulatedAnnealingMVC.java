import java.util.Random;

// Simulated Annealing Minimum Vertex Cover
public class SimulatedAnnealingMVC {
  int[][] matrix;
  int matrix_length;

  double t_max;
  double k;
  double kt;
  double t_min;

  Random random = new Random();

  public void set_matrix(int[][] matrix) {
    this.matrix = matrix;
  }

  public int[][] get_matrix() {
    return this.matrix;
  }

  public void initialize(int[][] matrix, double t_max, double k, double kt, double t_min) {
    this.matrix = matrix;
    this.matrix_length = matrix.length;
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
    int[] course = generate_course(this.matrix_length);
    double aval_vc = avaluate_weight(course, this.matrix_length);

    while (T >= T_min) {
      while (i < this.kt) {
        int[] neighboorhood = generate_neighboor(course);
        double aval_vn = avaluate_weight(neighboorhood, this.matrix_length);

        if (aval_vn < aval_vc) {
          course = neighboorhood;
        } else {
          double e = (aval_vc - aval_vn) / T;
          if (random.nextInt(1) < Math.exp(e)) {
            course = neighboorhood;
          }
        }

        i = i + 1;
      }

      T = this.k * T;
      t = t + 1;

      System.out.println("\nMelhor peso:    " + avaluate_weight(course, this.matrix_length));
    }
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

  /*
   * O que eu acho: não vai mais ser por peso, devemos avaliar o número de arestas
   * que foram usadas (qnt menos, melhor)
   * 
   */
  public double avaluate_weight(int[] course, int matrix_length) {
    double sum = 0;
    for (int x = 0; x < matrix_length; x++) {
      if (!((x + 1) >= matrix_length)) {
        sum = sum + matrix[course[x]][course[x + 1]];
      } else {
        sum = sum + matrix[matrix_length - 1][0];
      }
    }
    return sum;
  }

  /*
   * O que eu acho: pega um número aleatório, que vai ser o primeiro vértice,
   * 
   */
  private int[] generate_course(int matrix_length) {
    int[] course = new int[matrix_length];
    int[] visited = new int[matrix_length];

    for (int i = 0; i < visited.length; i++) {
      visited[i] = 0;
    }

    int initial = random.nextInt(matrix_length - 1);
    visited[initial] = 1;
    int i = 0;
    int origin = initial;
    int destination = 0;

    while (i < matrix_length - 1) {
      destination = random.nextInt(matrix_length);

      if (visited[destination] == 0) {
        visited[destination] = 1;
        course[i] = origin;
        origin = destination;
        i++;
      }
    }

    course[course.length - 1] = destination;
    return course;
  }

  /*
   * O que eu acho: pegamos uma ordem de vértices olhando a matriz e adicionando
   * os elementos que tem 1
   * 
   */
  public int[] generate_neighboor(int[] course) {
    double best_weight = 9999999;
    int weight_i = 0;
    int weight_j = 0;
    int[] vc;
    System.out.println();
    System.out.println();
    System.out.println();

    for (int c = 0; c < course.length; c++) {
      System.out.print(course[c] + " ");
    }
    System.out.print(course[0]);

    System.out.println();
    System.out.println();

    for (int i = 0; i < course.length; i++) {
      for (int j = 1; j < course.length; j++) {
        vc = two_opt(course, i, j);
        double weight = avaluate_weight(vc, vc.length);

        if (weight <= best_weight) {
          best_weight = weight;
          weight_i = i;
          weight_j = j;
        }
      }
    }

    int[] vn;
    vn = two_opt(course, weight_i, weight_j);

    return vn;
  }

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

  // TODO: not working
  public int get_valid_matix_number(int number, int matrix_length) {
    int new_number;
    // if (number == matrix_length + 1) {
    // new_number = 0;
    // } else {
    new_number = number - 1;
    // }

    return new_number;
  }
}