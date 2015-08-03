package bank.robbery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BankRobbery {

    public static Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
    public static int[] minutes;
    
    public static int markMinutes(int police) {
        int maxMinutes = 0;
        boolean[] visited = new boolean[adjacencyList.size() + 1];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(police);
        visited[police] = true;
        
        int temp = 0;
        while (!queue.isEmpty()) {
            temp = queue.removeFirst();
            for (int neighbour : adjacencyList.get(temp)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    minutes[neighbour] = minutes[temp] + 1;
                    maxMinutes = Math.max(maxMinutes, minutes[neighbour]);
                    queue.addLast(neighbour);
                }
            }
        }
        
        return maxMinutes;
    }
    
    public static int binarySearch(int[] arr, int key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        
        while (hi - lo >= 0) {
            mid = lo + (hi - lo)/2;
            if (arr[mid] < key) {
                lo = mid + 1;
            } else {
                if (hi - lo == 0) {
                    return arr[mid] == key ? mid : -1;
                }
                hi = mid;
            }
        }
        
        return -1;
    }
    
    public static boolean caughtAfterWaiting(int wait, int bank, int helipad) {
        boolean[] visited = new boolean[minutes.length];
        LinkedList<Integer> currentLevel = new LinkedList<Integer>(),
                nextLevel = new LinkedList<Integer>();
        currentLevel.addLast(bank);
        visited[bank] = true;
        
        int temp = 0, runningTime = wait + 1;   // you need 1 for the neighbours of the current level
        while (!currentLevel.isEmpty()) {
            temp = currentLevel.removeFirst();
            for (int neighbour : adjacencyList.get(temp)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    if (runningTime >= minutes[neighbour]) {
                        continue;    // the robber was caught by the police here
                    }
                    if (neighbour == helipad) { // escaped in time
                        return false;
                    }
                    nextLevel.addLast(neighbour);
                }
            }
            if (currentLevel.isEmpty()) {
                LinkedList<Integer> tempQueue = currentLevel;
                currentLevel = nextLevel;
                nextLevel = tempQueue;
                ++runningTime;
            }
        }
        
        return true;   // the robber didn't find the helipad in time
    }
    
    public static int bankRobbery(int police, int bank, int helipad) {
        int maxMinutes = markMinutes(police);

        for (int i = 0; i < maxMinutes; ++i) {
            if (caughtAfterWaiting(i, bank, helipad)) {
                return i - 1;
            }
        }
        
        return -1;
    }
    
    public static void makeBidirecionalEdge(int v1, int v2) {
        if (adjacencyList.get(v1) == null) {
            adjacencyList.put(v1, new ArrayList<Integer>());
        }
        adjacencyList.get(v1).add(v2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int vertexCount = scanner.nextInt(), edgesCount = scanner.nextInt(), v1 = 0, v2 = 0;
        minutes = new int[vertexCount + 1];
        for (int i = 0; i < edgesCount; ++i) {
            v1 = scanner.nextInt();
            v2 = scanner.nextInt();
            makeBidirecionalEdge(v1, v2);
            makeBidirecionalEdge(v2, v1);
        }
        
        int bank = scanner.nextInt(), police = scanner.nextInt(), helipad = scanner.nextInt();

        System.out.println(bankRobbery(police, bank, helipad));
        
        scanner.close();
    }
    
}
