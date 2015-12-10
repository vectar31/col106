/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;

import java.util.*;
import java.io.*;
public class pageEntry {
    String pageName;
    PageIndex pi;
    public pageEntry(String s) throws FileNotFoundException, IOException { 
        
        pageName=s;
        pi=new  PageIndex();
        String sentence;
        
        BufferedReader br = new BufferedReader(new FileReader(s));
        //System.out.println("check");
        int c=1;
        while ((sentence = br.readLine()) != null) 
        {
            sentence.replaceAll("\\s+", " ");
            //sentence=sentence.replaceAll("[^a-zA-Z\\s]", " ").replaceAll("\\s+", " ");
             sentence=sentence.replaceAll("[-{}\\[\\]<>=().,;'\"?#!:]"," ").replaceAll("\\s+", " ");
            //sentence=sentence.replaceAll("\\s+", " ");
            sentence=sentence.toLowerCase();
            //System.out.println(sentence);
            String strarray[]=sentence.split(" ");
            int i;
            for(i=0;i<strarray.length;i++)
            {
                String str=strarray[i];
                if(str.equals("stacks"))str="stack";
                if(str.equals("structures"))str="structure";
                if(str.equals("applications"))str="application";
                if(str.equals("a")||str.equals("an")||str.equals("the")||str.equals("they")||str.equals("these")||str.equals("this")||str.equals("for")||str.equals("is")||str.equals("are")||str.equals("was")||str.equals("of")||str.equals("or")||str.equals("and")||str.equals("does")||str.equals("will")||str.equals("whose"))
                    c++;
                else if(str.equals(""));
                else
                {
                //if(str.equals("longest"))System.out.println(str+" >> "+c);
                    Position temp=new Position(this, c++);
                    //System.out.println(str+" pos = "+(c-1));
                    pi.addPositionForWord(str, temp);
                }
                    
            }
        }
        pi.calculaterelevance();
        
    }
    public PageIndex getPageIndex()
    {
        return pi;
    }
    public double getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase)
    {
        double score=0;
        LinkedList<WordEntry> temp=pi.allwordentries;
        if(doTheseWordsRepresentAPhrase==false)
        {
            
            for(int i=0;i<str.length;i++)
            {
                for(int j=0;j<temp.size();j++)
                {
                    if(temp.get(j).word.equals(str[i]))
                    {
                        score=score+temp.get(j).relevance;
                    }
                }
            }
        }
        
        return score;
    }
    
}
