
public class Secant_Method {

	public void secant(double x1, double x2, double err) {

		double xm, x0, c;
		if (func(x1) * func(x2) < 0) {

			do {
				x0 = (x1 * func(x2) - x2 * func(x1)) / (func(x2) - func(x1));
				c = func(x1) * func(x0);
				x1 = x2;
				x2 = x0;
				if (c == 0)
					break;
				xm = (x1 * func(x2) - x2 * func(x1)) / (func(x2) - func(x1));
			} while (Math.abs(xm - x0) >= err);

			System.out.println("The value of root by secant method is: " + x0);
		} else
			System.out.print("Root Not Found For The Following Interval");
	}

	public double func(double x) {
		return (x * x - x - 1);
	}

	public static void main(String[] args) {
		double x1 = 1, x2 = 2, err = 0.0001;
		Secant_Method sec = new Secant_Method();
		sec.secant(x1, x2, err);
	}
}

Output:
The value of root by secant method is: 1.6180257510729614
