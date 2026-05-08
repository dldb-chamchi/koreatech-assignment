import java.util.Arrays;
import java.util.Iterator;

/*
 * @copyright 한국기술교육대학교 컴퓨터공학부 자료구조및실습
 * @version 2024년도 2학기
 * @author 임아리
 * @file SortedSingleLinkedList.java
 * 단일 연결구조 기반 정렬 정수 리스트, 중복 허용, head와 tail 유지, 
 *
 * 2024년도 1차 실습시험
 * 학번: 2023100657 이름: 임아리 
 */
public class SortedSingleLinkedList implements Iterable<Integer> {
	
	private static class Node{
		int item;
		Node next;
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
			return curr!=null;
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
	
	public SortedSingleLinkedList() {}
	public SortedSingleLinkedList(int... initList) {
		Arrays.sort(initList);
		Node curr = null;
		for(var n: initList) {
			Node newNode = new Node(n);
			if(head==null) head = curr = newNode;
			else {
				curr.next = newNode;
				curr = newNode;
			}
		}
		tail = curr;
		numItems = initList.length;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return numItems;
	}
	
	public void clear() {
		head = tail = null;
		numItems = 0;
	}
	
	public void add(int item) {
		Node newNode = new Node(item);
		if(isEmpty()) head = tail = newNode;
		else if(head.item >= item) {
			newNode.next = head;
			head = newNode;
		}
		else if(tail.item <= item) {
			tail.next = newNode;
			tail = newNode;
		}
		else {
			Node prev = head;
			while(prev.next != null && prev.next.item < item)
				prev = prev.next;
			newNode.next = prev.next;
			prev.next = newNode;
		}
		++numItems;
	}

	int peekFront(){
		if(isEmpty()) throw new IllegalStateException("peekFront: empty state");
		return head.item;
	}

	int peekBack(){
		if(isEmpty()) throw new IllegalStateException("peekBack: empty state");
		return tail.item;
	}

	// (3) 인자로 주어진 색인 위치에 있는 값을 제거하는 메소드
	void removeAt(int index) {
		if(isEmpty()) throw new IllegalStateException("removeAt");
		if(index < 0 || index >= numItems) throw new IndexOutOfBoundsException("");
		Node dummy = new Node(-1, head);
		Node prev = dummy;
		Node curr = head;
		
		for(int i = 0; i<index; ++i) {
			curr = curr.next;
			prev = prev.next;
		}
		if(curr == tail) {
			prev.next = null;
			tail = prev;
		}
		prev.next = curr.next;
		head = dummy.next;
		--numItems;
		
	}
	
	// (4) 중복된 요소를 모두 제거하는 메소드
	void removeDuplicates() {
		Node prev = head;
		Node curr = head.next;
		int num = 0;
		while(curr!=null) {
			if(curr.item == prev.item) {
				prev.next = curr.next;
				++num;
			}
			else {
				prev = prev.next;
			}
			curr = curr.next;
		}
		numItems -= num;
	}
	
	Node search(int item) {
		Node curr = head;
		while(curr!=null) {
			if(curr.item == item) return curr;
		}
		return null;
	}
	
	// (5) 인자로 주어진 리스트가 리스트의 부분 집합인지 검사하는 메소드
	//     개수가 중요. [3, 3, 4]는 [3, 4]의 부분 집합이 아님
	public boolean isSubset(SortedSingleLinkedList other) {
		if(isEmpty() || other.numItems > numItems) throw new IllegalStateException("removeAt");
		Node otherCurr = other.head;
		Node nodeCurr = head;
		while(nodeCurr != null) {
			if(search(otherCurr.item) != null) {
				Node idx = search(otherCurr.item);
				
			}
		}
		return false;
	}
	
	@Override public Iterator<Integer> iterator() {
		return new ListIterator();
	}
}
