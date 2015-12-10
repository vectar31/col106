/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment6;
import java.util.*;
public class Edge {
     public String src,dest;
     public int cost;
    public Edge(String a, String b, int c)
    {
        src=a;
        dest=b;
        cost=c;
    }
    public void printEdge()
    {
        System.out.println(src+"--"+cost+"-->"+dest);
    }
}