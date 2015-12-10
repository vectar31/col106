/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment1;
import java.util.*;
public class myqueue {
   
    int Q[]=new int[1001];
    int front=-1, rear=-1, queuelength=0 , maxsize=1001;
   
    public boolean isEmpty() {
		// ..... fill the stub function ......
        return front==-1;
    }
    public void enqueue(int element) 
    {
        if (rear == -1) 
        {
            front = 0;
            rear = 0;
            Q[rear] = element;
        }
        else if (rear + 1 >= maxsize)
            throw new IndexOutOfBoundsException("Exception type : Queue Overflow");
        else if ( rear + 1 < maxsize)
            Q[++rear] = element;    
        queuelength+=1;    
    }
    public int dequeue() 
    {
		// ..... fill the stub function ......
        if (isEmpty())
           throw new NoSuchElementException("Exception type : Queue Underflow");
        else 
        {
            int element = Q[front];
            queuelength-- ;
            if ( front == rear) 
            {
                front = -1;
                rear = -1;
            }
            else
                front++;   
            return element;
        }       
    }
    public int queuefront()
    {
        if (isEmpty())
           throw new NoSuchElementException("Exception type : Queue Underflow");
        return Q[front];
    }
    public int queuesize()
    {
        return queuelength;
    }
}
