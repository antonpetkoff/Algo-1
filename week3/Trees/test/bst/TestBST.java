package bst;

import org.junit.Test;

import bst.BST.Node;
import static bst.BST.isBST;
import static org.junit.Assert.*;

public class TestBST {

    @Test
    public void testIsBSTtrue() {
        Node root = new Node(12,
                new Node(4, new Node(2, null, null), new Node (5, null, null)), 
                new Node(17, new Node(14, null, null), null));
        assertTrue(isBST(root));
    }
    
    @Test
    public void testIsBSTfalse() {
        Node root = new Node(12,
                new Node(4, new Node(2, null, null), new Node (500, null, null)), 
                new Node(17, new Node(14, null, null), null));
        assertFalse(isBST(root));
    }
    
}
