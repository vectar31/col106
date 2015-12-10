/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;
import java.util.*;

public class PageIndex {
    LinkedList<WordEntry> allwordentries;
    public PageIndex()
    {
        allwordentries=new LinkedList();
    }
    void addPositionForWord(String str, Position p)
    {
        boolean isPresent=false;
        for(int i=0;i<allwordentries.size();i++)
        {
            WordEntry we=allwordentries.get(i);
            if(we.word.equals(str))
            {
                we.addPosition(p);
                isPresent=true;
                break;
            }
        }
        if(!isPresent)
        {
            WordEntry w=new WordEntry(str);
            w.addPosition(p);
            allwordentries.add(w);
        }
    }
    LinkedList<WordEntry> getWordEntries()
    {
        return allwordentries;
    }
    public void calculaterelevance()
    {
        for(int i=0;i<allwordentries.size();i++)
        {
            WordEntry temp=allwordentries.get(i);
            double score=0.0;
            for(int j=0;j<temp.positionentries.size();j++)
            {
                Position p=temp.positionentries.get(j);
                score=score+(1.0/(p.wordposition*p.wordposition));
            }
            temp.relevance=score;
        }
    }
    
}
