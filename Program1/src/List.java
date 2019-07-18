//-----------------------------------------------------------------------------
//  List.java
//  Name: Yongsheng Cui
//  ID: 1491148
//  HW: PA1
//  This file will implement the List ADT operation
//-----------------------------------------------------------------------------
public class List {
	// private class Node
	private class Node {
		int data;
		Node prev;
		Node next;
		boolean eq = false;

		// Constructor - create data, next and prev in Node
		Node(int data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		// Returns String representation of data
		public String toString() {
			return String.valueOf(data);
		}

		// Returns true if the twoNode are equal
		public boolean equals(Object n) {
			boolean eq = false;
			if (n instanceof Node) {
				Node that = (Node) n;
				eq = (this.data == that.data);
			}
			return eq;
		}
	}

	// Private element
	private Node front;
	private Node back;
	private Node cursor;
	private int index;
	private int length;

	// Constructor
	//create a new empty list
	public List() {
		this.length = 0;
		this.index = -1;
		this.cursor = null;
		this.front = null;
		this.back = null;
	}

	// -- Access functions --------------------------------------
	// returns the number of elements in this List
	public int length() {
		return length;
	}

	// index()
	// if cursor is defined, returns the index of
	// the current element,
	// otherwise return -1
	public int index() {
		if (cursor == null) {
			return -1;
		}
		return index;
	}

	// returns front element. pre: length() > 0
	public int front() {
		if (length() > 0) {
			return front.data;
		} else
			throw new RuntimeException("Error: cannot called front() on empty list!");
	}

	// returns back element. pre: length() > 0
	public int back() {
		if (length() > 0) {
			return back.data;
		} else
			throw new RuntimeException("Error: cannot called back() on empty list!");
	}

	// returns the cursor element. pre: length() > 0 and index() > 0
	public int get() {
		if (length() <= 0) {
			throw new RuntimeException("Error: cannot called get() on empty list!");
		}
		if (cursor == null) {
			throw new RuntimeException("Error: cannot called get() if cursor is null");
		}
		return cursor.data;
	}

	// returns true if this List and L are the same integer
	// sequence. the cursor is ignored in both lists.
	public boolean equals(List L) {
		if (this.length == L.length) {
			Node FirstIterator = this.front;
			Node SecondIterator = L.front;
			while (FirstIterator != null && SecondIterator != null) {
				if (FirstIterator.data == SecondIterator.data) {
					FirstIterator = FirstIterator.next;
					SecondIterator = SecondIterator.next;
				} else return false;
			} return true;
		} else {
			return false;
		}
			
	}

	// -- Manipulation procedure --------------------------

	// rests this List to it original empty state
	public void clear() {

		this.length = 0;
		this.index = -1;
		this.cursor = null;
		this.front = null;
		this.back = null;

	}

	// if List is non-empty, places the cursor under
	// the front element, otherwise does nothing
	public void moveFront() {
		if (length() > 0) {
			cursor = front;
			index = 0;
		}
	}

	// if list is non-empty, places the cursor under
	// the back element, otherwise does nothing
	public void moveBack() {
		if (length() > 0) {
			cursor = back;
			index = length - 1;
		}
	}
	
	// if cursor is defined and not at front, moves cursor
	// one step toward front of this List, cursor
	// becomes undefined, if cursor is undefined does nothing
	public void movePrev() {
		if (cursor != front && index != -1) {
			cursor = cursor.prev;
			index--;
		} else if (cursor == front && index != -1) {
			cursor = null;
			index = -1;
		}
	}

	// if cursor is defined and not at front, move cursor
	// one step toward back of this List, if cursor is defined,
	// and at front, cursor becomes undefined, if
	// cursor is undefined, does nothing
	public void moveNext() {
		if (cursor != null && index == length - 1) {
			cursor = null;
			index = -1;
		} else if (cursor != null && index != length - 1) {
			cursor = cursor.next;
			index++;
		}
	}

	// insert new element into this List. if List is non-empty,
	// insertion takes place before front element
	public void prepend(int data) {
		Node newNode = new Node(data);
		if (front == null) {
			front = newNode;
			back = newNode;
			cursor = front;
		} else {
			front.prev = newNode;
			newNode.next = front;
			newNode.prev = null;
			front = newNode;
			index++;
		}
		length++;
	}

	// insert new element into this List. if this lis is non-empty
	// insertion takes place after back element
	public void append(int data) {
		Node newNode = new Node(data);
		if (back == null) {
			front = newNode;
			back = newNode;
			cursor = back;
		} else {
			back.next = newNode;
			newNode.prev = back;
			back = newNode;
			newNode.next = null;
		}
		length++;
	}

	// insert new element before cursor
	// pre: length() > 0, index() > 0
	public void insertBefore(int data) {
		Node newNode = new Node(data);

		if (length == 0) {
			throw new RuntimeException("Error: cannot called nsertBefore() on empty list!");

		}

		else {

			newNode.next = cursor;

			if (cursor.prev != null) {

				newNode.prev = cursor.prev;

				cursor.prev.next = newNode;

			}

			cursor.prev = newNode;

			if (newNode.prev == null)

				front = newNode;

		}

		index++;

		length++;

	}

	// insert new element after cursor
	// pre: length() > 0, index() > 0
	public void insertAfter(int data) {
		if (length() < 0 && index < 0)

			throw new RuntimeException("List error: insertAfter() called on empty List");

		if (cursor == back) {

			append(data);

		}

		else {

			Node temp = new Node(data);

			cursor.next.prev = temp;

			temp.next = cursor.next;

			temp.prev = cursor;

			cursor.next = temp;

			length++;

		}
	}

	// deletes front element. pre: length() > 0
	public void deleteFront() {
		if (length() < 1) {
			throw new RuntimeException("Error: cannot deleteFront() empty list.");
		} else {
			if (front == cursor) {
				cursor = null;
				index = -1;
			}
			if (length > 1) {
				front = front.next;
				front.prev = null;
				if (cursor != null) {
					index--;
				}
			}
			length--;
		}
	}

	// deletes back element. pre: length() > 0
	public void deleteBack() {
		if (length < 1) {
			throw new RuntimeException("Error :cannot deleteBack() on an empty list");
		}
		if (back == cursor) {
			cursor = null;
			index = -1;
		}
		back.next = null;
		back = back.prev;

		length--;
	}

	// deletes cursor element, making cursor undefined
	// pre: length() > 0, index() > 0
	public void delete() {
		if(length < 1)
		{
			throw new RuntimeException("List Class Exception: cannot delete() on empty list");
		}
		if(index() < 0)
		{
			throw new RuntimeException("List Class Exception: cannot delete() with undefined cursor");
		}
		if(cursor == front)
		{
			deleteFront();
		}
		else if(cursor == back)
		{
			deleteBack();
		}
		else
		{
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			cursor = null;			
			length--;
		}
		index = -1;
	}

	// -- Other methods --------------------------------------

	 // Overrides Object's toString method. Returns a String
	 // representation of this List consisting of a space
	// separated sequence of integers, with front on left.
	@Override
	public String toString() {
		Node N = front;
		String output = new String();
		while(N != null)
		{
			output = output + String.valueOf(N.data) + " ";
			N = N.next;
		}
		return output;
	}

	// returns a new List representing the same integer
	// sequence as this List. the cursor in the new list
	// is undefined, regardless of the state of the cursor
	// in this list. the List is unchanged
	public List copy() {
		List copyList = new List();
			Node N = front;
			while (N != null) {
				copyList.append(N.data);
				N = N.next;
			}
			copyList.cursor = null;
			copyList.index = -1;
		return copyList;
	}
}