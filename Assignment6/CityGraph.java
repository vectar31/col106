
import assignment6.Edge;
import assignment6.Pair;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class CityGraph {
     public HashMap<String, LinkedList<Pair>> G;
     @SuppressWarnings("empty-statement")
    public CityGraph()
    {
        G=new HashMap<>();;
    }
    /*public void addEdge(Edge e)
    {
        
            if(G.containsKey(e.src))
            {
                G.get(e.src).add(new Pair(e.dest,e.cost));
            }
            else
            {
                LinkedList<Pair> lp=new LinkedList<>();
                lp.add(new Pair(e.dest, e.cost));
                G.put(e.src,lp);                
            }
        
    }
    public void printGraph()
    {
        Set s=G.entrySet();
        Iterator i = s.iterator();
        while(i.hasNext())
        {
            HashMap.Entry temp=(HashMap.Entry)i.next();
            System.out.print(temp.getKey()+"=> ");
            
        }
    }*/
    
}
