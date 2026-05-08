import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;



//테스트
class HashTableTest {

	@Test
	void addtest() {
		String[] fruits = {"peach", "grape", "orange", "kiwi", "mango",
			"tomato", "melon", "cherry", "lemon", "plum"};
		HashTable hTable = new HashTable();
		for(var fruit: fruits){
			hTable.add(fruit);
		}
		assertEquals(hTable.size(), 10);
		for(var fruit: fruits) {
			assertTrue(hTable.contains(fruit));
		}
	}

	@Test
	void removeTest() {
		String[] fruits = {"peach", "grape", "orange", "kiwi", "mango",
			"tomato", "melon", "cherry", "lemon", "plum"};
		HashTable hTable = new HashTable();
		for(var fruit: fruits){
			hTable.add(fruit);
		}
		assertEquals(hTable.size(), 10);
		List<String> tmp = Arrays.stream(fruits).collect(Collectors.toList());
		Collections.shuffle(tmp);
		for(int i = 0; i < 5; ++i) {
			hTable.remove(tmp.get(i));
			assertFalse(hTable.contains(tmp.get(i)));
		}
		assertEquals(hTable.size(), 5);
	}
	
}
