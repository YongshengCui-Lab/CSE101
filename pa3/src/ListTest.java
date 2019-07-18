//-----------------------------------------------------------------------------
//  ListTest.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA3
//-----------------------------------------------------------------------------
public class ListTest
{
    public static void main(String[] args)
    {
        List A = new List();
        List B = new List();

        for(int i = 1; i <= 20; i++)
        {
            A.append(i);
            B.prepend(i);
        }
        System.out.println(A);
        System.out.println(B);

        for(A.moveFront(); A.index() >= 0; A.moveNext())
        {
            System.out.print(A.get()+ " ");
        }
        System.out.println();
        
        for(B.moveBack(); B.index() >= 0; B.movePrev())
        {
            System.out.print(B.get() + " ");
        }
        System.out.println();

        System.out.println(A.equals(B));
        System.out.println(B.equals(A));        

        A.moveFront();
        for(int i = 0; i < 5; i++)
        { 
            A.moveNext(); // at index 5
        }

        A.insertBefore(-1); // at index 6
        for(int i = 0; i < 9; i++)
        { 
            A.moveNext(); // at index 15
        }

        A.insertAfter(-2);
        for(int i=0; i<5; i++)
        {
            A.movePrev(); // at index 10
        }

        A.deleteFront();
        System.out.println(A);
        System.out.println(A.length());        

        A.deleteBack();
        System.out.println(A);
        System.out.println(A.length());

        A.delete();
        System.out.println(A);
        System.out.println(A.length());
        A.clear();
        System.out.println(A.length());
    }
}
