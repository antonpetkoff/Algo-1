package jumping.soldiers;

import java.util.Scanner;

/**
 * Trivial solution with inversions counting using insertion sort
 */
public class JumpingSoldiers {

	public static int insertionSort(int[] arr) {
		int inversionsCount = 0, hand = 0, j = 0;
		
		for (int i = 1; i < arr.length; ++i) {
			hand = arr[i];
			j = i;
			while (j > 0 && arr[j - 1] > hand) {
				arr[j] = arr[j - 1];
				--j;
				++inversionsCount;
			}
			arr[j] = hand;
		}
		
		return inversionsCount;
	}
	
	public static int jumpingSoldiers(int[][] arrs) {
		int max = 0, maxIndex = 1, inversions = 0;
		for (int i = 0; i < arrs.length; ++i) {
			inversions = insertionSort(arrs[i]);
			if (inversions > max) {
				max = inversions;
				maxIndex = i + 1;
			}
		}
		return maxIndex;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int arrSize = scanner.nextInt(), arrCount = scanner.nextInt();
		int[][] arrs = new int[arrCount][arrSize];
		
		for (int i = 0; i < arrCount; i++) {
			for (int j = 0; j < arrSize; j++) {
				arrs[i][j] = scanner.nextInt();
			}
		}
		
		System.out.println(jumpingSoldiers(arrs));
		
		scanner.close();
	}
	
}
