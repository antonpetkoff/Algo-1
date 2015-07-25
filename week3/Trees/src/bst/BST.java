package bst;

import java.util.Scanner;

public class BST {

    public static int[] tree;
    
    public static int left(int i) {
        return 2 * i + 1;
    }
    
    public static int right(int i) {
        return 2 * i + 2;
    }
    
    public static String isBST() {
        return isBSTRange(0, Integer.MIN_VALUE, Integer.MAX_VALUE) ? "YES" : "NO";
    }

    public static boolean isBSTRange(int root, int left, int right) {
        if (root >= tree.length || tree[root] == 0) {
            return true;
        }
        
        if (tree[root] > left && tree[root] < right) {
            return isBSTRange(left(root), left, tree[root]) && isBSTRange(right(root), tree[root], right);
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        tree = new int[N];
        
        for (int i = 0; i < N; i++) {
            tree[i] = scanner.nextInt();
        }
        
        System.out.println(isBST());
        
        scanner.close();
    }
    
}