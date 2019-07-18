//-----------------------------------------------------------------------------
//  Sparse.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA3
//-----------------------------------------------------------------------------

import java.io.*; 
import java.util.*; 

public class Sparse{

	public static void main(String[] args) throws IOException{
		
		int n = 0;
        int a = 0;
        int b = 0;
        Matrix A = null;
        Matrix B = null;
		Scanner inFile = new Scanner(new File(args[0]));;
		PrintWriter outFile = null;
		// first check if args.length is within bounds
		if (args.length != 2) {
			System.err.println("spare inFile outFile");
			System.exit(1);  
		}

		// open input and output file  

		try
        {
			outFile = new PrintWriter(new FileWriter(args[1]));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		while (inFile.hasNextInt())
        {
            n = inFile.nextInt();
            a = inFile.nextInt();
            b = inFile.nextInt();      
            //create Matrices
            A = new Matrix(n);
            B = new Matrix(n);

		// adding in Matrix A's entries
		// reading out a entries
            for (int i = 1; i <= a; i++)
            {
                int Arow = inFile.nextInt();
                int Acolumn = inFile.nextInt();
                double Avalue = inFile.nextDouble();
                A.changeEntry(Arow, Acolumn, Avalue);
            }

            // loop through matrix B
            for (int j = 1; j <= b; j++)
            {
                int Brow = inFile.nextInt();
                int Bcolumn = inFile.nextInt();
                double Bvalue = inFile.nextDouble();
                B.changeEntry(Brow, Bcolumn, Bvalue);
            }
        }


		// Result in outFile
		outFile.println("A has " + a + " non-zero entries:\n" + A);
		outFile.println("B has " + b + " non-zero entries:\n" + B);
		outFile.println("(1.5)*A =\n" + A.scalarMult(1.5));
		outFile.println("A+B =\n" + A.add(B));
		outFile.println("A+A =\n" + A.add(A));
		outFile.println("B-A =\n" + B.sub(A));
		outFile.println("A-A =\n" + A.sub(A));
		outFile.println("Transpose(A) =\n" + A.transpose());
		outFile.println("A*B =\n" + A.mult(B));
		outFile.println("B*B =\n" + B.mult(B));

		inFile.close();
		outFile.close();
	}
}
