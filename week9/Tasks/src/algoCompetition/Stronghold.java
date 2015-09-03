package algoCompetition;

import java.util.Scanner;

public class Stronghold {
    
    public static int[] walls;
    
    public static void build(int index, int height) {
        walls[index - 1] = height;
    }
    
    public static void shoot(int shotHeight) {
        for (int i = 0; i < walls.length; ++i) {
            if (walls[i] > shotHeight) {
                walls[i] = 0;
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println("MISS");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        walls = new int[N];
        
        for (int i = 0; i < walls.length; i++) {
            walls[i] = scanner.nextInt();
        }
        
        int M = scanner.nextInt();
        scanner.nextLine();
        String command = null;
        String[] splitted = null;
        for (int i = 0; i < M; i++) {
            command = scanner.nextLine();
            splitted = command.split("\\s+");
            if (splitted[0].equals("shoot")) {
                shoot(Integer.parseInt(splitted[1]));
            } else if (splitted[0].equals("build")) {
                build(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
            }
        }
        
        scanner.close();
    }
    
}
