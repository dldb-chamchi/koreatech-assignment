//비정렬 연결 리스트 구현

import java.util.Iterator;

public class UnsortedLinkedList implements Iterable<Integer> {
	public static class Node {
		public static int count = 0;
		{
			++count;
		}
		private int item;
		private Node next;
		public Node(int item) {
			this(item, null);
		}
		public Node(int item, Node next) {
			this.item = item;
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
	private int numItems = 0;
	public UnsortedLinkedList() {}
	public UnsortedLinkedList(int... initList) {
		if(initList.length == 0) return;
		Node curr = new Node(initList[0]);
		head = curr;
		for(int i = 1; i < initList.length; ++i) {
			Node newNode = new Node(initList[i]);
			curr.next = newNode;
			curr = newNode;
		}
		numItems = initList.length;
	}
	
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
	
	private Node getNode(int index) {
		Node curr = head;
		for(int i = 0; i < index; ++i) curr = curr.next;
		return curr;
	}
	
	private Node getTail() {
		return getNode(numItems - 1);
	}
	
	public int get(int index) {
		checkRange(index);
		return getNode(index).item;
	}
	
	public void set(int index, int item) {
		checkRange(index);
		getNode(index).item = item;
	}
	
	public void pushBack(int item) {
		Node newNode = new Node(item);
		if(isEmpty()) head = newNode;
		else getTail().next = newNode;
		++numItems;
	}
	
	/*
	public int popBack() {
		if(isEmpty()) throw new IllegalStateException("popBack: empty state");
		Node dummy = new Node(-1, head);
		Node prev = dummy;
		Node curr = head;
		while(curr.next != null){
			prev = curr;
			curr = curr.next;
		}
		int ret = curr.item;
		prev.next = null;
		head = dummy.next;
		--numItems;
		return ret;
	}
	*/
	
	public int popBack() { //수정본, 예외 -> head가 head를 지울 때 curr.next == null
		if(isEmpty()) throw new IllegalStateException("popBack: empty state");
		Node curr = head;
		if (curr.next == null) { //head가 head를 지울때
	        int ret = curr.item;
	        head = null;  
	        --numItems;
	        return ret;
	    }
		while(curr.next.next != null){ //node가 2개 이상
			curr = curr.next;
		}
		int ret = curr.next.item;
		curr.next = null;
		--numItems;
		return ret;
	}

	public void pushFront(int item) {
		Node newNode = new Node(item, head);
		head = newNode;
		++numItems;
	}

	public int popFront() {
		if(isEmpty()) throw new IllegalStateException("popFront: empty state");
		int ret = head.item;
		head = head.next;
		--numItems;
		return ret;
	}

	int peekFront() {
		if(isEmpty()) throw new IllegalStateException("peekFront: empty state");
		return head.item;
	}

	int peekBack() {
		if(isEmpty()) throw new IllegalStateException("peekBack: empty state");
		return getTail().item;
	}
	
	boolean find(int item) {
		Node curr = head;
		while(curr != null){
			if(curr.item == item) return true;
			curr = curr.next;
		}
		return false;
	}
	
	private void removeNode(Node curr) {
	    if (curr.next != null) { //마지막노드가 아닐때
	        curr.next = curr.next.next;
	        --numItems;
	    }
	}
	/*
	public void removeFirst(int item) {
		Node dummy = new Node(-1, head);
		Node prev = dummy;
		Node curr = head;
		while(curr != null){
			if(curr.item == item) {
				removeNode(prev, curr);
				break;
			}
			prev = curr;
			curr = curr.next;
		}
		head = dummy.next;
	}
	*/
	public void removeFirst(int item) {
	    if(isEmpty())
	    	throw new IllegalStateException("removeFirst: empty state");
	    

	    // 첫 번째 노드가 item인 경우
	    if(head.item == item) {
	    	head = head.next;  // 첫 번째 노드를 삭제 (노드가 하나라도 동일 처리 -> head.next = null)
	    	--numItems;
	    	return;
	    }

	    // 두 번째 노드 이후 탐색
	    Node curr = head;
	    while (curr.next != null) {
	        if (curr.next.item == item) {
	            removeNode(curr);  //노드 삭제
	            return;  // 처음만 삭제하고 종료
	        }
	        curr = curr.next;
	    }
	}

	/*
	void removeAll(int item) {
		Node dummy = new Node(-1, head);
		Node prev = dummy;
		Node curr = head;
		while(curr != null) {
			if(curr.item == item) removeNode(curr);
			else prev = curr;
			curr = curr.next;
		}
		head = dummy.next;
	}
	*/
	
	void removeAll(int item) { //수정본
	    if (isEmpty()) throw new IllegalStateException("removeAll: empty state");

	    // 첫 번째 노드부터 item을 가지고 있을 경우, 계속 삭제 (head!= null, 노드가 하나만있을때 head를 지우면 head가 null을 가리키기때문에 while을 끝내야함)
	    while (head!=null && head.item == item) {
	        head = head.next;
	        --numItems;
	    }

	    // 그 다음부터는 curr을 통해 나머지 노드를 탐색하며 삭제
	    Node curr = head;
	    while (curr != null && curr.next != null) { //현재가 null(본인이 마지막), 다음이 null(다음이 마지막)
	        if (curr.next.item == item)
	        	removeNode(curr); //다음 노드를 삭제
	        else
	            curr = curr.next; //다음 노드로 이동
	        
	    }
	}

	
	@Override public Iterator<Integer> iterator() {
		return new ListIterator();
	}
}
