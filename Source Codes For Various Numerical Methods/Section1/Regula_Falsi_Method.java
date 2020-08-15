
public class Regula_Falsi_Method {

	static int mitr = 1000000;

	public void regulaFalsi(double a, double b) {
		if (func(a) * func(b) >= 0) {
			System.out.println("Please Enter Another Interval i.e a and b");
		}

		double c = a;

		for (int i = 0; i < mitr; i++) {
			c = (a * func(b) - b * func(a)) / (func(b) - func(a));

			if (func(c) == 0)
				break;

			else if (func(c) * func(a) < 0)
				b = c;
			else
				a = c;
		}
		System.out.println("The value of root by regula falsi method is: " + c);
	}

	public double func(double x) {
		return (x * x - x - 1);
	}

	public static void main(String[] args) {
		double a = 1, b = 2;
		Regula_Falsi_Method rf = new Regula_Falsi_Method();
		rf.regulaFalsi(a, b);
	}
}

Output:
The value of root by regula falsi method is: 1.618033988749895
