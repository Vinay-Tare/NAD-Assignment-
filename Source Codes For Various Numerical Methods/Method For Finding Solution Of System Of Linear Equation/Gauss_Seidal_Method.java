import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gauss_Seidal_Method {

	public static class Gauss_Seidal {

	    public static final int MAX_ITERATIONS = 100;
	    private double[][] M;

	    public Gauss_Seidal(double [][] matrix) { M = matrix; }

	    public void print()
	    {
	        int n = M.length;
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n + 1; j++)
	                System.out.print(M[i][j] + " ");
	            System.out.println();
	        }
	    }

	    public boolean transformToDominant(int r, boolean[] V, int[] R)
	    {
	        int n = M.length;
	        if (r == M.length) {
	            double[][] T = new double[n][n+1];
	            for (int i = 0; i < R.length; i++) {
	                for (int j = 0; j < n + 1; j++)
	                    T[i][j] = M[R[i]][j];
	            }

	            M = T;

	            return true;
	        }

	        for (int i = 0; i < n; i++) {
	            if (V[i]) continue;

	            double sum = 0;

	            for (int j = 0; j < n; j++)
	                sum += Math.abs(M[i][j]);

	            if (2 * Math.abs(M[i][r]) > sum) { // diagonally dominant?
	                V[i] = true;
	                R[r] = i;

	                if (transformToDominant(r + 1, V, R))
	                    return true;

	                V[i] = false;
	            }
	        }

	        return false;
	    }
	    
	    public boolean makeDominant()
	    {
	        boolean[] visited = new boolean[M.length];
	        int[] rows = new int[M.length];

	        Arrays.fill(visited, false);

	        return transformToDominant(0, visited, rows);
	    }

	    public void solve()
	    {
	        int iterations = 0;
	        int n = M.length;
	        double epsilon = 1e-15;
	        double[] X = new double[n]; // Approximations
	        double[] P = new double[n]; // Prev
	        Arrays.fill(X, 0);

	        while (true) {
	            for (int i = 0; i < n; i++) {
	                double sum = M[i][n]; // b_n

	                for (int j = 0; j < n; j++)
	                    if (j != i)
	                        sum -= M[i][j] * X[j];

	                // Update x_i to use in the next row calculation
	                X[i] = 1/M[i][i] * sum;   
	            }
	            
	            System.out.print("X_" + iterations + " = {");
	            for (int i = 0; i < n; i++)
	                System.out.print(X[i] + " ");
	            System.out.println("}");

	            iterations++;
	            if (iterations == 1) continue;

	            boolean stop = true;
	            for (int i = 0; i < n && stop; i++)
	                if (Math.abs(X[i] - P[i]) > epsilon)
	                       	stop = false;
	               
	            
	            if(stop)
	            {
	            	System.out.println("Solution Found At Iteration :"+(iterations-1)+" \nSolution is: X_"+(iterations-1));
	            	break;
	            }
	            if (iterations == MAX_ITERATIONS)
	            	{
	            	System.out.println("Reached Maxed No. Of Iterations ....But No Convergent Solution Found");
	            	break;
	            	}
	            P = (double[])X.clone();
	        }
	    }

	    public static void main(String[] args) throws IOException
	    {
	        int n;
	        double[][] M;

	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        PrintWriter writer = new PrintWriter(System.out, true);

	        n = Integer.parseInt(reader.readLine());
	        M = new double[n][n+1];

	        for (int i = 0; i < n; i++) {
	            StringTokenizer strtk = new StringTokenizer(reader.readLine());

	            while (strtk.hasMoreTokens())
	                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
	                    M[i][j] = Integer.parseInt(strtk.nextToken());
	        }


	        Gauss_Seidal Gauss_Seidal = new Gauss_Seidal(M);

	        if (!Gauss_Seidal.makeDominant()) {
	            writer.println("The system isn't diagonally dominant: " + 
	                    "The method cannot guarantee convergence.");
	        }

	        writer.println();
	        Gauss_Seidal.print();
	        Gauss_Seidal.solve();
	    }
	}
}

Output:

# (1st Run)
3
1 2 3 14
3 2 1 10
2 3 2 14
The system isn't diagonally dominant: The method cannot guarantee convergence.

1.0 2.0 3.0 14.0 
3.0 2.0 1.0 10.0 
2.0 3.0 2.0 14.0 
X_0 = {14.0 -16.0 17.0 }
X_1 = {-5.0 4.0 6.0 }
X_2 = {-12.0 20.0 -11.0 }
...
...
X_98 = {-12.0 20.0 -11.0 }
X_99 = {7.0 0.0 0.0 }
Reached Maxed No. Of Iterations ....But No Convergent Solution Found



# (2nd Run)

3
5 -2 3 -1
-3 9 1 2
2 -1 -7 3
The system isn't diagonally dominant: The method cannot guarantee convergence.

5.0 -2.0 3.0 -1.0 
-3.0 9.0 1.0 2.0 
2.0 -1.0 -7.0 3.0 
X_0 = {-0.2 0.15555555555555553 -0.5079365079365079 }
X_1 = {0.16698412698412698 0.334320987654321 -0.42862181909800956 }
X_2 = {0.19090148652053415 0.33348069762884575 -0.4216682463696825 }
X_3 = {0.1863932268733478 0.3312053252210806 -0.4226312673534835 }
...
...
X_16 = {0.18611987381703446 0.33123028391167175 -0.42271293375394325 }
X_17 = {0.18611987381703465 0.3312302839116719 -0.42271293375394325 }
Solution Found At Iteration :17 
Solution is: X_17
