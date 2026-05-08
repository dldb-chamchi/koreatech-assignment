import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;


//자료구조 과제7 부모 링크 유지 이진검색트리

class BST_Test {

	@Test
	void init_test() {
		BST tree = new BST();
		assertEquals(tree.size(), 0);
		assertEquals(tree.height(), -1);
		assertTrue(tree.isEmpty());
	}

	@Test
	void add_test() {
		BST tree = new BST();
		tree.add(3);
		tree.add(5);
		tree.add(1);
		tree.setIteratorType(TreeTraversal.PREORDER);
		StringBuilder output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"3,1,5,");
		
		tree.clear();
		tree.add(1);
		tree.add(3);
		tree.add(5);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,3,5,");
		
		tree.clear();
		tree.add(5);
		tree.add(3);
		tree.add(1);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"5,3,1,");
	}
	
	@Test
	void initializer_test() {
		BST tree = new BST(new int[] {4,1,3,6,5,2});
		assertEquals(tree.size(), 6);
		StringBuilder output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,2,3,4,5,6,");
	}
	
	@Test
	void height_test() {
		BST tree = new BST();
		assertEquals(tree.height(), -1);
		tree.add(1);
		tree.add(3);
		tree.add(5);
		assertEquals(tree.height(), 2);

		tree.clear();
		tree.add(7);
		tree.add(5);
		tree.add(3);
		tree.add(1);
		assertEquals(tree.height(), 3);

		tree.clear();
		tree.add(5);
		tree.add(7);
		tree.add(3);
		tree.add(1);
		assertEquals(tree.height(), 2);
	}
	
	@Test
	void find_test() {
		BST tree = new BST(7,1,3,9,10,4,2);

		assertEquals(7,tree.size());
		assertTrue(tree.find(3));
		assertTrue(tree.find(2));
		assertTrue(tree.find(7));
		assertTrue(tree.find(10));
		assertFalse(tree.find(0));
		assertFalse(tree.find(5));
		assertFalse(tree.find(11));
	}
	
	@Test
	void traversal_test() {
		BST tree = new BST(5,2,7,1,3,6,8);
		StringBuilder output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,2,3,5,6,7,8,");
		
		tree.setIteratorType(TreeTraversal.PREORDER);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"5,2,1,3,7,6,8,");

		tree.setIteratorType(TreeTraversal.POSTORDER);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,3,2,6,8,7,5,");
	}
	
	@Test
	void remove_test() {
		BST tree = new BST(7,1,3,9,10,4,2,8);
		tree.remove(10);
		StringBuilder output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,2,3,4,7,8,9,");
		tree.remove(7);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"1,2,3,4,8,9,");

		tree.remove(1);
		output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"2,3,4,8,9,");
		
		tree = new BST(5,3,7);
		tree.remove(3);
		assertEquals(tree.size(),2);
		tree.remove(7);
		assertEquals(tree.size(),1);
		tree.remove(5);
		assertTrue(tree.isEmpty());
		tree.add(6);
		assertEquals(tree.size(),1);
	}
	
	@Test
	void next_test() {
		BST tree = new BST(7,1,3,9,10,4,2,8);

		assertEquals(4,tree.next(3));
		assertThrows(IllegalArgumentException.class, ()->tree.next(6));
		assertEquals(7,tree.next(4));
		assertEquals(8,tree.next(7));
		assertEquals(10,tree.next(9));
		tree.clear();
		assertThrows(IllegalStateException.class, ()->tree.next(6));
	}
	
	@Test
	void prev_test() {
		BST tree = new BST(7,1,3,9,10,4,2,8);

		assertEquals(2,tree.prev(3));
		assertEquals(3,tree.prev(4));
		assertThrows(IllegalArgumentException.class, ()->tree.prev(6));
		assertEquals(7,tree.prev(8));
		assertEquals(9,tree.prev(10));
		tree.clear();
		assertThrows(IllegalStateException.class, ()->tree.prev(6));
	}
	
	
	
	@Test
	void rangesearch_test() {
		BST tree = new BST(6,7,2,3,10,1,19,20);
		List<Integer> range = tree.rangeSearch(4,19);
		String output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"6,7,10,19");
		range = tree.rangeSearch(2,18);
		output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"2,3,6,7,10");
		range = tree.rangeSearch(2,10);
		output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"2,3,6,7,10");
		range = tree.rangeSearch(20,30);
		output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"20");
		range = tree.rangeSearch(-5,3);
		output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"1,2,3");

		tree = new BST(5,3,8,1,4);
		range = tree.rangeSearch(4,6);
		output = range.stream().map(n->n.toString()).collect(Collectors.joining(","));
		assertEquals(output,"4,5");
	}
	
	@Test
	void nearestNeighbor_test() {
		BST tree = new BST(new int[]{6,7,2,3,10,1,19,20});
		Neighbors neighbor = tree.nearestNeighbor(4);
		assertEquals(neighbor.prev().getAsInt(), 3);
		assertEquals(neighbor.next().getAsInt(), 6);
		neighbor = tree.nearestNeighbor(2);
		assertEquals(neighbor.prev().getAsInt(), 1);
		assertEquals(neighbor.next().getAsInt(), 3);
		neighbor = tree.nearestNeighbor(1);
		assertFalse(neighbor.prev().isPresent());
		assertEquals(neighbor.next().getAsInt(), 2);
		neighbor = tree.nearestNeighbor(20);
		assertEquals(neighbor.prev().getAsInt(), 19);
		assertFalse(neighbor.next().isPresent());
		neighbor = tree.nearestNeighbor(21);
		assertEquals(neighbor.prev().getAsInt(), 20);
		assertFalse(neighbor.next().isPresent());
		neighbor = tree.nearestNeighbor(-5);
		assertFalse(neighbor.prev().isPresent());
		assertEquals(neighbor.next().getAsInt(), 1);
	}

	@Test
	void balanceTree_test() {
		BST tree = new BST(7,1,3,10,2,8,5);
		assertEquals(3,tree.height());
		tree.balanceTree();
		assertEquals(2 ,tree.height());
		tree.setIteratorType(TreeTraversal.PREORDER);
		StringBuilder output = new StringBuilder();
		for(var key: tree) {
			output.append(String.format("%d,", key));
		}
		assertEquals(output.toString(),"5,2,1,3,8,7,10,");
	}
}
