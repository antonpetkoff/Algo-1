package utils;

import java.util.Random;

import sorting.Insertion;
import sorting.Merge;
import sorting.Quick;
import sorting.Selection;
import utilities.Utils;

public class SpeedTest {

    public static final int SORTERS_COUNT = 4;
    public static Random rand = new Random();
    
    public static void speedTest(int size) {
        Integer[][] numbers = new Integer[SORTERS_COUNT][size];
        
        for (int col = 0; col < size; ++col) {
            Integer temp = rand.nextInt();
            for (int row = 0; row < SORTERS_COUNT; ++row) {
                numbers[row][col] = temp;
            }
        }

        Long[] runningTimes = new Long[SORTERS_COUNT];
        long start = System.currentTimeMillis();
        Selection.sort(numbers[0]);
        runningTimes[0] = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        Insertion.sort(numbers[1]);
        runningTimes[1] = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        Merge.sort(numbers[2]);
        runningTimes[2] = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        Quick.sort(numbers[3]);
        runningTimes[3] = System.currentTimeMillis() - start;
       
        Utils.printArray(runningTimes);

    }
    
    public static void main(String[] args) {
        speedTest(100000);
    }
    
}
