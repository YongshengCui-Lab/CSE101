//-----------------------------------------------------------------------------
//  Matrix.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA3
//-----------------------------------------------------------------------------
public class Matrix
{
    // private class Entry
    private class Entry 
    {
        int column;
        double value;

        // constructor
        public Entry(int column, double value) 
        {
            this.column = column;
            this.value = value;
        }

        // toString()
        // overrides Objet's toString()
        public String toString() 
        {
            return "(" + column + ", " + value + ")";
        }

        // equals()
        // overrides Object equals()
        public boolean equals(Object x) 
        {
            boolean eq = false;
            Entry entry;
            if (x instanceof Entry) 
            {
                entry = (Entry) x;
                eq = (this.value == entry.value);
                eq = (this.column == entry.column);
            }
            return eq;
        }
    }

    // fields
    List[] row;
    int matrixSize = 0;
    int NNZ = 0; // non-empty entries


    // constructor
    // makes a n * n zero matrix
    // pre: n > 1
    public Matrix(int n)
    {
        if (n < 1)
        {
            throw new RuntimeException("cannot make matrix if n is less than 1.");
        }else{
            matrixSize = n; // initialize matrixSize to n
            row = new List[n + 1]; // initialize array to start at 1
            // start entries counting by 1
            for (int i = 1; i <= matrixSize; i++) 
            {
                row[i] = new List(); // store a list
            }
        }
    }

    // Access functions    

    // getSize() 
    // returns n, the number of rows and columns
    // of this matrix
    public int getSize()
    {
        return matrixSize; 
    }

    // getNNZ()
    // returns the number of non-zero entries
    // in this matrix
    public int getNNZ()
    {
        return NNZ;
    }

