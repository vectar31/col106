/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment2;
import java.util.*;
/**
 *
 * @author chodu baklund
 */
public class RoutingMapTree {
    Exchange root;
    LinkedList<Exchange> exchangespresent;
    public RoutingMapTree()
    {
        root = new Exchange(0);
        exchangespresent=new LinkedList();
        exchangespresent.add(root);
    }
    public boolean isEmpty()
    {
        return root==null;
    }
    
    public boolean containsNode(Exchange a , Exchange temp)
    {
        if(temp.val==a.val)
            return true;
        for(Exchange p : temp.childlist)
        {
            if(p.val==a.val)
                return true;
            return containsNode(a, p);
        }
        return false;
    }
    public void add_mobile_to_all_parents(Exchange temp , MobilePhone n)
    {
        if(temp.val!=0)
        {
            //System.out.println(temp.val);
            temp.par.M.addMobilePhone(n);
            add_mobile_to_all_parents(temp.par, n);  
        }
    }
    
    public void switchOff(MobilePhone a)
    {
        a.phonestatus=false;
        Exchange temp=a.phoneExchange;
        temp.M.removeMobilePhone(a);
        remove_mobile_from_all_children(temp, a); 
        root.M.removeMobilePhone(a);
    }
    public  void switchOffMobile(int n) throws ErrorException
    {
        boolean check = false;
        for(Object p: root.M.m.a)
        {
            MobilePhone temp=(MobilePhone)p;
            if( temp.phonenumber == n && temp.phonestatus==true)
            {
                check=true;
                break;
            }
        }
        if(check){
        for ( Object p : root.M.m.a)
        {
            MobilePhone pp=(MobilePhone)p;
            if(pp.phonenumber==n)
            {
                switchOff(pp);
                return;
            }
        }
        }
        else
            throw new ErrorException("No mobile phone of such Identifier was found or it is already switched off");
    }
    public void remove_mobile_from_all_parents(Exchange temp , MobilePhone n)
    {
        if(temp==null)
        {
            return;
        }
        else
        {
            temp.par.M.removeMobilePhone(n);
            remove_mobile_from_all_parents(temp.par, n);  
        }
    }
    /*public Exchange get_node_with_identifier(int a , Exchange temp)
    {
        if(temp.val==a)
            return temp;
        for(Exchange p:temp.childlist)
            get_node_with_identifier(a,p);
        return null;
    }*/
    public void addExchange(int a  , int b , Exchange temp2)
    {
        if(temp2.val==a)
        {
            Exchange temp=new Exchange(b);
            exchangespresent.add(temp);
            if(temp2!=null)
            {
                temp.par=temp2;
                temp2.childlist.add(temp);
            }
        }
        for(Exchange p : temp2.childlist)
        {
            addExchange(a, b, p);
        }
    }
    public void addExchange(int a , int b) throws ErrorException
    {
        //you have to add exceptions
        boolean check=false;
        for(Exchange p:exchangespresent)
        {
            if(p.val==a)
            {
                check = true;
                break;
            }
        }
        if(check==true)
            addExchange(a,b,root);
        else
            throw new ErrorException("The Exchange where you want to add to was not found");
    }
    
