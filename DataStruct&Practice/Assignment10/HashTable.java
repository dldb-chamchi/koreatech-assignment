import java.util.ArrayList;
import java.util.List;



//Cuckoo 해싱
public class HashTable {
	private int capacity1 = 7;
	private int capacity2 = 5;
	private String[] table1 = new String[capacity1];
	private String[] table2 = new String[capacity2];
	private int numItems = 0;
	
	public HashTable() {}
	
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	public int size() {
		return numItems;
	}
	
	public void add(String key) {
		if(contains(key)) return;
		addKey(key);
	}

	private void check() {
		double loadFactor = (double) numItems / (capacity1 + capacity2);
		if(loadFactor >= 0.7) increaseCapacity();
	}

	private void addKey(String key) {
		for(int i=0; i<2; ++i){ //최대 4번

			//테이블 1 시도
			int idx1 = hashString1(key);
			if(table1[idx1] == null){
				table1[idx1] = key;
				++numItems;
				check(); //적재율 검사
				return;
			}

			//테이블 2로 보내기
			String failKey = table1[idx1];
			table1[idx1] = key;
			key = failKey;

			//테이블 2 시도
			int idx2 = hashString2(key);
			if(table2[idx2] == null){
				table2[idx2] = key;
				++numItems;
				check(); //적재율 검사
				return;
			}

			//다시 테이블1 보내기
			failKey = table2[idx2];
			table2[idx2] = key;
			key = failKey;
		}

		//4번 시도 -> 실패 -> 용량 증가
		increaseCapacity();
		addKey(key);
	}

	public boolean contains(String key) {
		if(isEmpty()) return false;
		int idx = hashString1(key);
		if(table1[idx] != null && table1[idx].equals(key))
			return true;
		idx = hashString2(key);
		return table2[idx] != null && table2[idx].equals(key);
	}

	public void remove(String key) {
		if(isEmpty()) return;

		//테이블 1에서 제거
		int index1 = hashString1(key);
		if(table1[index1] != null && table1[index1].equals(key)){
			table1[index1] = null;
			--numItems;
			return;
		}

		//테이블 2에서 제거
		int index2 = hashString2(key);
		if(table2[index2] != null && table2[index2].equals(key)){
			table2[index2] = null;
			--numItems;
		}
	}

	@SuppressWarnings("unused")
	private void increaseCapacity() {
		List<String> keys = getKeyList();
		capacity1 = capacity1 * 2 + 1;
		capacity2 = capacity2 * 2 + 1;
		table1 = new String[capacity1];
		table2 = new String[capacity2];
		numItems = 0;
		for(var key: keys) addKey(key);
	}
	
	private List<String> getKeyList() {
		List<String> keys = new ArrayList<>();
		for(var key: table1) if(key != null) keys.add(key);
		for(var key: table2) if(key != null) keys.add(key);
		return keys;
	}
	
	void debugPrint() {
		System.out.print("table1: ");
		for(var key: table1)
			System.out.printf("%s, ", (key != null)? key: "empty");
		System.out.print("\ntable2: ");
		for(var key: table1)
			System.out.printf("%s, ", (key != null)? key: "empty");
		System.out.println();
	}
	
	private int hashString1(String key) {
		long value = 0;
		for(var c: key.toCharArray())
			value = (value*31 + c);
		return (int)((29*value + 41) % capacity1);
	}
	
	private int hashString2(String key){
		long value = 0;
		for(var c: key.toCharArray())
			value = (value*31 + c);
		return (int)((29*value + 41) % capacity2);
	}
}