    // equals()
    // overrides Object's equals() method
    public boolean equals(Object x)
    {
        boolean equal = true;
        Matrix matrix = (Matrix) x;
        equal = (matrixSize == matrix.matrixSize);
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length()!= 0 && matrix.row[i].length() != 0)
            {
                if (!(row[i].equals(matrix.row[i]) || this.getNNZ() == matrix.getNNZ()))
                { 
                    return false;
                }
            }
        }
        return equal;
    }

    // Manipulation procedures
    
    // makeZero()
    // sets this matrix to zero state
    public void makeZero()
    {
        row = new List[matrixSize + 1];
        NNZ = 0;
        for(int i = 1; i <= getSize(); i++) 
        {
            row[i] = new List();
        }
    }

    // copy()
    // returns a new matrix having the same entries
    // as this matrix
    public Matrix copy()
    {
        Matrix matrix = new Matrix(matrixSize);
        for (int i = 1; i <= getSize(); i++)
        {
                row[i].moveFront();
                while (row[i].index() >= 0)
                {
                    Entry entry = (Entry) row[i].get();
                    matrix.changeEntry(i, entry.column, entry.value);
                    row[i].moveNext();
            }
        }
        return matrix;
    }

    // changeEntry()
    // changes ith row, jth column of this matrix to x
    // pre: 1<=i<=getSize(), 1<=j<=getSize()
    public void changeEntry(int i, int j, double x)
    {
        if((i <= 1 && i >= getSize()) || (j <= 1 && j >= getSize()))
        {
            throw new RuntimeException("Matrix called on wrong position");
        }        
        if (row[i].length() == 0) 
        {
            if (x != 0)
            {
                Entry entry1 = new Entry(j, x);
                row[i].append(entry1);
                NNZ++;
            }
        } else {
            row[i].moveFront();
            for (int rows = 1; rows <= j; rows++) 
            {
                Entry entry = (Entry) row[i].get();
                if (entry.column == j) 
                {
                    if (x == 0) 
                    {
                        row[i].delete();
                        NNZ--;
                        break;
                    } else {
                        entry.value = x;
                        break;
                    }
                }else if (entry.column > j) {
                    if (x == 0) 
                    {
                        break;
                    }else{
                        row[i].insertBefore(new Entry(j, x));
                        NNZ++;
                        break;
                    }
                }else{
                    row[i].moveNext();
                    if (row[i].index() == -1) 
                    {
                        if (x == 0) 
                        {
                            break;
                        }else{
                            row[i].append(new Entry(j, x));
                            NNZ++;
                            break;
                        }
                    }
                }
            }
        }
    }

    // scalarMult()
    // returns a new matrix that is the scalar
    // product of this matrix with x
    public Matrix scalarMult(double x)
    {
        Matrix matrix = new Matrix(matrixSize);
        for (int i = 1; i <= getSize(); i++) 
        {
            if (row[i].length() != 0) 
            {
                row[i].moveFront();
                while (row[i].index() != -1) 
                {
                    Entry entry = (Entry) row[i].get();
                    double scalarMult = x * entry.value;
                    matrix.changeEntry(i, entry.column, scalarMult);
                    row[i].moveNext();
                }
            }
        }
        return matrix;
    }

    // transpose()
    // returns a new matrix that is the
    // the transpose of this matrix
    public Matrix transpose()
    {
        Matrix matrix = copy();
        for (int i = 1; i <= getSize(); i++) 
        {
        	row[i].moveFront();
            if (row[i].length() != 0) 
            {                
                for (int j = 1; j <= row[i].length(); j++) 
                {
                    if (row[i].index() != -1) 
                    {
                        Entry entry = (Entry) row[i].get();
                        matrix.changeEntry(entry.column, i, entry.value);
                        row[i].moveNext();
                    }
                }
            }
        }
        return matrix;
    }
    

    // dot()
    // helper function for mult
    // returns the dot product of a matrix
    private static double dot(List P, List Q)
    {
        P.moveFront();
        Q.moveFront();
        double sum = 0;
        while (P.index() != - 1 && Q.index() != -1)
        {
            Entry entry1 = (Entry) P.get();
            Entry entry2 = (Entry) Q.get();
            if (entry1.column < entry2.column)
            {
                P.moveNext();
            }
            else if (entry1.column > entry2.column)
            {
                Q.moveNext();
            }else{
                sum += (entry1.value * entry2.value);
                P.moveNext();
                Q.moveNext();
            }
        }
        return sum;
    }

    // add()
    // returns a new matrix that is the sum
    // of this matrix with M
    public Matrix add(Matrix M)
    {
        if (getSize() != M.getSize())
        {
            throw new RuntimeException("called add() on Matrix");
        }
      
        if (M == this)
        {
            return this.scalarMult(2);
        }
        Matrix N = new Matrix(getSize());
        for (int i = 1; i <= getSize(); i++)
        {
            List l1 = row[i];
            List l2 = M.row[i];
            l1.moveFront();
            l2.moveFront();
         
            while (l1.index() >= 0 && l2.index() >= 0)
            {
                Entry entry1 = (Entry) (l1.get());
                Entry entry2 = (Entry) (l2.get());
                if (entry1.column == entry2.column)
                {
                    if (entry1.value + entry2.value != 0)
                    {
                        N.changeEntry(i, entry1.column, (entry1.value + entry2.value));
                        l1.moveNext();
                        l2.moveNext();
                    }else{
                        l1.moveNext();
                        l2.moveNext();
                    }
                }
                else if (entry1.column < entry2.column)
                {
                    N.changeEntry(i, entry1.column, entry1.value);
                    l1.moveNext();
                }else{
                    N.changeEntry(i, entry2.column, entry2.value);
                    l2.moveNext();
                }
            }
            if (l1.index() == l2.index())
            {
                continue;
            }
            List temp;
            if (l1.index() > -1)
            {
                temp = l1;
            }else{
                temp = l2;
            }
            while (temp.index() > -1)
            {
                Entry entry3 = (Entry) temp.get();
                N.changeEntry(i, entry3.column, entry3.value);
                temp.moveNext();
            }
        }
        return N;
    }

    // sub()
    // returns a new matrix that is the subtraction
    // of this matrix with M
    // pre: getSize() == M.getSize()
    public Matrix sub(Matrix M)
    {
        if (getSize() != M.getSize())
        {
            throw new RuntimeException("called sub() on Matrix");
        }
      
        Matrix N = new Matrix(getSize());
        if (M == this)
        {
            return N;
        }
        for (int i = 1; i <= getSize(); i++)
        {
            List l1 = row[i];
            List l2 = M.row[i];
            l1.moveFront();
            l2.moveFront();
            while (l1.index() > -1 && l2.index() > -1)
            {
                Entry entry1 = (Entry) (l1.get());
                Entry entry2 = (Entry) (l2.get());
                if (entry1.column == entry2.column)
                {
                    if ((entry1.value - entry2.value) != 0)
                    {
                        N.changeEntry(i, entry1.column, (entry1.value - entry2.value));
                        l1.moveNext();
                        l2.moveNext();
                    }else{
                        l1.moveNext();
                        l2.moveNext();
                    }
                }
                else if (entry1.column < entry2.column)
                {
                    N.changeEntry(i, entry1.column, entry1.value);
                    l1.moveNext();
                }else{
                    N.changeEntry(i, entry2.column, entry2.value);
                    l2.moveNext();
                }
            }
            if (l1.index() == l2.index())
            {
                continue;
            }
            List temp;
            if (l1.index() > -1)
            {
                temp = l1;
            }else{
                temp = l2;
            }
            int sign;
            if (l1.index() > -1)
            {
                sign = 1;
            }else{
                sign = -1;
            }
            while (temp.index() >= 0)
            {
                Entry entry3 = (Entry) (temp.get());
                N.changeEntry(i, entry3.column, sign * entry3.value);
                temp.moveNext();
            }
        }
        return N;
    }

    // mult()
    // returns a new matrix that is the product
    // of this matrix with M
    // pre: getSize() == M.getSize()
    public Matrix mult(Matrix M)
    {   
        if (getSize() != M.getSize())
        {
            throw new RuntimeException("called mult() on Matrix");
        }

        Matrix temp = M.transpose();
        Matrix N = new Matrix(getSize());
        
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length() == 0)
            {
                continue;  
            }
            for (int j = 1; j <= temp.getSize(); j++)
            {
                if (temp.row[j].length() == 0)
                {
                    continue;
                }else{
                    double dotProd = dot(row[i], temp.row[j]);
                    if (dotProd != 0.0)
                    {
                        N.changeEntry(i, j, dotProd);
                    }
                }
            }
        }        
        return N;
    }

    // Other functions

    // toString()
    // overrides Object's toString() method
    public String toString() 
    {        
        String ret = "";
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length() > 0)
            {
                ret+= (i + ": " + row[i] + "\n");
            }
        }
        return ret;
    }
}
