package pouring.glasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PouringGlasses {

    /**
     * (first, second, third) glasses
     */
    public static class Triplet implements Cloneable {
        public static final int SIZE = 3;
        protected int[] components;
        
        public Triplet(int... args) {
            if (args.length != 3) {
                throw new IllegalArgumentException("Expected " + SIZE + " args!");
            }
            
            components = new int[SIZE];
            
            for (int i = 0; i < SIZE; ++i) {
                components[i] = args[i];
            }
        }
        
        public int get(int i) {
            return components[i];
        }
        
        public void set(int i, int value) {
            components[i] = value;
        }
                
        @Override
        public String toString() {
            return "[" + components[0] + "," + components[1] + "," + components[2] + "]";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Triplet) {
                Triplet other = (Triplet) obj;
                for (int i = 0; i < SIZE; ++i) {
                    if (components[i] != other.get(i)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
     
        public Triplet clone() {
            return new Triplet(components);
        }
    }
        
    protected static Triplet capacity;
    protected static Triplet[][][] visited;

    public static boolean isGoalReached(Triplet state, int goal) {
        return goal == state.get(0) || goal == state.get(1) || goal == state.get(2);
    }
    
    public static Triplet pour(Triplet state, int from, int to) {
        if (state.get(from) == 0 || state.get(to) == capacity.get(to)) {
            return state;
        }
        
        int water = Math.min(state.get(from), capacity.get(to) - state.get(to));
        Triplet newState = state.clone();
        newState.set(from, state.get(from) - water);
        newState.set(to, state.get(to) + water);
        
        return newState;
    }
    
    public static List<Triplet> generateStates(Triplet state) {
        List<Triplet> states = new ArrayList<Triplet>();
        
        for (int i = 0; i < Triplet.SIZE; ++i) {
            for (int j = 0; j < Triplet.SIZE; ++j) {
                if (i != j) {
                    states.add(pour(state, i, j));
                }
            }
        }
        
        return states;
    }
    
    public static Triplet findGoal(Triplet initialState, int goal) {
        LinkedList<Triplet> queue = new LinkedList<Triplet>();
        Triplet tempState = null;
        List<Triplet> childStates = null;

        queue.addLast(initialState);
        while (!queue.isEmpty()) {
            tempState = queue.removeFirst();
            if (isGoalReached(tempState, goal)) {
                System.out.println("Goal reached with state: " + tempState);
                break;
            }
            
            childStates = generateStates(tempState);
            for (Triplet state : childStates) {
                if (visited[state.get(0)][state.get(1)][state.get(2)] == null) {
                    queue.addLast(state);
                    visited[state.get(0)][state.get(1)][state.get(2)] = tempState;
                }
            }
        }
        
        return tempState;
    }
    
    public static List<String> backtrackPath(Triplet state) {
        List<String> path = new ArrayList<String>();
        Triplet parent = visited[state.get(0)][state.get(1)][state.get(2)];
        int from = 0, to = 0;
        
        while (parent != null) {
            
            for (int i = 0; i < Triplet.SIZE; ++i) {
                if (state.get(i) - parent.get(i) < 0) {
                    from = i + 1;
                } else if (state.get(i) - parent.get(i) > 0) {
                    to = i + 1;
                }
            }
            
            path.add(new String(from + " " + to));
            
            state = parent;
            parent = visited[state.get(0)][state.get(1)][state.get(2)];
        }
        
        Collections.reverse(path);
        return path;
    }
    
    public static List<String> optimalPour(Triplet cap, Triplet initialState, int goal) {
        capacity = cap;
        visited = new Triplet[capacity.get(0) + 1][capacity.get(1) + 1][capacity.get(2) + 1];
        
        Triplet tempState = findGoal(initialState, goal);
        List<String> path = backtrackPath(tempState);

        return path;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Triplet capacity = new Triplet(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        Triplet volume = new Triplet(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        int goal = scanner.nextInt();
        
        List<String> path = optimalPour(capacity, volume, goal);
        
        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i));
        }
        
        scanner.close();
    }
    
}
