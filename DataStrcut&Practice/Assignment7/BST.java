import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;


//자료구조 과제7 부모 링크 유지 이진검색트리

public class BST implements Iterable<Integer> {
	
	public static class TreeNode {
		int key = -1;
		TreeNode left = null;
		TreeNode right = null;
		TreeNode parent = null;
		TreeNode() {}
		TreeNode(int key) {
			this.key = key;
		}
	}
	
	public class TreeIterator implements Iterator<Integer> {
		private List<Integer> visitedOrder = new ArrayList<>();
		private int curr = 0;
		
		public TreeIterator() {
			traversal(visitedOrder);
		}
		
		@Override public boolean hasNext() {
			return curr < visitedOrder.size();
		}
		
		@Override public Integer next() {
			int ret = visitedOrder.get(curr);
			++curr;
			return ret;
		}
	}
	
	private TreeNode root = null;
	private int numNodes = 0;
	
	private TreeTraversal traversalMethod = TreeTraversal.INORDER;
	
	public BST() {}
	
	public BST(int... keyList) {
		for(var n: keyList) add(n);
	}
	
	public int size() {
		// return numNodes;
		return size(root);
	}
	
	private int size(TreeNode node) {
		if(node == null) return 0;
		return 1 + size(node.left) + size(node.right);
	}
	
	public boolean isEmpty() {
		return numNodes == 0;
	}
	
