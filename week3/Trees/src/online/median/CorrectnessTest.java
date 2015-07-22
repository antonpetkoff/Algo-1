package online.median;

import java.util.Random;

public class CorrectnessTest {

    private static Median incorrect;
    private static SortedSequence correct;
    
    private static final int TESTS = 1000;
    private static final int ITERATIONS = 2000;
    private static final int BOUND = 300000;
    
    private static boolean foundFlag = false;
    
    public static String testCase() {
        incorrect = new Median();
        correct = new SortedSequence();
        Random rand = new Random();
        StringBuilder msg = new StringBuilder();
        StringBuilder correctMedians = new StringBuilder();
        
        int[] numbers = new int[ITERATIONS];
        int incorrectMedian = 0;
        for (int i = 0; i < ITERATIONS; ++i) {
            numbers[i] = rand.nextInt(BOUND);
            incorrectMedian = incorrect.insert(numbers[i]);
            correct.add(numbers[i]);
            correctMedians.append(Integer.toString((int) correct.getMedian()) + " ");
            if (incorrectMedian != correct.getMedian()) {
                foundFlag = true;
                msg.append(i + " iteration missmatch, number " + numbers[i]);
                msg.append(" the corect is " + correct.getMedian() + " rather than " + incorrectMedian + '\n');
            }
        }
        
        msg.append("Numbers used: ");
        for (int i = 0; i < ITERATIONS; ++i) {
            msg.append(numbers[i] + " ");
        }
        
        return msg.toString() + "\n" + correctMedians.toString();
    }
    
    public static void main(String[] args) {
        String msg = null;
        for (int i = 0; i < TESTS; ++i) {
            msg = testCase();
            if (foundFlag) {
                System.out.println(msg);
                foundFlag = false;
            }
        }
    }
    
}
