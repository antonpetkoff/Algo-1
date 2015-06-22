package bst;

public class BST {

    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    
    // Checks if a binary tree is a binary search tree.    
    public static boolean isBST(Node root) {
        return isBSTRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTRange(Node root, int left, int right) {
        if (root == null) {
            return true;
        }
        
        if (root.value > left && root.value < right) {
            return isBSTRange(root.left, left, root.value) && isBSTRange(root.right, root.value, right);
        }
        
        return false;
    }
    
}