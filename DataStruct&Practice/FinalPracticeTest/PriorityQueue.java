import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자료구조및실습
 * @version 2024년도 2학기
 * @author 김상진
 * @file PriorityQueue.java
 * 이진 검색 트리를 이용한 우선순위 큐 
 * 부모에 대한 포인터 유지
 * 학번:	2023100657	이름: 임아리
 */
public class PriorityQueue {
	
	private static class TreeNode {
		private int key = -1;
		private TreeNode left = null;
		private TreeNode right = null;
		private TreeNode parent = null;	// 추가
		@SuppressWarnings("unused")
		public TreeNode(){}
		public TreeNode(int key){
			this.key = key;
		}
	}
	
	private TreeNode root = null;
	private TreeNode minNode = null;
	private int numNodes = 0;
	
	public PriorityQueue() {}
	
	public PriorityQueue(int... keyList) {
		for(var n: keyList) add(n);
	}
	
	public int size() {
		return numNodes;
	}
	
	public boolean isEmpty() {
		return numNodes == 0;
	}
	
	public void clear() {
		root = minNode = null;
		numNodes = 0;
	}
	
	public int getMin() {
		if(isEmpty()) throw new IllegalStateException();
		return minNode.key;
	}
	
	public void add(int key) {
		TreeNode newNode = new TreeNode(key);
		if(isEmpty()) root = minNode = newNode;
		else{
			TreeNode parent = findNode(root, key);
			if(parent.key == key) return;
			if(parent.key > key) parent.left = newNode;
			else parent.right = newNode;
			newNode.parent = parent;
			if(key < minNode.key) minNode = newNode;
		}
		++numNodes;
	}
	
	private TreeNode findNode(TreeNode node, int key) {
		if(node.key == key) return node;
		TreeNode nextNode = (node.key > key)? node.left: node.right;
		return nextNode == null? node: findNode(nextNode, key);
	}
	
	public int extractMin() {
		if(isEmpty()) throw new IllegalStateException();
		int ret = minNode.key;
		removeMin();
		return ret;
	}
	
	// 3) minNode가 가리키는 가장 작은 값을 제거하고 minNode가 그다음 작은 값을 가리키도록 갱신함
	private void removeMin() {
		--numNodes;
		if(root == minNode) {
			if (root.right == null) {
				root = null;
				minNode = null;
			} else {
				minNode = root.right;
				root = root.right;
			}
		}
		else {
			if (minNode.right != null) {
				TreeNode curr = minNode.right;
				while(curr.left!=null){
					curr = curr.left;
				}

				minNode.parent.left = curr;
				minNode = minNode.parent.key > minNode.right.key ? curr : minNode.parent;
			} else {
				minNode.parent.left = null;
				minNode = minNode.parent;
			}
		}
	}

	//4번 헬퍼함수
	private int checkHeight(TreeNode node) {
		if (node == null) return 0;

		int leftHeight = checkHeight(node.left);
		if (leftHeight == -1) return -1;

		int rightHeight = checkHeight(node.right);
		if (rightHeight == -1) return -1;

		if (Math.abs(leftHeight - rightHeight) > 1) return -1;

		return 1 + Math.max(leftHeight, rightHeight);
	}

	// 4) (재귀적 구현) 이 트리의 모든 노드가 AVL 특성을 만족하면 true를 반환하고, 아니면 false를 반환해야 함
	public boolean isBalanced() {
		return checkHeight(root) != -1;
	}

	// 5) 트리가 유지하는 값을 내림차순으로 유지하고 있는 선형 리스트를 반환해야 함
	public List<Integer> reverseOrder() {
		List<Integer> keys = new ArrayList<>();
		if(root == null) return keys;
		revers(root, keys);
		return keys;
	}

	private void revers(TreeNode node, List<Integer> keys){
		if(node.left!=null) revers(node.left, keys);
		keys.addFirst(node.key);
		if(node.right!=null) revers(node.right, keys);
	}
	
	// 6) 트리에 있는 값 중 lo와 같거나 크고 hi와 같거나 작은 값들을 오름차순으로 유지하는 선형 리스트를 반환해야 함
	public List<Integer> rangeSearch(int lo, int hi) {
		List<Integer> keys = new ArrayList<>();
		if(root == null) return keys;
		TreeNode lowNode = helper(root, lo, hi);

		return keys;
	}
	private TreeNode helper(TreeNode node, int lo, int hi) {
		if(node.key < lo) return node;
		else if(node.key > hi) return helper(node.left, lo, hi);
		else return helper(node.right, lo, hi);
	}

	private int next(TreeNode node, int lo, int hi){
		if(lo < node.key && node.key > hi) return node.key;
		return 0;
	}

}
