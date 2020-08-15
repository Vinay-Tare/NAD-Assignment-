import java.io.*; 

public class Euler_Method {
		// Consider a differential equation 
	    // dy/dx = (x + y) 
	    float func(float x, float y) 
	    { 
	        return (x + y ); 
	    } 
	  
	    void euler(float x0, float y, float h, float x) 
	    { 
	        while (x0 < x) { 
	            y = y + h * func(x0, y); 
	            x0 = x0 + h; 
	        }
	        
	        System.out.println("Approximate solution at x = "
	                           + x + " i.e. f(1) is:  " + y); 
	    } 
	  
	    public static void main(String args[]) throws IOException 
	    { 
	        Euler_Method obj = new Euler_Method(); 
	         
	        float x0 = 0; 
	        float y0 = 1; 
	        float h = 0.025f; 
	  
	        // Value of x at which we need approximation 
	        float x = 1; 
	  
	        obj.euler(x0, y0, h, x); 
	    } 
	} 

Output:
Approximate solution at x = 1.0 i.e. f(1) is:  3.4793813