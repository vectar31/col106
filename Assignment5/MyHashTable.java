/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;

import java.util.*;
public class MyHashTable {
    ArrayList<LinkedList <WordEntry> > ht;

    public MyHashTable() {
        ht=new ArrayList<>();
        for(int i=0;i<10007;i++)
        {
            LinkedList<WordEntry> a=new LinkedList<>();
            ht.add(a);
        }
    }
    private int getHashIndex(String s)
    {
        int hash=11;
        for(int i=0;i<s.length();i++)
            hash= ((hash*37)%10007 + s.charAt(i)%10007)%10007;
        return hash;
    }
    public void addPositionsForWord(WordEntry w)
    {
        String s=w.word;
        int hashindex=getHashIndex(s);
        ht.get(hashindex).add(w);
    }
    public LinkedList getvaluesforkey(String s)
    {
        int hashindex=getHashIndex(s);
        LinkedList a=ht.get(hashindex);
        return a;
    }
    
}
