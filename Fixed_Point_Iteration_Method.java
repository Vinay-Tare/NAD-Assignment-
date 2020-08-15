import java.lang.Math;

public class Fixed_Point_Iteration_Method {

	public static double f(double x) {
		return (x * x - x - 1);
	}

	// Finding g(x)
	// x*x-x-1 = 0
	// x*x = x+1 => x = (x+1)^0.5 => g(x)

	public static double g(double x) {
		return (Math.pow((x + 1), 0.5));
	}

	public static void main(String[] args) {
		double p, p0, err;
		int itr = 1, mitr;

		p0 = 0; // p0 => approximate p
		err = 0.0001; // err => Desired Tolerance
		mitr = 1000; // mitr => Maximum Iterations

		while (itr <= mitr) {
			p = g(p0);

			if ((Math.abs(p - p0)) < err) {
				System.out.println(
						"The value of root by fixed point iteration method is : " + p + "\nNo. Of Iterations Done: " + itr);
				break;
			}

			itr++;
			p0 = p;

			if (itr > mitr) {
				System.out.println("Method Failed After " + mitr + " Iterations");
			}
		}
	}
}

Output:
The value of root by fixed point iteration method is : 1.6180165422314876
No. Of Iterations Done: 10
