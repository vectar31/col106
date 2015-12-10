/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;
import java.util.*;
public class InvertedPageIndex {
    MyHashTable hashtable;

    public InvertedPageIndex() {
        hashtable=new MyHashTable();
    }
    public void addPage (pageEntry p)
    {
        for(int i=0;i<p.pi.allwordentries.size();i++)
        {
            WordEntry w=p.pi.allwordentries.get(i);
            hashtable.addPositionsForWord(w);
        }
    }
    public LinkedList <pageEntry> getPagesWhichContainWord(String s)throws ErrorException
    {
        LinkedList<pageEntry> pagesWhichContainWord =new LinkedList();
        LinkedList<WordEntry> temp=hashtable.getvaluesforkey(s);
        for(int i=0;i<temp.size();i++)
        {
            WordEntry w=temp.get(i);
            LinkedList <Position> a=w.positionentries;
            for(int j=0;j<a.size();j++)
            {
                Position tempos=a.get(j);
                if(!pagesWhichContainWord.contains(tempos.page))
                    pagesWhichContainWord.add(tempos.page);
            }
            
        }
        if(pagesWhichContainWord.size()==0)throw new ErrorException("No page with the given word was found");
        return pagesWhichContainWord;   
    }
    public LinkedList<pageEntry> getPagesWhichContainPhrase(String str[])
    {
        // COmplete the function 
        LinkedList <pageEntry> res=new LinkedList();
        return res;
    }
    
    
    
}
