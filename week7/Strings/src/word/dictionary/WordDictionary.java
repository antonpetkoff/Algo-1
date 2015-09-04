package word.dictionary;

import java.util.Scanner;

public class WordDictionary {

    public static class Trie {

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
            for (i = 0; i < word.length(); ++i) {
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
            for (i = 0; i < word.length(); ++i) {
                index = word.charAt(i) - 'a';
                if (temp.next[index] != null) {
                    temp = temp.next[index];
                }
            }
            
            return temp.isTerminal;
        }
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        String command = null;
        Trie trie = new Trie();
        
        for (int i = 0; i < N; ++i) {
            command = scanner.next();
            if (command.equals("insert")) {
                trie.insert(scanner.next());
            } else if (command.equals("contains")) {
                System.out.println(trie.contains(scanner.next()));
            }
        }
        
        scanner.close();
    }
    
}
