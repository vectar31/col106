//package assignment1;
import java.util.*;
public class mystack {

    // implement the stack using an array
	//int a[]=new int[100];
        
	int a[]=new int[1001];
        int topofstack=-1,lengthofstack=0;
        // declare additional variables here
        
	public void push(int element) {
		// ..... fill the stub function ......
            a[++topofstack]=element;
            lengthofstack++;
	}

	public int pop() {
		// ..... fill the stub function ......
            if( isEmpty() )
                throw new NoSuchElementException("Exception type: Stack Underflow");
            lengthofstack--;
            return a[topofstack--];
	
	}

	public boolean isEmpty() {
		// ..... fill the stub function ......
            return topofstack==-1 ;
	}

	public int getElementAtTopOfStack() {
		// ..... fill the stub function ......
            return a[topofstack];
	}
}
