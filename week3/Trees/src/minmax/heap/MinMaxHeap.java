package minmax.heap;

import java.util.Scanner;

public class MinMaxHeap {
    
    public static int[] heap;
    public static int size;

    public static int left(int i) {
        return 2 * i + 1;
    }
    
    public static int right(int i) {
        return 2 * i + 2;
    }
    
    public static String isMinMax() {
        return isMinMaxLevel(0, 1) ? "YES" : "NO";
    }
    
    public static boolean isMinMaxLevel(int root, int level) {
        if (root >= heap.length) {
            return true;
        }
        
        if (level % 2 != 0) {
            if (left(root) < size && heap[root] > heap[left(root)]
                    || right(root) < size && heap[root] > heap[right(root)]) {
                return false;
            }
        } else {
            if (left(root) < size && heap[root] < heap[left(root)]
                    || right(root) < size && heap[root] < heap[right(root)]) {
                return false;
            }
        }
        
        return isMinMaxLevel(left(root), level + 1) && isMinMaxLevel(right(root), level + 1);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        size = scanner.nextInt();
        heap = new int[size];
        
        for (int i = 0; i < size; i++) {
            heap[i] = scanner.nextInt();
        }
        
        System.out.println(isMinMax());
        
        scanner.close();
    }
    
}