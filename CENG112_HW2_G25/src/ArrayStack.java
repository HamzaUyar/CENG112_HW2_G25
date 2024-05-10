import java.util.Arrays;
public class ArrayStack<T> implements StackInterface<T>{
    
    private T[] stack;
    private int topIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public void checkInitialization(){
        if(!initialized){
            throw new SecurityException("ArrayStack object is not initialized properly.");
        }
    }
    
    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayStack(int initialCapacity){
        
        checkCapacity(initialCapacity);
        
        
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        initialized = true;
    }
    
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a stack " +
                    "whose capacity exceeds allowed maximum.");
        }
    }
    
    private void ensureCapacity(){
        if(topIndex >= stack.length - 1){
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
    
    public void push(T newEntry){
        checkInitialization();
        ensureCapacity();
        topIndex++;
        stack[topIndex] = newEntry;
    }
    
    public T pop(){
        checkInitialization();
        T top = null;
        if(!isEmpty()){
            top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
        }
        return top;
    }
    
    public T peek(){
        checkInitialization();
        T top = null;
        if(!isEmpty()){
            top = stack[topIndex];
        }
        return top;
    }
    
    public boolean isEmpty(){
        return topIndex < 0;
    }
    
    public void clear(){
        while(topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        }
    }

}
