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
public class MobilePhone {
    int phonenumber;
    boolean phonestatus;
    Exchange phoneExchange;
    public MobilePhone(int n)
    {
        phonenumber=n;
        phonestatus=false;
        phoneExchange=null;
    }
    public int number()
    {
        return phonenumber;
    }
    public boolean status()
    {
        return phonestatus;
    }
    public void switchon()
    {
        phonestatus=true;
    }
    public void switchoff()
    {
        phonestatus=false;
    }
    public Exchange location() throws ErrorException
    {
        /*if(temp.childlist.size()==0)
        {
            if (temp.M.containsMobilePhone(this))
                return temp;
        }
        for(Exchange p:temp.childlist)
        {
            return location(p);
        }
        return null;*/
        if(phonestatus==false)
            throw new ErrorException("The Mobile you are trying to find the location of is Off");
        else
        return phoneExchange;
    }
    
}
