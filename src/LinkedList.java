
public class LinkedList { //circular, doubly-linked
	int element;
	LinkedList next;
	LinkedList prev;
	
	public LinkedList(int element, LinkedList prev, LinkedList next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}
	
	public LinkedList(int element) {
		this.element = element;
		this.prev = this;
		this.next = this;
	}
	
	public static LinkedList newList() {
		return new LinkedList(-1); //sentry
	}
	
	public static void delete(LinkedList link) {
		link.prev.next = link.next;
		link.next.prev = link.prev;
	}
	
	// lastItem <-> this => lastItem <-> new <-> this
	public void addBefore(int element) {
		LinkedList lastItem = this.prev;
		lastItem.next = new LinkedList(element, lastItem, this);
		this.prev = lastItem.next;
	}
	
	// this <-> nextItem => this <-> new <-> nextItem
	public void addAfter(int element) {
		LinkedList nextItem = this.next;
		nextItem.prev = new LinkedList(element, this, nextItem);
		this.next = nextItem.prev;
	}
	
	public void reverse() { // prev <-> next for all links
		LinkedList current = this;
		do {
			LinkedList doNext = current.next;
			current.next = current.prev;
			current.prev = doNext;
			current = doNext;
		} while(current != this);
	}
	
	public static LinkedList merge(LinkedList l1, LinkedList l2) {
		LinkedList l1End = l1.prev;
		LinkedList l2End = l2.prev;
		l1End.next = l2;
		l2End.next = l1;
		l2.prev = l1End;
		l1.prev = l2End;
		
		return l1;
	}
	
	public static LinkedList[] split(LinkedList list, LinkedList splitStart) {
		LinkedList l1End = splitStart.prev;
		LinkedList l2End = list.prev;
		list.prev = l1End;
		splitStart.prev = l2End;
		l1End.next = list;
		l2End.next = splitStart;
		return new LinkedList[] { list, splitStart };
	}
}
