import java.util.Comparator;


//힙정렬

public class Util {
	@SuppressWarnings("unused")
	private static <T extends Object & Comparable<? super T>> void reheapDown(T[] items, int size, int index, Comparator<T> comparator) {
		int largest = index;
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		if(left < size && comparator.compare(items[left], items[largest]) > 0) largest = left;
		if(right < size && comparator.compare(items[right], items[largest]) > 0) largest = right;

		if (largest != index) {
			swap(items, index, largest);
			reheapDown(items, size, largest, comparator);
		}
	}
	// 주어진 배열을 이진힙으로 바꾸기 O(n)
	private static <T extends Object & Comparable<? super T>> void heapify(T[] items, Comparator<T> comparator) {
		for(int i = items.length / 2 - 1; i >= 0; --i)
			reheapDown(items, items.length, i, comparator);
	}
	@SuppressWarnings("unused")
	private static <T extends Object & Comparable<? super T>> void swap(T[] items, int a, int b) {
		T tmp = items[a];
		items[a] = items[b];
		items[b] = tmp;
 	}
	// 이진 힙을 정렬된 상태로 바꾸기 
	private static <T extends Object & Comparable<? super T>> void reorder(T[] items, Comparator<T> comparator) {
		for(int i = items.length - 1; i > 0; --i){
			swap(items, 0, i);
			reheapDown(items, i, 0, comparator);
		}
	}

	public static <T extends Object & Comparable<? super T>> void sort(T[] items) {
		sort(items, (a, b)->a.compareTo(b));
	}
	
	// 단계 1. 기존 배열을 이진힙 특성을 갖도록 재배치: 비용 O(n)
	// 단계 2. 가장 큰 값을 맨 뒤로 이동한 후 이 값을 제외하고 이진힙 다시 구성 O(n log n)
	public static <T extends Object & Comparable<? super T>> void sort(T[] items, Comparator<T> comparator) {
		heapify(items, comparator);
		reorder(items, comparator);
	}
}
