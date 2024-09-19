public class Polynomial {
	double [] coefficents;
	
	public Polynomial(){
		coefficents = new double [1];
		coefficents[0] = 0;
	}
	
	public Polynomial(double [] arr) {
		coefficents = arr;
		
	}
	
	public Polynomial add(Polynomial p) {
		int len;
		
		if (p.coefficents.length < coefficents.length) {
			
			len = coefficents.length;
		}
		else {
				
				len = p.coefficents.length;			
		}
		
		double [] arr = new double [len];
			
		for(int i = 0; i < len; i++) {
			
			if (i >= coefficents.length) {
				arr[i] = p.coefficents[i];
				
			}
			
			else if(i >= p.coefficents.length) {
				arr[i] = coefficents[i];
				
			}
			else {
				
				arr[i] = coefficents[i] + p.coefficents[i];
			}
			
		}
		
		Polynomial poly = new Polynomial(arr);
		
		return poly;
	}
	
	public double evaluate(double d){
		
		double total = coefficents[0];
		for(int i = 1; i < coefficents.length; i++) {
			
			total += coefficents[i] * Math.pow(d, i);
		}
		
		return total;
		
	}
	
	public boolean hasRoot(double root) {
		
		return evaluate(root) == 0;
		
	}
	   	    

}