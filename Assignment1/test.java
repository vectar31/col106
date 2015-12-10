/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment1;
public class test 
{

    public boolean isStackSortablePermutation(myqueue q) 
    {
        mystack s=new mystack();//creating new stack
        myqueue Q=new myqueue();//creating new queue
        boolean answer=true;
        int n=q.queuesize();
        if(n==0)
            return answer;
       // try {
            while(q.isEmpty()!=true)
        {
            int el=q.dequeue();
            while(s.isEmpty()!=true && s.getElementAtTopOfStack()<el)
                Q.enqueue(s.pop());
            s.push(el);
        }
        while(s.isEmpty()!=true)
        {
            int el=s.pop();
            Q.enqueue(el);
        }
        int el=Q.dequeue();
        while(Q.isEmpty()!=true)
        {
            int temp=Q.dequeue();
            if(el>temp)
            {
                answer=false;
                break;
            }
            el=temp;
        }
        
       //} //catch (Exception e) {
            //System.out.println("Error : " + e.getMessage());
        //}
        
            
       return answer; 
    }

}
