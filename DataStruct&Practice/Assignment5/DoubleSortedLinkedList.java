
import java.util.Arrays;
import java.util.Iterator;

public class DoubleSortedLinkedList implements Iterable<Integer> {
	private static class Node {
		int item;
		Node prev;
		Node next;
		public Node(int item) {
			this(item, null, null);
		}
		public Node(int item, Node prev, Node next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	private class ListIterator implements Iterator<Integer> {
		private Node curr = head;
		@Override public boolean hasNext() {
			return curr != null;
		}

		@Override public Integer next() {
			int ret = curr.item;
			curr = curr.next;
			return ret;
		}
		
	}
	private Node head = null;
	private Node tail = null;
	private int numItems = 0;
	public DoubleSortedLinkedList() {}
	public DoubleSortedLinkedList(int... initList) {
		if(initList.length == 0) return;
		Arrays.sort(initList);
		Node curr = new Node(initList[0]);
		head = curr;
		for(int i = 1; i < initList.length; ++i) {
			Node newNode = new Node(initList[i]);
			curr.next = newNode;
			newNode.prev = curr;
			curr = newNode;
		}
		tail = curr;
		numItems = initList.length;
	}
//	private Node head = null;
//	private Node tail = null;
//	private int numItems = 0;
//	public DoubleSortedLinkedList() {}
//	public DoubleSortedLinkedList(int... initList) {
//		if(initList.length == 0) return;
//		for(int i = 0; i < initList.length; ++i) {
//			add(initList[i]);
//		}
//		numItems = initList.length;
//	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public int size() {
		return numItems;
	}
	
	public void clear() {
		head = null;
		numItems = 0;
	}
	
	private void checkRange(int index) {
		if(index < 0 || index >= numItems)
			throw new IndexOutOfBoundsException(String.format("checkRange: %d index", index));
	}
	
	public Node getNode(int index) {
		Node curr = head;
		for(int i = 0; i < index; ++i) curr = curr.next;
		return curr;
	}
	
	public int get(int index) {
		checkRange(index);
		return getNode(index).item;
	}
	
	public void set(int index, int item) {
		checkRange(index);
		getNode(index).item = item;
	}
	
	public void add(int item) {
		Node curr = head;
		Node newNode = new Node(item);
		if(curr == null) {
			head = newNode;
			tail = newNode;
			++numItems;
			return;
		}
		while(curr != null && curr.item < item) {
			curr = curr.next;
		}
		if(curr == head) {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		else if(curr == null) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode; //newNode = tail 하면 예쩐 tail값 덮어씌어짐
		}
		else {
			curr.prev.next = newNode;
			newNode.next = curr;
			newNode.prev = curr.prev;
			curr.prev = newNode;
		}
		++numItems;
	}

	public int popBack() {
		if(isEmpty()) throw new IllegalStateException("popBack: empty state");
		int ret = tail.item;
		Node prev = tail.prev;
		if(prev != null) {
			tail = prev;
			tail.next = null;
		}
		else head = tail = null;
		--numItems;
		return ret;
	}

	public int popFront() {
		if(isEmpty()) throw new IllegalStateException("popFront: empty state");
		int ret = head.item;
		head = head.next;
		if(head != null) head.prev = null;
		else tail = null;
		--numItems;
		return ret;
	}

	int peekFront() {
		if(isEmpty()) throw new IllegalStateException("peekFront: empty state");
		return head.item;
	}

	int peekBack() {
		if(isEmpty()) throw new IllegalStateException("peekBack: empty state");
		return tail.item;
	}
	
	boolean find(int item) {
		Node curr = head;
		while(curr != null && curr.item < item) {
			curr = curr.next;
		}
		return curr!= null && curr.item == item;
	}
	
	
//	void removeFirst(int item) {
//		if(isEmpty()) return;
//		Node curr = head;
//		while(curr != null) {
//			if(curr.item == item) {
//				if(curr.prev != null) curr.prev.next = curr.next;
//				if(curr.next != null) curr.next.prev = curr.prev;
//				if(curr == head) head = curr.next;
//				if(curr == tail) tail = curr.prev;
//				--numItems;
//				break;
//			}
//			curr = curr.next;
//		}
//	}
	
	void removeFirst(int item) {
		if(isEmpty()) return;
		Node curr = head;
		if(head.item == item && head == tail) { //유일 노드 지우기
			head = tail = null;
			--numItems;
			return;
		}
		while(curr!=null && curr.item < item) curr = curr.next;
		if(curr == null || curr.item != item) return; //find는 boolean 반환이라 코드 중복 제거가 안됨
		if(curr == head) { //head 갱신
			head = head.next;
			head.prev = null;
		}
		else if(curr == tail) { //tail 갱신
			tail = tail.prev;
			tail.next = null;
		}
		else { //보통의 경우
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}
		--numItems;
	}
		
//	void removeAll(int item) {
//		if(isEmpty()) return;
//		Node curr = head;
//		while(curr != null) {
//			if(curr.item == item) {
//				if(curr.prev != null) curr.prev.next = curr.next;
//				if(curr.next != null) curr.next.prev = curr.prev;
//				if(curr == head) head = curr.next;
//				if(curr == tail) tail = curr.prev;
//				--numItems;
//			}
//			curr = curr.next;
//		}
//	}
	void removeAll(int item) {
	    if (isEmpty()) return;

	    Node curr = head;
	    Node first = null, last = null;

	    while (curr != null) {
	        if (curr.item == item) {
	            last = curr;
	            if (first == null) first = curr;
	            --numItems;
	        }
	        curr = curr.next;
	    }

	    if (first == null) return; // 일치하는 항목이 없으면 종료

	    if (first == head) head = last.next; //head 갱신
	    if (last == tail) tail = first.prev; //tail 갱신

	    if (first.prev != null) first.prev.next = last.next; //정상의 경우
	    if (last.next != null) last.next.prev = first.prev; //정상의 경우

	    if (head == null) tail = null; //갱신된 head가 null일 때 -> 유일노드 처리
	}

	
	
	@Override public Iterator<Integer> iterator() {
		return new ListIterator();
	}
}
