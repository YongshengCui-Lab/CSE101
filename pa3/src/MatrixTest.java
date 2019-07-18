//-----------------------------------------------------------------------------
//  MatrixTest.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA3
//-----------------------------------------------------------------------------
public class MatrixTest
{
    public static void main(String[] args)
    {
        int i, j, n = 100000;
        Matrix A = new Matrix(n);
        Matrix B = new Matrix(n);

        A.changeEntry(1,1,1); B.changeEntry(1,1,1);
        A.changeEntry(1,2,2); B.changeEntry(1,2,0);
        A.changeEntry(1,3,3); B.changeEntry(1,3,1);
        A.changeEntry(2,1,4); B.changeEntry(2,1,0);
        A.changeEntry(2,2,5); B.changeEntry(2,2,1);
        A.changeEntry(2,3,6); B.changeEntry(2,3,0);
        A.changeEntry(3,1,7); B.changeEntry(3,1,1);
        A.changeEntry(3,2,8); B.changeEntry(3,2,1);
        A.changeEntry(3,3,9); B.changeEntry(3,3,1);

        System.out.println(A.getNNZ());
        System.out.println(A);

        System.out.println(B.getNNZ());
        System.out.println(B);
        
        System.out.println("ScalarMult");
        Matrix C = A.scalarMult(1.5);
        System.out.println(C.getNNZ());
        System.out.println(C);
        
        System.out.println("ADD");
        Matrix D = A.add(A);
        System.out.println(D.getNNZ());
        System.out.println(D);
        
        System.out.println("SUB");
        Matrix E = A.sub(A);
        System.out.println(E.getNNZ());
        System.out.println(E);
        
        System.out.println("TRANSPOSE");
        Matrix F = B.transpose();
        System.out.println(F.getNNZ());
        System.out.println(F);
        
        System.out.println("MULT");
        Matrix G = B.mult(B);
        System.out.println(G.getNNZ());
        System.out.println(G);
        
        System.out.println("Matrix A add Matrix B");
        Matrix H = A.add(B);
        System.out.println(H.getNNZ());
        System.out.println(H);
        
        System.out.println("Matrix B add Matrix A");
        Matrix I = B.add(A);
        System.out.println(I.getNNZ());
        System.out.println(I);
        
        System.out.println("Matrix A sub Matrix B");
        Matrix J = A.sub(B);
        System.out.println(J.getNNZ());
        System.out.println(J);
        
        System.out.println("Matrix B sub Matrix A");
        Matrix K = B.sub(A);
        System.out.println(K.getNNZ());
        System.out.println(K);
        
        System.out.println("COPY");
        Matrix L = A.copy();
        System.out.println(L.getNNZ());
        System.out.println(L);
        
        System.out.println(A.equals(L));
        System.out.println(A.equals(B));
        System.out.println(A.equals(A));        
        System.out.println("Make Zero");
        A.makeZero();
        System.out.println(A.getNNZ());
        System.out.println(A);
    }
}
