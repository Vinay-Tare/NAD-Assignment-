public class Gauss_Seidal_Method{
	
static final int X = 2;
	
public static void main(String[] args)
{
    float ae, max,t,s,e;
    int i,j,r,mxit;
    
    ae = (float) 0.0001; // allowed error
    mxit=10; //max no. of iterations
    
    float x[][] =  { 	{ 2,-9,8 }, 
    					{ -7,4,8 }	};
    
    float[] a = new float[X];
  
    for(i=0;i<X;i++) {
    	a[i]=0;
    }
   
    System.out.printf("Iteration\tx[1]\tx[2]\n");
    for(r=1;r<=mxit;r++)
    {
        max=0;
        for(i=0;i<X;i++)
        {
            s=0;
            for(j=0;j<X;j++)
            if(j!=i) s+=x[i][j]*a[j];
            t=(x[i][X]-s)/x[i][i];
            e=Math.abs(a[i]-t);
            a[i]=t;
        }
        System.out.printf(" %5d\t",r);
        for(i=0;i<X;i++)
        System.out.printf(" %9.4f\t",a[i]);
        System.out.printf("\n");
        if(max<ae)
        {
        	System.out.printf(" Converses in %3d iteration\n", r);
            for(i=0;i<X;i++)
            System.out.printf("a[%d]=%7.4f\n", i+1,a[i]);
            return ;
        }
 
       }
    } 
}

Output:

Iteration	x[1]	x[2]
     1	    4.0000	    9.0000	
 Converses in   1 iteration
a[1]= 4.0000
a[2]= 9.0000