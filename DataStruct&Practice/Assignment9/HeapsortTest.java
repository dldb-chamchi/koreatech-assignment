import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;


//힙정렬

class HeapsortTest {

	String[] generateRandomStringArray(int size) {
		String[] words = new String[size];
		for(int i = 0; i < size; ++i) {
			int strLen = ThreadLocalRandom.current().nextInt(3, 21);
			StringBuilder word = new StringBuilder(strLen);
			for(int j = 0; j < strLen; ++j)
				word.append((char)(ThreadLocalRandom.current().nextInt(26) + 'a'));
			words[i] = word.toString();
		}
		return words;
	}
	
	@Test
	void sortTest() {
		String[] list = {"banana", "melon", "mango", "strawberry", "apple", "grape"};
		String[] expected = list.clone();
		Arrays.sort(expected);
		Util.sort(list);
		assertTrue(Arrays.equals(list, expected));
		Arrays.sort(expected, (a,b)->b.compareTo(a));
		Util.sort(list, (a,b)->b.compareTo(a));
		assertTrue(Arrays.equals(list, expected));
	}
	
	@Test
	void randomTest() {
		for(int i = 0; i < 100; ++i) {
			String[] words = generateRandomStringArray(ThreadLocalRandom.current().nextInt(50, 100));
			String[] expected = words.clone();
			Arrays.sort(expected);
			Util.sort(words);
			assertTrue(Arrays.equals(words, expected));
			Arrays.sort(expected, (a,b)->b.compareTo(a));
			Util.sort(words, (a,b)->b.compareTo(a));
			assertTrue(Arrays.equals(words, expected));
		}
	}

}
