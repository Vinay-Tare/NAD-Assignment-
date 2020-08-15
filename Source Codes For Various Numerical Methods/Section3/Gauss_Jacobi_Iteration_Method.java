import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gauss_Jacobi_Iteration_Method {

	public static class Jacobi {

	    public static final int MAX_ITERATIONS = 100;
	    private double[][] M;

	    public Jacobi(double [][] matrix) { M = matrix; }

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
	        Arrays.fill(P, 0);

	        while (true) {
	            for (int i = 0; i < n; i++) {
	                double sum = M[i][n]; // b_n

	                for (int j = 0; j < n; j++)
	                    if (j != i)
	                        sum -= M[i][j] * P[j];
	                
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


	        Jacobi jacobi = new Jacobi(M);

	        if (!jacobi.makeDominant()) {
	            writer.println("The system isn't diagonally dominant: " + 
	                    "The method cannot guarantee convergence.");
	        }

	        writer.println();
	        jacobi.print();
	        jacobi.solve();
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
X_0 = {14.0 5.0 7.0 }
X_1 = {14.0 5.0 7.0 }
X_2 = {-17.0 -19.5 -14.5 }
X_3 = {96.5 37.75 53.25 }
...
...
X_98 = {-7.811604549969776E47 -4.638576110026869E47 -4.845087920100023E47 }
X_99 = {2.381241598035381E48 1.4139950785004675E48 1.476946871501008E48 }
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
X_0 = {-0.2 0.2222222222222222 -0.42857142857142855 }
X_1 = {-0.2 0.2222222222222222 -0.42857142857142855 }
X_2 = {0.146031746031746 0.20317460317460315 -0.5174603174603174 }
X_3 = {0.1917460317460317 0.328395061728395 -0.4158730158730159 }
...
...
X_25 = {0.18611987381703832 0.3312302839116739 -0.4227129337539405 }
X_26 = {0.18611987381703385 0.3312302839116728 -0.4227129337539424 }
X_27 = {0.1861198738170346 0.33123028391167153 -0.4227129337539436 }
X_28 = {0.18611987381703476 0.3312302839116719 -0.42271293375394314 }
Solution Found At Iteration :28
Solution is: X_28 