package longest.subsequence;

import java.util.Scanner;

public class LongestSubsequence {
    
    public static void longestSubsequenceLength(int[] sequence) {
        int max = 0, maxIndex = 0;
        int[] aux = new int[sequence.length];
        int[] prev = new int[sequence.length];
        aux[0] = 1;
        
        for (int i = 0; i < prev.length; ++i) {
            prev[i] = -1;
        }
        
        for (int pos = 1; pos < sequence.length; ++pos) {
            for (int i = 0; i < pos; ++i) {
                if (sequence[i] < sequence[pos]) {
                    if (max < aux[i]) {
                        max = aux[i];
                        maxIndex = i;
                    }
                }
            }
            aux[pos] = max + 1;
            prev[pos] = maxIndex;
            max = 0;
        }
        
        for (int i = 0; i < aux.length; ++i) {
            if (max < aux[i]) {
                max = aux[i];
                maxIndex = i;
            }
        }
        
        System.out.println(max);
        
        int[] path = new int[max];
        int p = path.length - 1;
        int index = maxIndex;
        while (index != -1 && p >= 0) {
            path[p--] = sequence[index];
            index = prev[index];
        }
        
        for (int i = 0; i < path.length; ++i) {
            System.out.print(path[i] + " ");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int size = scanner.nextInt();
        int[] array = new int[size];
        
        for (int i = 0; i < size; ++i) {
            array[i] = scanner.nextInt();
        }
        
        longestSubsequenceLength(array);
        
        scanner.close();
    }
    
}
