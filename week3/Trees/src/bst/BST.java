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

    public static boolean isLeftChildGreater(Node root) {
        return root.left != null && root.left.value > root.value;
    }
    
    public static boolean isRightChildSmaller(Node root) {
        return root.right != null && root.right.value < root.value;
    }
    
    public static boolean isRightChildOverRightBound(Node root, int rightBound) {
        return root.right != null && root.right.value > rightBound;
    }

    public static boolean isLeftChildOverLeftBound(Node root, int leftBound) {
        return root.left != null && root.left.value < leftBound;
    }
    
    public static boolean isBSTleft(Node root, int rightBound) {
        if (root == null) {
            return true;
        }
        
        if (isLeftChildGreater(root) || isRightChildSmaller(root)
                || isRightChildOverRightBound(root, rightBound)) {
            return false;
        }
        
        return isBSTleft(root.left, root.value) && isBSTright(root.right, root.value);
    }
    
    public static boolean isBSTright(Node root, int leftBound) {
        if (root == null) {
            return true;
        }
        
        if (isLeftChildGreater(root) || isRightChildSmaller(root)
                || isLeftChildOverLeftBound(root, leftBound)) {
            return false;
        }
        
        return isBSTleft(root.left, root.value) && isBSTright(root.right, root.value);
    }
    
    // Checks if a binary tree is a binary search tree.    
    public static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }

        if (isLeftChildGreater(root) || isRightChildSmaller(root)) {
            return false;
        }

        return isBSTleft(root.left, root.value) && isBSTright(root.right, root.value);
    }

}