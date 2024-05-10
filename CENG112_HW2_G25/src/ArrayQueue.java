public final class ArrayQueue<T> implements QueueInterface<T>{
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    
    public ArrayQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }
    
    public ArrayQueue(int initialCapacity) {
        
        checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        initialized = true;
    }

    public void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("ArrayQueue object is not initialized properly.");
        }
    }
    
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a queue " +
                    "whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
        }
    }
    
    private void ensureCapacity() {
        if (frontIndex == ((backIndex + 2) % queue.length)) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize - 1);
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++) {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    }
    
    public void enqueue(T newEntry) {
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }
    
    public T dequeue() {
        checkInitialization();
        T front = null;
        if (!isEmpty()) {
            front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
        }
        return front;
    }
    
    public T getFront() {
        checkInitialization();
        T front = null;
        if (!isEmpty()) {
            front = queue[frontIndex];
        }
        return front;
    }
    
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length); 
    }
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

}
