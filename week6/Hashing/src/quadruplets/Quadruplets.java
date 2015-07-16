package quadruplets;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Quadruplets {

    public static Map<Integer, Integer> combine(Integer[] a, Integer[] b) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                sum = a[i] + b[j];
                if (map.containsKey(sum)) {
                    map.put(sum, 1 + map.get(sum));
                } else {
                    map.put(sum, 1);
                }
            }
        }
        return map;
    }
    
    public static int quadrupletsSumToZero(Integer[][] ints) {
        int result = 0;
        Map<Integer, Integer> map = combine(ints[0], ints[1]);
        
        Integer[] a = ints[2], b = ints[3];
        int sum = 0;
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                sum = -a[i] - b[j];
                if (map.containsKey(sum)) {
                    result += map.get(sum);
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        Integer[][] ints = new Integer[4][N];
        
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < N; ++j) {
                ints[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println(quadrupletsSumToZero(ints));
        
        scanner.close();
    }
    
}
