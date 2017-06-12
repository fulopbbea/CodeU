package assignment3;

import java.util.List;

public class Dictionary {
    
    private class Node {
        private final Node[] next;
        private boolean isWord;
        
        public Node() {
            this.next = new Node[26];
            this.isWord = false;
            for (Node n : next) {
                n = null;
            }
        }
        
        public Node addNode(char l) {
            if (next[l - 'a'] == null) {
                next[l - 'a'] = new Node();
            }
            return next[l - 'a'];
        }
        
        public Node getNode(char l) {
            return next[l - 'a'];
        }
        
        public boolean isWord() {
            return this.isWord;
        }
        
        public void setWord() {
            this.isWord = true;
        }
    }
    
    private final Node root;
    private final int size;
    
    public Dictionary(List<String> words) {
        this.root = new Node();
        this.size = words.size();
        for (String word : words) {
            word = word.toLowerCase();
            Node iter = this.root;
            for (int i = 0; i < word.length(); i++) {
                iter = iter.addNode(word.charAt(i));
            }
            iter.setWord();
        }
    }
    
    boolean isPrefix(String prefix) {
        Node iter = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            iter = iter.getNode(prefix.charAt(i));
            if (iter == null) {
                return false;
            }
        }
        return true;
    }
    
    boolean isWord(String word) {
        Node iter = this.root;
        for (int i = 0; i < word.length(); i++) {
            iter = iter.getNode(word.charAt(i));
            if (iter == null) {
                return false;
            }
        }
        return iter.isWord();
    }
    
    public int size() {
        return this.size;
    }
}
