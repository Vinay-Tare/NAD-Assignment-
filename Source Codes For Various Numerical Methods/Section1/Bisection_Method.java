class Bisection_Method {

	static final double err = 0.0001;

	public void bisection(double a, double b) {
		if (func(a) * func(b) >= 0) {
			System.out.println("Please Enter Another Interval i.e a and b");
			return;
		}

		double c = a;
		while ((b - a) >= err) {

			c = (a + b) / 2;

			if (func(c) == 0.0)
				break;

			else if (func(c) * func(a) < 0)
				b = c;
			else
				a = c;
		}

		System.out.println("The value of root by bisection method is :" +c);
	}

	public double func(double x) {
		return (x * x - x - 1);
	}

	public static void main(String[] args) {

		double a = 1, b = 2;
		Bisection_Method bisc = new Bisection_Method();
		bisc.bisection(a, b);
	}
}

Output:
The value of root by bisection method is :1.61798095703125