package birthday.ranges;

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
        int values[] = {5, 10, 6, 7, 3, 4, 5, 11, 21, 300, 15};
        BirthdayRanges br = new BirthdayRanges(values);
        System.out.println(br.count(2, 10));
    }

}
