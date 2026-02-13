//Leetcode 208

class Trie {

    class Node{
        Node children[] = new Node[26];
        boolean isEnd = false;
        int count = 0;
        public Node () {}
    }

    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        if(root == null) new Node();
        Node curr = root;
        for(int i=0; i<word.length(); ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null){
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        if(root == null) return false;
        Node curr = root;
        for(int i=0; i<word.length(); ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.isEnd == true;
    }
    
    public boolean startsWith(String prefix) {
        if(root == null) return false;
        Node curr = root;
        for(int i=0; i<prefix.length(); ++i){
            int idx = prefix.charAt(i) - 'a';
            if(curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return true;
    }
}