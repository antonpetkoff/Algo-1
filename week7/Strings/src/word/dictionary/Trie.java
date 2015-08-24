package word.dictionary;

public class Trie {

    private static final int ALPHABET_SIZE = 26;
    
    private static class Node {
        public Node[] next;
        public boolean isTerminal;
        
        public Node() {
            this.next = new Node[ALPHABET_SIZE];
            this.isTerminal = false;
        }
    }
    
    private Node root;
    
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        if (word.length() == 0) {
            root.isTerminal = true;
            return;
        }
        
        Node temp = root;
        int i = 0, index = 0;
        for (i = 0; i < word.length() - 1; ++i) {
            index = word.charAt(i) - 'a';
            if (temp.next[index] == null) {
                temp.next[index] = new Node();
            }
            temp = temp.next[index];
        }
        temp.isTerminal = true;
    }
    
    public boolean contains(String word) {
        if (word.length() == 0) {
            return root.isTerminal;
        }
        
        Node temp = root;
        int i = 0, index = 0;
        for (i = 0; i < word.length() - 1; ++i) {
            index = word.charAt(i) - 'a';
            if (temp.next[index] != null) {
                temp = temp.next[index];
            }
        }
        
        return temp.isTerminal;
    }
    
}
