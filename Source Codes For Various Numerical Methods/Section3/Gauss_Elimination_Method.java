import java.lang.Math;

public class Gauss_Elimination {
	static final int N = 3; // Number of unknowns

	public void gaussianElimination(double mat[][]) {
		int singular_flag = forwardElim(mat);

		if (singular_flag != -1) {
			System.out.println("Singular Matrix.");

			if (!(mat[singular_flag][N] == 0))
				System.out.println("Inconsistent System.");
			else
				System.out.println("May have infinitely many solutions.");

			return;
		}

		backSub(mat);
	}

	public void swap_row(double[][] mat, int i, int j) {

		for (int k = 0; k <= N; k++) {
			double temp = mat[i][k];
			mat[i][k] = mat[j][k];
			mat[j][k] = temp;
		}
	}

	// function to print matrix content at any stage
	public void print(double[][] mat) {
		for (int i = 0; i < N; i++) {
			System.out.println("\n");
			for (int j = 0; j <= N; j++)
				System.out.println(mat[i][j]);
		}

		System.out.println("\n");
	}

	int forwardElim(double[][] mat) {
		for (int k = 0; k < N; k++) {

			int i_max = k;
			int v_max = (int) mat[i_max][k];

			for (int i = k + 1; i < N; i++)
				if (Math.abs(mat[i][k]) > v_max) {
					v_max = (int) mat[i][k];
					i_max = i;
				}

			if ((mat[k][i_max] == 0))
				return k;

			if (i_max != k)
				swap_row(mat, k, i_max);

			for (int i = k + 1; i < N; i++) {
				double f = mat[i][k] / mat[k][k];

				for (int j = k + 1; j <= N; j++)
					mat[i][j] -= mat[k][j] * f;

				mat[i][k] = 0;
			}

		}

		return -1;
	}

	void backSub(double[][] mat) {
		double[] x = new double[N];

		for (int i = N - 1; i >= 0; i--) {
			x[i] = mat[i][N];

			for (int j = i + 1; j < N; j++) {
				x[i] -= mat[i][j] * x[j];
			}

			x[i] = x[i] / mat[i][i];
		}

		System.out.println("\nSolution for the system:\n");
		for (int i = 0; i < N; i++)
			System.out.printf("Value For Variable %d : %.1f\n", i + 1, x[i]);
	}

	public static void main(String[] args) {

		// The System Of Linear Equation Is:
		// x+2y+3z=14 , 3x+2y+z=10 , 2x+3y+2z=14
		double mat[][] = { { 1.0, 2.0, 3.0, 14.0 }, 
                           { 3.0, 2.0, 1.0, 10.0 },
                           { 2.0, 3.0, 2.0, 14.0 } };
		Gauss_Elimination ge = new Gauss_Elimination();
		ge.gaussianElimination(mat);
	}
}

Output:
Solution for the system:

Value For Variable 1 : 1.0
Value For Variable 2 : 2.0
Value For Variable 3 : 3.0