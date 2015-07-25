package birthday.ranges;

import java.util.Scanner;

public class BirthdayRanges {

    private BinaryIndexedTree bit;
    
    public BirthdayRanges(int[] values) {
        bit = new BinaryIndexedTree(365, values);
    }
    
    // adds people who are born on a specific day
    public void add(int day, int numberOfPeople) {
        bit.update(day, numberOfPeople);
    }

    // removes people who are born on a specific day
    public void remove(int day, int numberOfPeople) {
        bit.update(day, -numberOfPeople);
    }

    // returns the number of people born in a range
    public int count(int startDay, int endDay) {
        int a = bit.query(startDay), b = bit.query(endDay);
        return a != 0 ? b - a + 1 : b - a;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        int daysCount = scanner.nextInt(), commandsCount = scanner.nextInt();
        int[] values = new int[daysCount];
        
        for (int i = 0; i < daysCount; i++) {
            values[i] = scanner.nextInt();
        }
        
        BirthdayRanges br = new BirthdayRanges(values);
        
        String command = null;
        for (int i = 0; i < commandsCount; ++i) {
            command = scanner.next();
            if (command.equals("count")) {
                System.out.println(br.count(scanner.nextInt(), scanner.nextInt()));
            } else if (command.equals("add")) {
                br.add(scanner.nextInt(), scanner.nextInt());
            } else if (command.equals("remove")) {
                br.remove(scanner.nextInt(), scanner.nextInt());
            }
        }
        
        scanner.close();
    }

}
