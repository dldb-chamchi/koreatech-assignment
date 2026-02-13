import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



public class BinaryTree {
	private static class TreeNode {
		private int v = -1;
		private TreeNode left = null;
		private TreeNode right = null;
		public TreeNode(int v) {
			this.v = v;		
		}
	}
	
	private TreeNode root = null;
	private int numNodes = 0;
	
	public BinaryTree() {}
	public BinaryTree(String tree) {
		String[] values = tree.split("\\,");
		root = stringToBinaryTree(values, 0);
	}
	
	private TreeNode stringToBinaryTree(String[] values, int idx) {
		if(idx >= values.length) return null;
		if(values[idx].equals("null")) return null;
		TreeNode node = new TreeNode(Integer.parseInt(values[idx]));
		++numNodes;
		int left = 2 * idx + 1;
		int right = left + 1;
		node.left = stringToBinaryTree(values, left);
		node.right = stringToBinaryTree(values, right);
		return node;
	}
	
	public int size() {
		return 0;
	}
	
	public int height() {
		return -1;
	}
	
	public boolean isSameR(BinaryTree other) {
		return false;
	}
	
	public boolean isSameNR(BinaryTree other) {
		return false;
	}
	
	public boolean find(int value) {
		if(root == null) return false;
		Queue<TreeNode> Q = new LinkedList<>();
		Q.add(root);
		while(!Q.isEmpty()){
			TreeNode node = Q.remove();
			if(node.v == value) return true;
			if(node.left != null) Q.add(node.left);
			if(node.right != null) Q.add(node.right);
		}
		return false;
	}
	
	public boolean hasPathSum(int target) {
		return hasPathSum(root, target, 0);
	}

	public boolean hasPathSum(TreeNode node, int target, int sum) {
	    if (node == null) return false;
	    
	    sum = sum + node.v;
	    
	    // 단말 노드 도달
	    if (node.left == null && node.right == null) return sum == target;

	    return hasPathSum(node.left, target, sum) || hasPathSum(node.right, target, sum);
	}

	
	public void debugPrint() {
		if(numNodes == 0) {
			System.out.println("empty binary tree");
			return;
		}
		int H = height();
		String[] tree = new String[(int)Math.pow(2, H + 1) - 1];
		Arrays.fill(tree, "null");
		binaryTreeToString(tree, root, 0);
		System.out.println(Arrays.toString(tree));
	}
	
	private void binaryTreeToString(String[] tree, TreeNode node, int idx) {
		if(idx >= tree.length) return;
		tree[idx] = "" + node.v;
		if(node.left != null) binaryTreeToString(tree, node.left, 2 * idx + 1);
		if(node.right != null) binaryTreeToString(tree, node.right, 2 * idx + 2);
	}
}
