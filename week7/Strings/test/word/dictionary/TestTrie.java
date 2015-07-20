package word.dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTrie {

    private Trie trie = new Trie();
    
    @Test
    public void testEqualPrefix() {
        trie.insert("alabala");
        assertTrue(trie.contains("alabala"));
        assertFalse(trie.contains("alabal"));
        
        trie.insert("alabal");
        assertTrue(trie.contains("alabal"));
        assertTrue(trie.contains("alabala"));
    }
    
}
