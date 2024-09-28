import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
	double [] coefficents;
	int [] exponents;
	
	public Polynomial(){
		coefficents = new double [0];
		exponents = new int [0];
	}
	
	public Polynomial(File f){
		try{
		Scanner input = new Scanner(f);
		String poly = input.nextLine();
		String[] terms = poly.split("(?=[+-])");
		input.close();
		
		double [] coeff = new double [terms.length];
		int [] expo = new int [coeff.length];
		int count = 0;
		
		for (int i = 0; i < terms.length; i++) {
			if (terms[i].contains("x")) {
				double coefficent = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
				int exponent = Integer.parseInt(terms[i].substring(terms[i].indexOf("x") + 1));
				coeff[i] = coefficent;
				expo[i] = exponent;
			}
			else {
				coeff[i] = Double.parseDouble(terms[i]);
				expo[i] = 0;
			}
			count++;	}
			
			double [] coefficents22 = new double[count];
			int [] exponents22 = new int[count];
			for(int i = 0; i < count; i++) {
				coefficents22[i] = coeff[i];
				exponents22[i] = expo[i];
				
			}

			coefficents = coefficents22.clone();
			exponents = exponents22.clone();
		}

		catch (FileNotFoundException e) {
            System.err.println("File not found");
		}
	}
	
	public Polynomial(double [] arr, int [] arr2) {
		coefficents = arr.clone();
		exponents = arr2.clone();
		
	}
	
	public Polynomial add(Polynomial p) {
		
		double [] arr = new double [p.exponents.length + exponents.length];
		int [] expo = new int [p.coefficents.length + coefficents.length];
		int count = 0;
		for(int i = 0; i < coefficents.length; i++) {
			boolean check = false;
			for(int j = 0; j < p.coefficents.length; j++) {
				if (exponents[i] == p.exponents[j]) {
					arr[i] = coefficents[i] + p.coefficents[j];
					expo[i] = exponents[i];
					check = true;
					break;
				}
			}
			
			if (check == false){
				arr[i] = coefficents[i];
				expo[i] = exponents[i];
			}
			count++;
			
				}
		
		for(int i = 0; i < p.coefficents.length; i++) {
			boolean check = false;
			for(int j = 0; j < arr.length; j++) {
				if (p.exponents[i] == expo[j]) {
					check = true;
					break;
				}
				
			}
			
			if (check == false) {
				arr[count] = p.coefficents[i];
				expo[count] = p.exponents[i];
				count++;
			}
			}
		
		double [] arr1 = new double [count];
		int [] expo1 = new int [count];
		for(int i = 0; i < count; i++) {
			
			arr1[i] = arr[i];
			expo1[i] = expo[i];
		
		}
		
		Polynomial p1 = new Polynomial(arr1, expo1);
		return p1;
			}
		
		

	
	public double evaluate(double d){
		
		double total = 0;
		for(int i = 0; i < coefficents.length; i++) {
			
			total += coefficents[i] * Math.pow(d, exponents[i]);
		}
		
		return total;
		
	}
	
	public boolean hasRoot(double root) {
		
		return evaluate(root) == 0;
		
	}
	
	public Polynomial multiply(Polynomial p) {
		
		double [] arr = new double [p.exponents.length + exponents.length];
		int [] expo = new int [p.coefficents.length + coefficents.length];
		int count = 0;
		
		for(int i = 0; i < coefficents.length; i++) {
			for(int j = 0; j < p.coefficents.length; j++) {
				
				arr[count] = coefficents[i] * p.coefficents[j];
				expo[count] = exponents[i] + p.exponents[j];
				count++;
		}}
		
		Polynomial product = new Polynomial(new double [1], new int [1]);
		
		for (int i = 0; i < count; i++) {
			double[] coeff = new double[1];
			coeff[0] = arr[i];
			int[] expos = new int[1];
			expos[0] = expo[i];
			product = product.add(new Polynomial(coeff, expos));
			
		}
		
		return product;
		}
	
	public void saveToFile(String file){
		try{
		File f = new File(file);
		FileWriter output = new FileWriter(f, true);
		for (int i = 0; i < coefficents.length; i++) {
			if(i == 0) {
				if (coefficents[i] < 0) {
					output.write("-");
				}
            }
			else {
				if (coefficents[i] >= 0) {
			        output.write("+");
			    } else {
			        output.write("-");
			    }
            } 
			
			if (coefficents[i] != 0.0) {
			int c1 = (int) coefficents[i];
			output.write(String.valueOf(Math.abs(c1)));
			}

            if (exponents[i] > 0) {
                output.write("x" + exponents[i]);
		}
            
		}
		output.close();
	}

	catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("An I/O error occurred");
        }
	}
	   	    

}






