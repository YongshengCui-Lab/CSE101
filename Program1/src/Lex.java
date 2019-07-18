
//-----------------------------------------------------------------------------
//  Lex.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA1
//  //insertion sort , return a output file 
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.*;

public class Lex {
	public static void main(String[] args) throws IOException {
		Scanner in = null;
		PrintWriter out = null;
		int LineNum = 0;

		if (args.length != 2) // check if args is 2
		{
			System.err.println("Usage: Lex input output");
			System.exit(1);
		}

		ArrayList<String> arraylist = new ArrayList<String>();

		// open file to read
		try {
			in = new Scanner(new File(args[0]));
			out = new PrintWriter(new FileWriter(args[1]));
			while (in.hasNextLine()) {
				arraylist.add(in.nextLine());
				LineNum++;
			}

			String[] array = arraylist.toArray(new String[arraylist.size()]);
			List list = new List();

			list.append(0); // insert the first element
			// use insertion sort to sort the List
			// note: the array is untouched
			for (int i = 1; i < array.length; i++) {
				list.moveFront();
				String element = array[i];
				int j = i;
				while (j > 0 && element.compareTo(array[list.get()]) >= 0) {
					list.moveNext();
					j--;
				}
				if (list.index() >= 0) {
					list.insertBefore(i);
				} else {
					list.append(i);
				}
			}

			// print contents to file
			list.moveFront();
			while (list.index() >= 0) {
				out.println(array[list.get()]);
				list.moveNext();
			}
			in.close();
			out.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
