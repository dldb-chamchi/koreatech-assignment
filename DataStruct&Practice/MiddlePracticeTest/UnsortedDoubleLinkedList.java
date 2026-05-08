import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자료구조및실습
 * @version 2024년도 2학기
 * @author 임아리
 * @file UnsortedDoubleLinkedList.java
 * 이중 연결구조를 이용한 비정렬 정수 리스트, 중복 허용, head와 tail 유지
 * 
 * 2024년도 2학기 1차 실습시험
 * 학번: 2023100657 이름: 임아리 
 */
public class UnsortedDoubleLinkedList implements Iterable<Integer> {
	private static class Node {
		private int item;
		private Node prev;
		private Node next;
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
	
	public UnsortedDoubleLinkedList() {}
	public UnsortedDoubleLinkedList(int... initList) {
		if(initList.length == 0) return;
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
	
	public void pushFront(int item) {
		Node newNode = new Node(item, null, head);
		if(head != null) head.prev = newNode;
		else tail = newNode;
		head = newNode;
		++numItems;
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
	
	public void pushBack(int item) {
		Node newNode = new Node(item, tail, null);
		if(tail != null) tail.next = newNode;
		else head = newNode;
		tail = newNode;
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

	public int peekFront() {
		if(isEmpty()) throw new IllegalStateException("peekFront: empty state");
		return head.item;
	}

	public int peekBack() {
		if(isEmpty()) throw new IllegalStateException("peekBack: empty state");
		return tail.item;
	}
	
	// (6) 처음부터 검색하여 주어진 값을 찾으면 해당 값을 삭제하는 메소드
	void removeFirst(int item) {
		if(isEmpty()) return;
		Node headDummy = new Node(-1, null, head);
		Node tailDummy = new Node(-1, tail, null);
		head.prev = headDummy;
		tail.next = tailDummy;
		Node curr = headDummy;
		while(curr!=null) {
			if(curr.item == item) { //정상일떄
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
			}
			curr = curr.next;
		}
		//head 갱신
		head = headDummy.next;
		head.prev = null;
		
		//tail 갱신
		tail = tailDummy.prev;
		if(tail != null) tail.next = null;
		
		--numItems;
		
	}
	
	@Override public Iterator<Integer> iterator() {
		return new ListIterator();
	}
}
