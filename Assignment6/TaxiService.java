//package assignment6;
import java.util.*;

public class TaxiService{
    public HashMap<String, LinkedList<Pair>> G;
    public LinkedList<String> cities;
    LinkedList<Taxi> taxis;
    public TaxiService() {
        G=new HashMap<>();;
        taxis=new LinkedList<>();
        cities=new LinkedList<>();
    }
    public void checkPriorityQueue()
    {
        Comparator<Pair> comparator = new compareCosts();
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(10, comparator);
         queue.add(new Pair("short",12));
        queue.add(new Pair("very long indeed" , 522));
        queue.add(new Pair("medium",43));
        while (queue.size() != 0)
        {
            System.out.println(queue.remove().second );
        }
    }
    public void addEdge(Edge e) throws ErrorException
    {
        
        if(e.cost<0)
        {
            throw new ErrorException("Time taken cannot be negative");
        }
        if(G.containsKey(e.src))
        {
            G.get(e.src).add(new Pair(e.dest,e.cost));
        }
        else
        {
            LinkedList<Pair> lp=new LinkedList<>();
            lp.add(new Pair(e.dest, e.cost));
            G.put(e.src,lp);
            cities.add(e.src);
        }
    }
    public void printValues(HashMap<String, Integer> d,HashMap<String, Boolean> visited,HashMap<String, String> predecessor)
    {
        Set s1=d.entrySet();
        Set s2=visited.entrySet();
        Set s3=predecessor.entrySet();
        Iterator i1 = s1.iterator();
        Iterator i2 = s2.iterator();
        Iterator i3 = s3.iterator();
        while (i1.hasNext()) {
            HashMap.Entry temp=(HashMap.Entry)i1.next();
            System.out.println(" --- > " + temp.getKey()+" Value " + temp.getValue() );
        }
        while (i2.hasNext()) {
            HashMap.Entry temp=(HashMap.Entry)i2.next();
            System.out.println(" --- > " + temp.getKey()+" Value " + temp.getValue() );
        }
        while (i3.hasNext()) {
            HashMap.Entry temp=(HashMap.Entry)i3.next();
            System.out.println(" --- > " + temp.getKey()+" Value " + temp.getValue() );
        }
    }
    //@SuppressWarnings("empty-statement")
    public int Dijkstra(String str , String strdest)
    {
        Comparator<Pair> comparator = new compareCosts();
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(100, comparator);
        int n=G.size(),inf=9999999;
        HashMap<String, Integer> d=new HashMap<>();
        HashMap<String, Boolean> visited=new HashMap<>();
        HashMap<String, String> predecessor=new HashMap<>();
        for(String c:cities)
        {
            d.put(c, inf);
            visited.put(c, Boolean.FALSE);
        }
      //Comparator<int> comp=new compareCost();
        d.put(str, 0);
        queue.add(new Pair(str, 0));
        //System.out.println(queue.size());
        //System.out.println("check"); 
        //    printValues(d, visited, predecessor);
            
        while(!queue.isEmpty())
        {
            String u=queue.remove().first;
            
            LinkedList<Pair> Gu=G.get(u);
            
            if(visited.get(u)==Boolean.TRUE) {
                
                continue;
            } 
            else {
                int sz=Gu.size();
                
                for(int i=0;i<sz;i++)
                {
                    String v=Gu.get(i).first;
                    
                    int w=Gu.get(i).second;
                    //System.out.println("check "+d.get(u).intValue());
                    if(visited.get(v)==Boolean.FALSE && (d.get(u).intValue()+w<d.get(v).intValue()) )
                    {
                        
                        d.put(v, w+d.get(u).intValue());
                        predecessor.put(v, u);
                        //System.out.println("check : "+ sz);
                        queue.add(new Pair(v,d.get(v).intValue()));
                    }
                }
                
                visited.put(u, Boolean.TRUE);
            }
        }
        /*Set s=d.entrySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            HashMap.Entry temp=(HashMap.Entry)i.next();
            System.out.println(" --- > " + temp.getKey()+" Value " + temp.getValue() );
            
            
        }*/
        String temp=strdest,path="";
        while(temp!=str)
        {
            path= predecessor.get(temp)+ " "+path;
            temp=predecessor.get(temp);
        }
        //path= str + " --> " + path;
        path = path + strdest;
        System.out.print(path);
        return d.get(strdest);
        
        
    }
    public void printGraph()
    {
        Set s=G.entrySet();
        Iterator i = s.iterator();
        while(i.hasNext())
        {
            HashMap.Entry temp=(HashMap.Entry)i.next();
            LinkedList <Pair> l = (LinkedList<Pair>)temp.getValue();
            System.out.print(temp.getKey());
            for(Pair p : l)
            {
                System.out.print(" ==> "+p.first+"("+p.second+")");
            }
            System.out.println();
            
        }
    }
    public void printTaxiPosition(int time) throws ErrorException
    {
        /*System.out.println("All Taxis:");
        for(Taxi tx : taxis)
        {
            System.out.println("Taxi "+tx.name+" : "+tx.taxiPosition+" : "+tx.taxitime);
        }*/
                
        if(time<0)
        {
            throw new ErrorException("Time cannot be negative");
        }
        System.out.println("Taxipositions at time "+time);
        for(Taxi tx : taxis)
                {
                    if(tx.taxitime<=time)
                    {
                         System.out.println("Taxi "+tx.name+" : "+tx.taxiPosition );
                         tx.taxitime=time;
                    }
                }
    }
    public void addTaxi(String s, String t)
    {
        Taxi temp=new Taxi(s, t);
        taxis.add(temp);
    }
    public  void customerservice(String source , String destination , int time) throws ErrorException
    {
        if(time<0)
        {
            throw new ErrorException("Time cannot be negative");
        }
        if(cities.contains(source)==false ||cities.contains(destination)==false)
        {
            throw new ErrorException("No such city is present");
        }
        int mintime=Integer.MAX_VALUE;String taxirec="";Taxi temp=null;
        System.out.println("Available Taxis:");
        for(Taxi tx : taxis)
                {
                    if(tx.taxitime<=time)
                    {
                        System.out.print("Path of taxi "+tx.name+": ");
                        if(tx.taxiPosition.equals(source))
                        {
                            temp=tx;
                            System.out.print(tx.taxiPosition);
                            System.out.println(". time taken is "+0+" units");
                            taxirec=tx.name;
                            mintime=0;
                        }
                        else{
                        int temptime=Dijkstra(tx.taxiPosition, source);
                        if(temptime<mintime)
                        {
                            mintime=temptime;
                            taxirec=tx.name;
                            temp=tx;
                        }
                        System.out.println(". time taken is "+temptime+" units");
                    }
                    }
                }
        System.out.println("** Chose taxi "+ taxirec +" to service the customer request ***");
        
        System.out.print("Path of customer: ");
        int temptime=Dijkstra(source, destination);
        temp.taxitime=time+temptime+mintime;
        temp.taxiPosition=destination;
        System.out.println(". time taken is " +temptime+"units");
    }
    public void performAction(String actionMessage)throws ErrorException{
        try {
            String command []=actionMessage.split(" ");
        switch(command[0])
        {
            
            case "edge":
                Edge e=new Edge(command[1], command[2], Integer.parseInt(command[3]));
                Edge eu=new Edge(command[2], command[1], Integer.parseInt(command[3]));
                this.addEdge(e);
                this.addEdge(eu);
                //System.out.println("Action performed : An edge between "+command[1]+" and "+command[2]+" has been added");
                
                break;
            case "printGraph":
                this.printGraph();
                //this.checkPriorityQueue();
                break;
            case "taxi":
                this.addTaxi(command[1], command[2]);
                //System.out.println("Action performed : A taxi "+command[1]+" has been added");
                break;
            case "shortestpathsfrom":
                //System.out.println("check " + actionMessage);
                for(Taxi tx : taxis)
                {
                    if(tx.available)
                    {
                        System.out.print("Path of taxi "+tx.name+": ");
                        int time=Dijkstra(tx.taxiPosition, command[1]);
                        System.out.println(". time taken is "+time+" units");
                    }
                }
                //Dijkstra(command[1],command[2]);
                break;
            case "printTaxiPosition":
                printTaxiPosition(Integer.parseInt(command[1]));
                break;
            case "customer":
                customerservice(command[1],command[2],Integer.parseInt(command[3]));
                break;
                
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}