/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.util.*;
//import java.util.ListIterator;

public class Myset<X> {
    LinkedList a;
    public Myset() {
        a=new LinkedList(); 
    }
    public boolean IsEmpty()
    {
        return a.isEmpty();
    }
    public void addElement(Object n)
    {
        a.add(n);
    }
    public void removeElement(Object n)
    {
        a.remove(n);
    }
    public boolean isMember(Object n)
    {
        return a.contains(n);
    }
    public Myset<X> intersection(Myset b)
    {
       Myset ans = new Myset();
       for(Object p : a)
        {
            if(b.isMember(p))
                ans.addElement(p);
        }
        return ans;
    }
    public Myset<X> union(Myset b)
    {
        
        Myset ans=new  Myset();
        for(Object p : b.a)
        {
            ans.addElement(p);
        }
        for(Object p : a)
        {
            if(!b.isMember(p))
                ans.addElement(p);
        }
        return ans;
    }
    public void print()
    {
        System.out.println(a);
    }
    public static void main(String args[])throws Exception
    {
        
    
    }
}
