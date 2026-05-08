
import java.util.Arrays;
import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자료구조및실습
 * @version 2024년도 2학기
 * @author 임아리
 * @file SortedArrayList.java
 *  배열 기반 정렬 정수 리스트, 동적 배열, 중복 허용
 * 
 * 2024년도 2학기 1차 실습시험
 * 학번:2023100657 이름: 임아리 
 */
public class SortedArrayList implements Iterable<Integer>{
	
	private class ListIterator implements Iterator<Integer>{
		int curr = 0;
		@Override public boolean hasNext() {
			return curr < numItems;
		}

		@Override public Integer next() {
			int ret = items[curr];
			++curr;
			return ret;
		}
	}
	
	private int capacity = 5;
	private int numItems = 0;
	private int[] items = null;

	public SortedArrayList() {
		items = new int[capacity];
	}
	
	public SortedArrayList(int capacity) {
		this.capacity = capacity;
		items = new int[capacity];
	}
	
	public SortedArrayList(int... initList) {
		capacity = numItems = initList.length;
		items = initList.clone();
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public int size() {
		return numItems;
	}
	
	public void clear() {
		numItems = 0;
	}

	public void add(int item) {
		if(numItems == capacity) increaseCapacity();
		int insertLoc = (isEmpty() || items[numItems-1] <= item)? numItems:
			(items[0] >= item)? 0: search(item);
		shiftRight(insertLoc);
		items[insertLoc] = item;
		++numItems;
	}

	public int peekFront() {
		if(isEmpty()) throw new IllegalStateException("peekFront: empty state");
		return items[0];
	}

	public int peekBack() {
		if(isEmpty()) throw new IllegalStateException("peekBack: empty state");
		return items[numItems - 1];
	}
	
	
	// (1) 인자로 주어진 색인 위치에 있는 값을 제거하는 메소드 
	public void removeAt(int index) {
		if(index <0 || index >= numItems) throw new IndexOutOfBoundsException("");
		if(isEmpty()) throw new IllegalStateException("removeAt");
		for(int i = index; i<numItems-1; ++i) {
			items[i] = items[i+1]; 
		}
		--numItems;
	}

	// (2) 중복된 요소를 모두 제거하는 메소드 
	public void removeDuplicates() {
		if(isEmpty()) throw new IllegalStateException("removeDuplicates");
		int j = 0;
		for(int i = 0; i<numItems; ++i) {
			if(items[i] != items[j]) {
				++j;
				items[j] = items[i];
			}
		}
		numItems = j+1;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ListIterator();
	}
	
	private void increaseCapacity() {
		capacity *= 2;
		items = Arrays.copyOf(items, capacity);
	}
	
	private void shiftRight(int startIdx) {
		for(int i = numItems; i > startIdx; --i)
			items[i] = items[i - 1];
	}
	
	private int search(int item) {
		int lo = 0;
		int hi = numItems - 1;
		while(lo<=hi) {
			int mid = lo + (hi-lo) / 2;
			if(items[mid] == item) return mid;
			else if(items[mid] > item) hi = mid - 1;
			else lo = mid+1;
		}
		return lo;
	}
}
