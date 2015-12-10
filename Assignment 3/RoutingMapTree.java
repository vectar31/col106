/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package asiignment3test;
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
            if(temp.childlist.size()!=0)
            {
                throw new ErrorException(temp.val+ " is not a base exchange");
            }
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
    public boolean check_Phone_Existence(int n) throws ErrorException 
    {
        boolean check=false;
        for (Object p: root.M.m.a)
        {
            MobilePhone temp=(MobilePhone)p;
            if(temp.phonenumber==n)
            {
                check=true;
                
            }
        }
        return check;
    }
    public void findPhone(int n) throws ErrorException 
    {
        boolean check=false;
        for (Object p: root.M.m.a)
        {
            MobilePhone temp=(MobilePhone)p;
            if(temp.phonenumber==n)
            {
                check=true;
                System.out.println("FindPhone "+n+": "+temp.phoneExchange.val);
                
            }
        }
        if(check!=true)
        System.out.println("queryFindPhone "+n+" : Error - No mobile phone with identifier "+ n +" found in the networkquery");
    }
    public Exchange lowestRouter(Exchange a , Exchange b)throws ErrorException
    {
        if(!(exchangespresent.contains(b)&&exchangespresent.contains(a)))
            throw new ErrorException("One or both the Exchanges are not in the tree");
        if(a.val==0)return a;
        if(a.val==b.val)return a;
        if(a.par.val==b.par.val)
        {
            return a.par;
        }
        if(a.childlist.contains(b))return a;
        return lowestRouter(a.par, b);
        //throw new ErrorException("One or both the Exchanges are not in the tree");
    }
    public Exchange lowestRouter(int a , int b)throws ErrorException
    {
        Exchange temp1 = null,temp2 = null;
        for(Exchange p : exchangespresent)
        {
           if(p.val==a)
               temp1=p;
           if(p.val==b)
               temp2=p;
        }
        if(temp1==null || temp2==null)
        {
            throw new ErrorException("One or Both of the Exchanges wasn't found");
        }
        return lowestRouter(temp1, temp2);
        
    }
    public void routeCall(MobilePhone a, MobilePhone b) throws ErrorException
    {
        Exchange start=a.location(),end=b.location();
        //System.out.println("check "+start.val+" "+end.val);
        Exchange lca=lowestRouter(start, end);
        //System.out.println("check "+lca.val);
        LinkedList <Exchange> res= new LinkedList();
        while(start!=lca)
        {
            res.add(start);
            start=start.par;
        }
        LinkedList <Exchange> tempres = new LinkedList();
        while(end!=lca)
        {
            tempres.add(end);
            end=end.par;
        }
        res.add(lca);
        for(int i=tempres.size()-1;i>=0;i--)
        {
            res.add(tempres.get(i));
        }
        for(int i=0;i<res.size();i++)
            System.out.print(res.get(i).val+" ");
        System.out.println();
    }
    public void findCallPath(int a , int b ) throws ErrorException
    {
        if(check_Phone_Existence(a)==false)
            throw new ErrorException("Error - Mobile phone with identifier "+ a+" is currently switched off");
        if(check_Phone_Existence(b)==false)
            throw new ErrorException("Error - Mobile phone with identifier "+ b+" is currently switched off");
        System.out.print("Shortest call path from " + a + " to  "+ b +" is : ");
        MobilePhone tempa=null,tempb=null;
        for(Object p : root.M.m.a)
        {
            MobilePhone temp=(MobilePhone)p;
            if(temp.phonenumber==a)
            {
                tempa=temp;
            }
            if(temp.phonenumber==b)
            {
                tempb=temp;
            }
        }
        //System.out.println("check "+tempa.phonenumber+" "+tempb.phonenumber);
        routeCall(tempa, tempb);
    }
    public void movePhone(int a, int b) throws ErrorException
    {
        switchOffMobile(a);
        SwitchOnMobile(a, b);
        
        
    }
    public void print_path_till_root(Exchange a)
    {
        if(a.par!=null)
        {
            System.out.print(a.val+" ");
            print_path_till_root(a.par);
        }
    }
    public void print_path_till_root(int a)
    {
        for(Object p:root.M.m.a)
        {
            MobilePhone pp=(MobilePhone)p;
            if(pp.phonenumber==a)
                print_path_till_root(pp.phoneExchange);
        }
    }
    public void print_exchangespresent()
    {
        for(Exchange p : exchangespresent)
            System.out.print(p.val+" ");
        System.out.println();
    }
    public void performAction(String s)throws ErrorException 
    {
        try {
        
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
            case "findPhone":
                int a5;
                a5=Integer.parseInt(c[1]);
                findPhone(a5);
                break;
            case "lowestRouter":
                int a6,b6;
                a6=Integer.parseInt(c[1]);
                b6=Integer.parseInt(c[2]);
                Exchange temp=lowestRouter(a6, b6);
                System.out.println("queryLowestRouter : " + temp.val);
                //print_exchangespresent();
                break;
            case "findCallPath":
                int a7,b7;
                a7=Integer.parseInt(c[1]);
                b7=Integer.parseInt(c[2]);
                findCallPath(a7, b7);
                break;
            case "movePhone":
                int a8,b8;
                a8=Integer.parseInt(c[1]);
                b8=Integer.parseInt(c[2]);
                movePhone(a8, b8);
                break;
                
                
                
        }
        } catch (ErrorException e1) {
            System.out.println(e1.getMessage());
        }
        
    }
    
}
