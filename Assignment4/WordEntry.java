/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment4;
import  java.util.*;
//re
public class WordEntry {
    LinkedList<Position> positionentries;
    String word;

    public WordEntry(String w) {
        word=w;
        positionentries=new LinkedList();
    }
    public void addPosition(Position p)
    {
        positionentries.add(p);        
    }
    public void addPositions(LinkedList<Position> a)
    {
        for(int i=0;i<a.size();i++)
        {
            positionentries.add(a.get(i));
        }
    }
    public LinkedList<Position> getAllPositionsforthisWord()
    {
        return positionentries;
    }
    public void printPositionEntries()
    {
        for(int i=0;i<positionentries.size();i++)
        {
            Position temp=positionentries.get(i);
            System.out.print(" "+temp.wordposition);
        }
    }
    
    
}
