/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package asiignment3test;

/**
 *
 * @author chodu baklund
 */
import java.util.*;
public class Exchange {
    LinkedList<Exchange> childlist;
    MobilePhoneSet M;
    Exchange par;
    int val;
    public Exchange(int Number)
    {
        val=Number;
        childlist=new LinkedList();
        M=new MobilePhoneSet();
        par=null;
    }
    public Exchange parent()
    {
        return par;
    }
    public boolean isRoot()
    {
        return par == null;
    }
    public Exchange child(int i)
    {
        Exchange ithchild=childlist.get(i);
        return ithchild;
    }
    public int numChildren()// The returned parameter is mentioned to be Exchange, confirm that it's wrong!
    {
        return childlist.size();
    }
    public void printChildList()
    {
        System.out.println("Child List of :" + val);
        for(int i=0;i<childlist.size();i++)
        {
            System.out.println(childlist.get(i).val);
        }
    }
    public RoutingMapTree subtree(int i)
    {
        RoutingMapTree T=new RoutingMapTree();
        Exchange temproot=this;
        T.root=this.childlist.get(i);
        return T;
    }
    
}
