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
public class MobilePhoneSet {
    Myset m;
    public MobilePhoneSet()
    {
        m=new Myset();
    }
    public void addMobilePhone(MobilePhone n)
    {
        
        //Object N=(Object)n;
        m.Insert(n);
    }
    public void removeMobilePhone(MobilePhone n)
    {
        //Object N=(Object)n;
        m.Delete(n);
    }
    public boolean containsMobilePhone(MobilePhone n)
    {
        //Object N=(Object)n;
        return m.isMember(n);
    }
    public void print()
    {
        for(Object o : m.a)
        {
            MobilePhone temp=(MobilePhone)o;
            System.out.println(temp.phonenumber);
        }
    }
}
