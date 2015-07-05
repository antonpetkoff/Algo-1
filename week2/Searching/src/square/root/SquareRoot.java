package square.root;

import java.util.Scanner;

public class SquareRoot {
    
    public static final double PRECISION = 0.000001;
    public static final int ITER_BOUND = 100;
    
    public static double sqrt(double n) {
        double lo = 0.0, hi = n, mid = 0.0;
        int iter = 0;
        
        while (Math.abs(hi - lo) > PRECISION && iter < ITER_BOUND) {
            mid = lo + (hi - lo) / 2.0;
            if (mid * mid > n) {
                hi = mid;
            } else {
                lo = mid;
            }
            ++iter;
        }
        
        return mid;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double input = (double) scanner.nextInt();
        System.out.printf("%.5f", sqrt(input));
        scanner.close();
    }
    
}