	public void clear() {
		root = null;
		numNodes = 0;
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(TreeNode node) {
		if(node == null) return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	public void add(int key) {
		TreeNode newNode = new TreeNode(key);
		if(isEmpty()) root = newNode;
		else {
			TreeNode par = findNode(root, key);
			if(par.key == key) return;
			if(par.key > key) par.left = newNode;
			else par.right = newNode;
			newNode.parent = par;
		}
		++numNodes;
	}
	
	public boolean find(int key) {
		if(isEmpty()) return false;
		return findNode(root, key).key == key;
	}
	
	private TreeNode findNode(TreeNode node, int key) {
		if(node.key == key) return node;
		TreeNode nextNode = (node.key > key)? node.left: node.right;
		return nextNode == null? node: findNode(nextNode, key);
	}
	
	public void remove(int key) {
		if(isEmpty()) return;
		TreeNode delNode = findNode(root, key);
		if(delNode.key != key) return;
		TreeNode par = delNode == root? null: delNode.parent;
		if(delNode.left != null && delNode.right != null) {
			par = delNode;
			TreeNode prevNode = delNode.left;
			while(prevNode.right != null) {
				par = prevNode;
				prevNode = prevNode.right;
			}
			delNode.key = prevNode.key;
			delNode = prevNode;
		}
		removeChild(par, delNode);
		--numNodes;
	}
	
	private void removeChild(TreeNode par, TreeNode child) {
		TreeNode grandChild = child.left != null? child.left: child.right;
		if(par == null) root = grandChild;
		else {
			if(par.key >= child.key) par.left = grandChild;
			else par.right = grandChild;
		}
		if(grandChild != null) grandChild.parent = par;
	}

	public int prev(int key) {
		if(isEmpty()) throw new IllegalStateException("");
		TreeNode node = findNode(root, key);
		if(node.key != key) throw new IllegalArgumentException("");
		return prev(node, key);
	}
	
	public int prev(TreeNode node, int key) {
		if(node.left != null) {
			node = node.left;
			while(node.right != null) node = node.right;
			return node.key;
		}
		else {
			if(node.key != key) {
				TreeNode child = new TreeNode(key);
				TreeNode par = node;
				child.parent = node;
				TreeNode childCopy = child;
				while(par!=null) {
					if(par.key < child.key) {
						childCopy.parent = null;
						return par.key;
					}
					child = par;
					par = child.parent;
				}
				childCopy.parent = null;
			}
			else {
				TreeNode child = node;
				TreeNode par = node.parent; 
				while(par!=null) {
					if(par.key < child.key) return par.key;
					child = par;
					par = child.parent;
				}
			}
			return key;
		}
	}

	public int next(int key) {
		if(isEmpty()) throw new IllegalStateException("");
		TreeNode node = findNode(root, key);
		if(node.key != key) throw new IllegalArgumentException("");
		return next(node, key);
	}
	
	public int next(TreeNode node, int key) {
		if(node.right != null) {
			node = node.right;
			while(node.left != null) node = node.left;
			return node.key;
		}
		else {
			if(node.key != key) {
				TreeNode child = new TreeNode(key);
				TreeNode par = node;
				child.parent = node;
				TreeNode childCopy = child;
				while(par!=null) {
					if(par.key > child.key) {
						childCopy.parent = null;
						return par.key;
					}
					child = par;
					par = child.parent;	
				}
				childCopy.parent = null;
			}
			else {
				TreeNode child = node;
				TreeNode par = node.parent; 
				while(par!=null) {
					if(par.key > child.key) return par.key;
					child = par;
					par = child.parent;
				}
			}
			return key;
		}
	}
	
	public List<Integer> rangeSearch(int low, int high) {
		List<Integer> ret = new ArrayList<>();
		rangeSearch(root, low, high, ret);
		return ret;
	}
	
	private void rangeSearch(TreeNode node, int low, int high, List<Integer> ret) {
		if(node.left != null && node.key >= low) rangeSearch(node.left, low, high, ret);
		if(node.key >= low && node.key <= high) ret.add(node.key);
		if(node.right != null && node.key <= high) rangeSearch(node.right, low, high, ret);
	}
	
	public Neighbors nearestNeighbor(int key) {
		if(isEmpty()) throw new IllegalStateException("");
		TreeNode node = findNode(root, key);
		int prevKey = prev(node, key);
		int nextKey = next(node, key);
		OptionalInt prev = prevKey != key? OptionalInt.of(prevKey): OptionalInt.empty();
		OptionalInt next = nextKey != key? OptionalInt.of(nextKey): OptionalInt.empty();
		return new Neighbors(prev, next);
	}
	
	public void balanceTree() {
		List<Integer> visitedOrder = new ArrayList<>();
		inorder(root, visitedOrder);
		clear();
		balanceTree(visitedOrder, 0, visitedOrder.size() - 1);
	}
	
	private void balanceTree(List<Integer> visitedOrder, int lo, int hi) {
		if(lo == hi) add(visitedOrder.get(lo));
		else if(lo < hi) {
			int mid = lo + (hi-lo)/2;
			add(visitedOrder.get(mid));
			balanceTree(visitedOrder, lo, mid - 1);
			balanceTree(visitedOrder, mid + 1, hi);
		}
	}
	
	public void setIteratorType(TreeTraversal traversalMethod) {
		this.traversalMethod = traversalMethod;
	}
	
	private void preorder(TreeNode node, List<Integer> visitedOrder) {
		visitedOrder.add(node.key);
		if(node.left != null) preorder(node.left, visitedOrder);
		if(node.right != null) preorder(node.right, visitedOrder);
	}
	
	private void inorder(TreeNode node, List<Integer> visitedOrder) {
		if(node.left != null) inorder(node.left, visitedOrder);
		visitedOrder.add(node.key);
		if(node.right != null) inorder(node.right, visitedOrder);
	}
	
	private void postorder(TreeNode node, List<Integer> visitedOrder) {
		if(node.left != null) postorder(node.left, visitedOrder);
		if(node.right != null) postorder(node.right, visitedOrder);
		visitedOrder.add(node.key);
	}
	
	private void traversal(List<Integer> visitedOrder) {
		if(isEmpty()) return;
		switch(traversalMethod) {
		case PREORDER: preorder(root, visitedOrder); break;
		case INORDER: inorder(root, visitedOrder); break;
		case POSTORDER: postorder(root, visitedOrder); break;
		}
	}
	
	@Override public Iterator<Integer> iterator() {
		return new TreeIterator();
	}
	
}
