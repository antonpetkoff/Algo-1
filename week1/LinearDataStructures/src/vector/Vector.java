package vector;

public class Vector<T> implements VectorInterface<T> {

    private T[] array;
    private int size; // index to the first empty element

    static final float GROWTH_RATE = 2.0f;
    static final int INITIAL_CAPACITY = 8;

    @SuppressWarnings("unchecked")
    Vector() {
        array = (T[]) new Object[0];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    Vector(int initCapacity, T value) {
        array = (T[]) new Object[initCapacity];

        for (int i = 0; i < initCapacity; ++i) {
            array[i] = value;
        }
    }

    @Override
    public void insert(int index, T value) {
        if (size == array.length) {
            resize((int) (capacity() == 0 ? INITIAL_CAPACITY : GROWTH_RATE * array.length));
        }

        array[size] = value;
        leftSwaps(array, index, size);
        ++size;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resize((int) (capacity() == 0 ? INITIAL_CAPACITY : GROWTH_RATE * array.length));
        }

        array[size++] = value;
    }

    @Override
    public T get(int index) {
        boundsCheck(index, size);
        return array[index];
    }

    @Override
    public void remove(int index) {
        boundsCheck(index, size);
        
        rightSwaps(array, index, array.length - 1);
        array[--size] = null;
    }

    @Override
    public T pop() {
        if (size != 0) {
            T result = array[--size];
            array[size] = null;
            return result;
        }

        throw new IllegalStateException("No elements to pop!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    private void copyElements(T[] source, int from, int to, T[] destination) {
        for (int i = from; i < to; i++) {
            destination[i] = source[i];
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        copyElements(array, 0, array.length, newArray);
        array = newArray;
    }

    /**
     * takes an interval [begin , end] and takes the element at position end
     * moves it to position begin with adjacent swaps.
     * 
     * @param array
     * @param begin
     * @param end
     */
    static <T> void leftSwaps(T[] array, int begin, int end) {
        T temp = array[end];
        while (begin != end) {
            array[end] = array[end - 1];
            --end;
        }
        array[end] = temp;
    }
    
    static <T> void rightSwaps(T[] array, int begin, int end) {
        T temp = array[begin];
        while (begin != end) {
            array[begin] = array[begin + 1];
            ++begin;
        }
        array[begin] = temp;
    }
    
    private static void boundsCheck(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Out of bounds!");
        }
    }

}
