package minmax.heap;

public class MinMaxHeap {

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

    // Checks if a binary tree is a min/max heap.
    public static boolean isMinMax(Node root) {
        return isMinMaxLevel(root, 1);
    }
    
    public static boolean isMinMaxLevel(Node root, int level) {
        if (root == null) {
            return true;
        }
        
        if (level % 2 != 0) {
            if (root.left != null && root.value > root.left.value
                    || root.right != null && root.value > root.right.value) {
                return false;
            }
        } else {
            if (root.left != null && root.value < root.left.value
                    || root.right != null && root.value < root.right.value) {
                return false;
            }
        }
        
        return isMinMaxLevel(root.left, level + 1) && isMinMaxLevel(root.right, level + 1);
    }
}