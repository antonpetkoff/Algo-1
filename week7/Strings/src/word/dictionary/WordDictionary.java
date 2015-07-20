package word.dictionary;

import java.util.Scanner;

public class WordDictionary {

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
