//비정렬 연결 리스트 테스트

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnsortedLinkedListTest {

	@Test
	void listEmptyInitializationTest()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		assertEquals(list.size(), 0);
		assertTrue(list.isEmpty());
		assertFalse(list.isFull());
	}

	@Test
	void PushAndPopBackTest()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		list.pushBack(3);
		list.pushBack(5);
		list.pushBack(7);
		list.pushBack(3);
		assertEquals(list.size(),4);
//		assertEquals(list.popBack(), 3);
//		assertEquals(list.popBack(), 7);
//		assertEquals(list.popBack(), 5);
//		assertEquals(list.popBack(), 3);
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty())
			output.append(String.format("%d,",list.popBack()));
		assertEquals(output.toString(),"3,7,5,3,"); //?
	}

	@Test
	void PushAndPopBackTest_EXTRA()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		assertThrows(RuntimeException.class, ()->list.popBack());
		list.pushBack(1);
		list.pushBack(2);
		list.pushBack(3);
		list.pushBack(4);
		list.pushBack(1);
		list.pushBack(2);
		list.pushBack(3);
		list.pushBack(4);
		list.pushBack(1);
		list.pushBack(2);
	}

	@Test
	void PushAndPopFrontTest()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		assertThrows(RuntimeException.class, ()->list.popFront());
		list.pushFront(3);
		list.pushFront(5);
		list.pushFront(7);
		list.pushFront(3);
		list.pushFront(7);
		assertEquals(list.size(),5);
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty())
			output.append(String.format("%d,",list.popFront()));
		assertEquals(output.toString(),"7,3,7,5,3,");
	}

	@Test
	void PushPopBackFrontTest()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		list.pushFront(3);
		assertEquals(list.peekFront(), 3);
		assertEquals(list.peekBack(), 3);
		list.pushFront(5);
		list.pushFront(7);
		assertEquals(list.peekFront(), 7);
		assertEquals(list.peekBack(), 3);
		list.pushBack(1);
		list.pushBack(2); // 7 5 3 1 2
		assertEquals(list.peekFront(), 7);
		assertEquals(list.peekBack(), 2);
		assertEquals(list.size(),5);
		StringBuilder output = new StringBuilder();
		while(!list.isEmpty())
			output.append(String.format("%d,",list.popFront()));
		assertEquals(output.toString(),"7,5,3,1,2,");
	}

	@Test
	void listInitializationTest()
	{
		UnsortedLinkedList list1 = new UnsortedLinkedList(new int[] {3,5,7});
		list1.pushBack(9);
		StringBuilder output = new StringBuilder();
		while(!list1.isEmpty())
			output.append(String.format("%d,",list1.popFront()));
		assertEquals(output.toString(),"3,5,7,9,");

		UnsortedLinkedList list2 = new UnsortedLinkedList(new int[] {1,2,3,4,5,6,7,8,9,10,11});
		output = new StringBuilder();
		while(!list2.isEmpty())
			output.append(String.format("%d,",list2.popFront()));
		assertEquals(output.toString(),"1,2,3,4,5,6,7,8,9,10,11,");
	}

	@Test
	void findTest(){
		UnsortedLinkedList list = new UnsortedLinkedList(new int[] {3,3,5,7,9});
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
		UnsortedLinkedList list = new UnsortedLinkedList();
		list.pushBack(3);
		list.pushBack(5);
		list.removeFirst(3);
		assertEquals(list.size(),1);
		
		UnsortedLinkedList list2 = new UnsortedLinkedList();
		list2.pushBack(3);
		list2.removeFirst(3);
		assertEquals(list2.size(),0);
		//list.removeFirst(7);
		//list.removeFirst(3);
		
		UnsortedLinkedList list3 = new UnsortedLinkedList();
		list3.pushBack(1);
		list3.removeFirst(0);
		assertEquals(list3.size(),1);
		
		
//		UnsortedLinkedList list4 = new UnsortedLinkedList();
//		list4.removeFirst(0); //예외 발생
		
	}

	
	@Test
	void removeAllTest(){
		UnsortedLinkedList list = new UnsortedLinkedList(new int[] {1,3,1,1,3,4,5,4,4,6});
		list.removeAll(0);
		list.removeAll(2);
		list.removeAll(8);
		//없는 값 지우려고 함
		assertEquals(list.size(),10);
		
		list.removeAll(3); //3은 잘 지워짐
		assertEquals(list.size(),8);
		StringBuilder output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"1,1,1,4,5,4,4,6,");
		list.removeAll(1);
		StringBuilder output2 = new StringBuilder();
		for(var i: list)
			output2.append(String.format("%d,",i));
		assertEquals(output2.toString(),"4,5,4,4,6,");
		assertEquals(list.size(),5); 
		output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"4,5,4,4,6,");
		list.removeFirst(5);
		assertEquals(list.size(),4);
		list.removeAll(4);
		assertEquals(list.size(),1);
		assertEquals(6,list.popFront());
		assertTrue(list.isEmpty());
		list.pushBack(3);
		list.pushFront(3);
		list.pushFront(3);
		list.removeAll(3);
		assertTrue(list.isEmpty());
//		UnsortedLinkedList list = new UnsortedLinkedList(new int[] {1});
//		list.removeAll(1);
//		assertEquals(list.size(),0);
	}
	

	@Test
	void iteratorTest(){
		UnsortedLinkedList list = new UnsortedLinkedList();
		list.pushBack(3);
		list.pushBack(5);
		list.pushBack(7);
		StringBuilder output = new StringBuilder();
		for(var i: list){
			output.append(String.format("%d,",i));
		}
		assertEquals(output.toString(),"3,5,7,");
	}

	@Test
	void get_set_Test()
	{
		UnsortedLinkedList list = new UnsortedLinkedList();
		list.pushBack(3);
		list.pushBack(5);
		assertEquals(list.get(1), 5);
		assertThrows(IndexOutOfBoundsException.class, ()->list.get(2));
		assertThrows(IndexOutOfBoundsException.class, ()->list.set(2, 0));

		list.pushBack(7);
		list.set(0, 4);
		StringBuilder output = new StringBuilder();
		for(var i: list)
			output.append(String.format("%d,",i));
		assertEquals(output.toString(),"4,5,7,");
	}

	@Test
	void clearTest()
	{
		UnsortedLinkedList list = new UnsortedLinkedList(new int[] {1,3,1,1,3,4,5,4,4,6});
		list.clear();
		assertTrue(list.isEmpty());
		list.pushBack(4);
		assertEquals(list.size(),1);
		assertEquals(4,list.popFront());
		assertTrue(list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
		assertEquals(list.size(),0);
	}

}
