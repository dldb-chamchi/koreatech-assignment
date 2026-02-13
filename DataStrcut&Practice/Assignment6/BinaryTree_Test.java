import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinaryTree_Test {

	
	
	@Test
	void find_test() {
		BinaryTree tree = new BinaryTree("1,-2,3");
		assertFalse(tree.find(-1));
		assertTrue(tree.find(3));
		
		tree = new BinaryTree("1,null,2,null,null,3,null,null,null,null,null,4,5");
		assertTrue(tree.find(1));
		assertTrue(tree.find(5));
		assertFalse(tree.find(6));
		
		tree = new BinaryTree("1,2,null,3,4,5,null,6,7,null,null,null,null,7");
		assertFalse(tree.find(-1));
		assertFalse(tree.find(8));
		assertTrue(tree.find(7));
		assertFalse(tree.find(-4));
		
		tree = new BinaryTree("5,4,8,11,null,13,4,7,2,null,null,null,1");
		assertTrue(tree.find(11));
		assertTrue(tree.find(2));
		assertTrue(tree.find(13));
		assertTrue(tree.find(1));
		assertFalse(tree.find(6));
	}
	
	
	
	@Test
	void pathsum_test() {
		BinaryTree tree = new BinaryTree("1,-2,3");
		assertFalse(tree.hasPathSum(-2));
		assertTrue(tree.hasPathSum(4));
		
		tree = new BinaryTree("1,2,null,3,null,null,null,4,null,null,null,null,null,null,null,5");
		assertFalse(tree.hasPathSum(3));
		assertTrue(tree.hasPathSum(15));
		
		tree = new BinaryTree("1,-2,-3,1,3,-2,null,-1");
		assertFalse(tree.hasPathSum(-3));
		assertTrue(tree.hasPathSum(-1));
		assertTrue(tree.hasPathSum(2));
		assertTrue(tree.hasPathSum(-4));
		
		tree = new BinaryTree("5,4,8,11,null,13,4,7,2,null,null,null,1");
		assertTrue(tree.hasPathSum(27));
		assertTrue(tree.hasPathSum(22));
		assertFalse(tree.hasPathSum(26));
		assertFalse(tree.hasPathSum(18));
		assertTrue(tree.hasPathSum(17));
	}
	
}