    public void remove_mobile_from_all_children(Exchange temp , MobilePhone n)
    {
        if(temp==null)
        {
            return;
        }
        else
        {
            temp.par.M.removeMobilePhone(n);
            
        }
    }
    public void SwitchOnMobile(int a, int b , Exchange temp) throws ErrorException
    {
        if(temp.val==b)
        {
            
            MobilePhone n=new MobilePhone(a);
            
            temp.M.addMobilePhone(n);
            
            n.phoneExchange=temp;
            n.phonestatus=true;
            
            add_mobile_to_all_parents(temp,n);
            
        }
        for ( Exchange p : temp.childlist)
        {
            SwitchOnMobile(a, b, p);
        }
    }
    public void SwitchOnMobile(int a, int b) throws ErrorException
    {
        // assuming that the mobile phone with identifier doesnt exist 
        // add exception if b doesn't exist
        /*MobilePhone n=new MobilePhone(a);
        n.phonestatus=true;
        Exchange temp=get_node_with_identifier(b, root);
        temp.M.addMobilePhone(n);*/
        boolean check = true,check2=false;
        for(Object p: root.M.m.a)
        {
            MobilePhone temp=(MobilePhone)p;
            if( temp.phonenumber == a )
            {
                check=false;
                break;
            }
        }
        for(Exchange p:exchangespresent)
        {
            if(p.val==b)
            {
                check2 = true;
                break;
            }
        }
        if(check==true&& check2==true)
            SwitchOnMobile(a, b , root);
        else if(check==false)
            throw new ErrorException("Mobile Phone is already switched on");
        else
            throw new ErrorException("Exchange not founnd");
    }
    public void queryNthChild(int a , int b , Exchange temp)
    {
        if(temp.val==a)
        {
            int res=temp.childlist.get(b).val;
            System.out.println(res);
            return ;
        }
        for(Exchange p:temp.childlist)
            queryNthChild(a, b, p);
    }
    public void queryNthChild(int a , int b) throws ErrorException
    {
        boolean check=false,check2=false;
        for(Exchange p:exchangespresent)
        {
            if(p.val==a)
            {
                check = true;
                if(p.childlist.size()>b)
                    check2=true;
                
                break;
            }
        }
        if(check ){
        System.out.print("Id of child number "+ b + " of Exchange with id " + a + " is ");
        queryNthChild(a, b, root);
        }
        else if(check==false)
            throw  new ErrorException("Exchange does not exist");
        else
            throw  new ErrorException("Nth child does not exist");
            
        
    }
    public void print_phones(Exchange temp)
    {
        for (Object p: temp.M.m.a)
        {
            MobilePhone pp=(MobilePhone)p;
            System.out.print(pp.phonenumber+" ");
        }
        System.out.println();
    }
    public void queryMobilePhoneSet(int a,Exchange temp)
    {
        if(temp.val==a)
        {
            print_phones(temp);
        }
        for(Exchange p:temp.childlist)
        {
            queryMobilePhoneSet(a,p);
        }
            
    }
    public  void queryMobilePhoneSet(int a) throws ErrorException
    {
        boolean check=false;
        for(Exchange p:exchangespresent)
        {
            if(p.val==a)
            {
                check = true;
                break;
            }
        }
        if(check){
        System.out.print("MobilePhoneSet of Exchange with id "+a+" is : ");
        queryMobilePhoneSet(a,root);}
        else
            throw  new ErrorException("Exchange does not exist");
    }
    public void performAction(String s)throws ErrorException 
    {
        try{
        //System.out.println(s+" ");
        String c[]=s.split(" ");
        String command=c[0];
        //if(command.equals("switchOnMobile"))
        switch(command)
        {
            case "addExchange":
                int a,b;
                a=Integer.valueOf(c[1]);
                b=Integer.valueOf(c[2]);
                addExchange(a,b);
                break;
            case "switchOnMobile":
                int a1,b1;
                //String c1[]=s.split(" ")
                
                a1=Integer.valueOf(c[1]);
                b1=Integer.valueOf(c[2]);
                
                SwitchOnMobile(a1, b1);
                break;
            case "switchOffMobile":
                int a2;
                //String c2[]=s.split(" ");
                a2=Integer.valueOf(c[1]);
                switchOffMobile(a2);
                break;
            case "queryNthChild":
                int a3,b3;
                a3=Integer.valueOf(c[1]);
                b3=Integer.valueOf(c[2]);
                queryNthChild(a3, b3);
                
                break;
            case "queryMobilePhoneSet":
                int a4;
                //String c4[]=s.split(" ");
                a4=Integer.valueOf(c[1]);
                queryMobilePhoneSet(a4);
                break;       
        }
        }
        catch(ErrorException e1)
        {
            System.out.println("Error : "+e1.getMessage());
        }
        
    }
    
}
