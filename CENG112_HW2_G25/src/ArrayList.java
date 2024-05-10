import java.util.Arrays;
public class ArrayList<T> implements ListInterface<T>{
    private T[] list;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean initialized = false;
    private static final int MAX_CAPACITY = 10000;
    
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayList(int initialCapacity) {
        initialized = false;
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            checkCapacity(initialCapacity);
        }
        
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    }
    
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a list " +
                                            "whose capacity exceeds " +
                                            "allowed maximum.");
        }
    }
    
    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("ArrayList object is corrupt.");
        }
    }
    
    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    }
    
    public void add(T newEntry) {
        checkInitialization();
        ensureCapacity();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    }
    
    public void add(int newPosition, T newEntry) {
        checkInitialization();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            ensureCapacity();
            for (int idx = numberOfEntries; idx >= newPosition; idx--) {
                list[idx + 1] = list[idx];
            }
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity();
        } else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        }
    }
    
    public T remove(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T result = list[givenPosition];
            if (givenPosition < numberOfEntries) {
                for (int idx = givenPosition; idx < numberOfEntries; idx++) {
                    list[idx] = list[idx + 1];
                }
            }
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }
    
    // Implement the missing methods from ListInterface<T> here
    
    public int getLength() {
        return numberOfEntries;
    }
    
    public boolean contains(T anEntry) {
        for (int i = 1; i <= numberOfEntries; i++) {
            if (list[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }
    
    public void clear() {
        for (int i = 1; i <= numberOfEntries; i++) {
            list[i] = null;
        }
        numberOfEntries = 0;
    }
    
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 1; i <= numberOfEntries; i++) {
            result[i - 1] = list[i];
        }
        return result;
    }
    
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public T replace(int givenPosition, T newEntry) {
        checkInitialization();
        T originalEntry = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else {
            return null;

    }
    }
    
    public T getEntry(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
    }
}
