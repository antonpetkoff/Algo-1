package algoCompetition;

import java.util.Scanner;

public class PeanltyPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt(), M = scanner.nextInt();
        
        String[] map = new String[N];
        
        for (int i = 0; i < map.length; i++) {
            map[i] = scanner.nextLine();
        }
        
        scanner.close();
    }
    
}
