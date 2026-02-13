
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoubleSortedLinkedListTest {

	@Test
	void listEmptyInitializationTest()
	{
		DoubleSortedLinkedList list = new DoubleSortedLinkedList();
		assertEquals(list.size(), 0);
		assertTrue(list.isEmpty());
		assertFalse(list.isFull());
	}

	@Test
	void addFindTest()
	{
		DoubleSortedLinkedList list = new DoubleSortedLinkedList();
		list.add(3);
		list.add(5);
		list.add(9);
		list.add(7);
		list.add(9);
		list.add(3); // 3, 3, 5, 7, 9, 9
		assertEquals(list.size(),6);
		StringBuilder output = new StringBuilder();
		for(var n: list)
			output.append(String.format("%d,",n));
		assertEquals(output.toString(),"3,3,5,7,9,9,");
		
		assertTrue(list.find(3));
		assertTrue(list.find(5));
		assertTrue(list.find(7));
		assertTrue(list.find(9));
		assertFalse(list.find(2));
		assertFalse(list.find(4));
		assertFalse(list.find(11));
	}

	@Test
	void popTest()
	{
		DoubleSortedLinkedList list = new DoubleSortedLinkedList();
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(3);
		assertEquals(list.size(),4);
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty())
			output.append(String.format("%d,",list.popBack()));
		assertEquals(output.toString(),"7,5,3,3,");
		assertTrue(list.isEmpty());

		list.add(5);
		list.add(5);
		assertEquals(list.size(),2);
		list.add(3);
		list.add(7);
		output = new StringBuilder();
		while(!list.isEmpty())
			output.append(String.format("%d,",list.popFront()));
		assertEquals(output.toString(),"3,5,5,7,");
	}

	@Test
	void listInitializationTest()
	{
		DoubleSortedLinkedList list1 = new DoubleSortedLinkedList(new int[] {3,5,7});
		list1.add(9);
		StringBuilder output = new StringBuilder();
		while(!list1.isEmpty())
			output.append(String.format("%d,",list1.popFront()));
		assertEquals(output.toString(),"3,5,7,9,");

		DoubleSortedLinkedList list2 = new DoubleSortedLinkedList(new int[] {1,11,2,7,8,5,6,3,4,9,10,12});
		output = new StringBuilder();
		while(!list2.isEmpty())
			output.append(String.format("%d,",list2.popFront()));
		assertEquals(output.toString(),"1,2,3,4,5,6,7,8,9,10,11,12,");
	}

	@Test
	void findTest(){
		DoubleSortedLinkedList list = new DoubleSortedLinkedList(new int[] {3,3,5,7,9});
		assertTrue(list.find(3));
		assertTrue(list.find(5));
		assertTrue(list.find(7));
		assertTrue(list.find(9));
		assertFalse(list.find(2));
		assertFalse(list.find(4));
		assertFalse(list.find(11));
	}

	
	@Test
	void removeFirstTest(){
		DoubleSortedLinkedList list = new DoubleSortedLinkedList(new int[] {3,3,5,6,6,6,9});
		list.removeFirst(3);
		assertEquals(list.peekFront(), 3);
		assertEquals(list.size(),6);
		list.removeFirst(9);
		assertEquals(list.peekBack(), 6);
		assertEquals(list.size(),5);
		list.removeFirst(3);
		assertEquals(list.size(),4);
		list.removeFirst(6);
		assertEquals(list.size(),3);
		list.removeFirst(6);
		assertEquals(list.size(),2);
		list.removeFirst(6);
		assertEquals(list.size(),1);
		assertEquals(5,list.popFront());
		assertTrue(list.isEmpty());
		list.add(7);
		list.add(3);
		list.add(5);
		assertEquals(list.size(),3);
		list.removeFirst(5);
		list.removeFirst(3);
		list.removeFirst(7);
		assertTrue(list.isEmpty());
	}

	@Test
	void removeAllTest(){
		DoubleSortedLinkedList list = new DoubleSortedLinkedList(new int[] {1,1,1,3,3,4,4,7,9,9});
		list.removeAll(3);
		assertEquals(list.size(),8);
		StringBuilder output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"1,1,1,4,4,7,9,9,");
		list.removeAll(1);
//		assertEquals(list.popFront(),4);
//		assertEquals(list.popBack(),9);
		//assertEquals(list.size(),5);
		output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"4,4,7,9,9,");
		list.removeAll(9);
		assertEquals(list.size(),3);
		output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"4,4,7,");
		assertEquals(list.popBack(),7);
		list.removeAll(7);
		output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"4,4,");
		list.removeAll(4);
		assertTrue(list.isEmpty());
		list.add(7);
		list.add(7);
		list.add(7);
		assertEquals(list.size(),3);
		list.removeAll(7);
		assertTrue(list.isEmpty());
		DoubleSortedLinkedList list2 = new DoubleSortedLinkedList(new int[] {9});
		list2.removeAll(9);
		list2.add(7);
		assertEquals(list2.popBack(), 7);
	}

	@Test
	void iteratorTest(){
		DoubleSortedLinkedList list = new DoubleSortedLinkedList();
		list.add(3);
		list.add(5);
		list.add(7);
		StringBuilder output = new StringBuilder();
		for(var i: list){
			output.append(String.format("%d,",i));
		}
		assertEquals(output.toString(),"3,5,7,");
	}

	@Test
	void get_set_Test()
	{
		DoubleSortedLinkedList list = new DoubleSortedLinkedList();
		list.add(3);
		list.add(5);
		assertEquals(list.get(1), 5);
		assertThrows(IndexOutOfBoundsException.class, ()->list.get(3));
		
		list.add(7);
		StringBuilder output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"3,5,7,");
	}

	@Test
	void clearTest()
	{
		DoubleSortedLinkedList list = new DoubleSortedLinkedList(new int[] {1,3,1,1,3,4,5,4,4,6});
		list.clear();
		assertTrue(list.isEmpty());
		list.add(4);
		assertEquals(list.size(),1);
		assertEquals(4,list.popFront());
		assertTrue(list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
		assertEquals(list.size(),0);
	}
	

}
