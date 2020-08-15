
public class Lagrange_Interpolation {

	public double interpolate_points(Point f[], int xi, int n) {
		double func_val = 0;

		for (int i = 0; i < n; i++) {

			double term = f[i].y;
			for (int j = 0; j < n; j++) {
				if (j != i)
					term = term * (xi - f[j].x) / (f[i].x - f[j].x);
			}

			func_val += term;
		}

		return func_val;
	}

	public class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	};

	public static void main(String[] args) {
		Lagrange_Interpolation li = new Lagrange_Interpolation();

		Point f[] = { li.new Point(5, 150), li.new Point(7, 392), li.new Point(11, 1452), li.new Point(13, 2366),
				li.new Point(17, 5202) };

		System.out.println("Value of function at x=9 i.e. f(9) by lagrange's interpolation method is: "
				+ (int) li.interpolate_points(f, 9, 5));
	}
}

Output:
Value of function at x=9 i.e. f(9) by lagrange's interpolation method is: 810
