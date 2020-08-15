
public class Newton_Raphson_Method {

	static final double err = 0.0001;

	static double func(double x) {
		return (x * x - x - 1);
	}

	public double d_Func(double x) {
		return (2 * x - 1);
	}

	public void newtonRaphson(double x) {
		double k = func(x) / d_Func(x);
		while (Math.abs(k) >= err) {
			k = func(x) / d_Func(x);

			x = x - k;
		}

		System.out.println("The value of root by newton raphson method is :" + x);
	}

	public static void main(String[] args) {

		double x0 = 10;
		Newton_Raphson_Method nrm = new Newton_Raphson_Method();
		nrm.newtonRaphson(x0);
	}
}

Output:
The value of root by newton raphson method is :1.6180339887500548
