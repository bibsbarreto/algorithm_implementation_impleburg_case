import java.util.Random;

// Simulated Annealing Minimum Vertex Cover
public class SimulatedAnnealingMVC {
  int[][] matrix;

  Random random = new Random();

  public void set_matrix(int[][] matrix) {
    this.matrix = matrix;
  }

  public int[][] get_matrix() {
    return this.matrix;
  }

  // TODO: matrix_length + 1
  public int get_valid_matix_number(int number, int matrix_length) {
    int new_number;
    if (number == matrix_length + 1) {
      new_number = 0;
    } else {
      new_number = number - 1;
    }

    return new_number;
  }
}