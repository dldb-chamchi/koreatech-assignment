//Leetcode 139

class Solution {
    private TrieNode root;
    private Map<String, Boolean> visit;

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private void insert(String word) {
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                int idx = c - 'a';
                if(curr.children[idx] == null)
                    curr.children[idx] = new TrieNode();
                curr = curr.children[idx];
            }
            curr.isEnd = true;
    }

    private boolean search(String word) {
        TrieNode curr = root;
        for(int i=0; i<word.length(); ++i){
            int idx = word.charAt(i) - 'a';
            if(curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.isEnd == true;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        root = new TrieNode();
        visit = new HashMap<>();
        for(String word : wordDict){
            insert(word);
        }
        return check(s);
    }

    private boolean check(String s){
        if(s.length() == 0) return true;
        if(visit.containsKey(s)) return visit.get(s);

        for(int i=1; i<=s.length(); ++i){
            if(search(s.substring(0, i)) && check(s.substring(i))){
                visit.put(s, true);
                return true;
            }
        }
        visit.put(s, false);
        return false;        
    }
}