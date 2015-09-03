package algoCompetition;

import java.util.LinkedList;
import java.util.Scanner;

public class Prefix {

    public static final int ALPHABET_SIZE = 26;

    public static class Node {
        public Node parentNode;
        public char parentChar;
        
        public Node[] next;
        public boolean isTerminal;
        public int count;
        
        public Node() {
            this.next = new Node[ALPHABET_SIZE];
            this.isTerminal = false;
            this.count = 1;
            this.parentChar = ' ';
            this.parentNode = null;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < ALPHABET_SIZE; ++i) {
                if (next[i] != null) {
                    sb.append((char) ('a' + i));
                }
            }
            
            return sb.toString();
        }
    }
    
    public static class Trie {
        public Node root;

        public Trie() {
            root = new Node();
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
                    
                    temp.next[index].parentChar = word.charAt(i);
                    temp.next[index].parentNode = temp;
                } else {
                    ++temp.next[index].count;
                }
                temp = temp.next[index];
            }
            temp.isTerminal = true;
        }

    }

    public static void prefixes(Trie trie) {
        LinkedList<Node> currentLevel = new LinkedList<Node>(),
                nextLevel = new LinkedList<Node>();
        currentLevel.addLast(trie.root);
        
        
        int max = 0;
        Node temp = null, neighbour = null, maxNode = null;
        while (!currentLevel.isEmpty()) {
            temp = currentLevel.removeFirst();
            
            for (int i = 0; i < ALPHABET_SIZE; ++i) {
                neighbour = temp.next[i];
                if (neighbour != null) {
                    nextLevel.addLast(neighbour);
                    if (neighbour.count > max) {
                        max = neighbour.count;
                        maxNode = neighbour;
                    }
                }
            }
            
            if (currentLevel.isEmpty()) {
                if (max == 0) {
                    return;
                } else {
                    StringBuilder sb = new StringBuilder("");
                    Node tempNode = maxNode;
                    while (tempNode != null) {
                        sb.append(tempNode.parentChar);
                        tempNode = tempNode.parentNode;
                    }
                    System.out.println(sb.reverse().toString().trim() + "(" + max +")");
                }
                
                max = 0;
                
                LinkedList<Node> tempQueue = currentLevel;
                currentLevel = nextLevel;
                nextLevel = tempQueue;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        Trie trie = new Trie();
        
        String temp = null;
        for (int i = 0; i < N; ++i) {
            temp = scanner.next();
            trie.insert(temp);
        }
        
        prefixes(trie);
        
        scanner.close();
    }

}
