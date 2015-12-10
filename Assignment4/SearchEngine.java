/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment4;
import java.io.IOException;
import java.util.*;
public class SearchEngine{
    LinkedList<pageEntry> pageEntries;
    LinkedList<String> addedpages;
    InvertedPageIndex ipi;
    public SearchEngine() {
            // ...
        ipi=new InvertedPageIndex();
        addedpages=new LinkedList();
        pageEntries=new LinkedList<>();
    }
    public void addPage(String s) throws IOException
    {
        
        pageEntry temp=new pageEntry(s);
        pageEntries.add(temp);
        ipi.addPage(temp);
        System.out.println("The page "+s+" was added to the Search Engine database");
    }
    public void queryFindPagesWhichContainWord(String s) throws ErrorException
    {
        System.out.print("Name of the pages which contain "+s+" are : ");
        LinkedList <pageEntry> a=ipi.getPagesWhichContainWord(s);
        for(int i=0;i<a.size();i++)
        {
            System.out.print(a.get(i).pageName+"  ");
        }
        System.out.println();
    }
    public void queryFindPositionsOfWordInAPage(String x, String y) throws ErrorException
    {
        boolean check=false;
        System.out.print("Positions of the word "+x+" in the page "+y+" are : ");
        //System.out.println(x+","+y);
        /*LinkedList <pageEntry> a=ipi.getPagesWhichContainWord(x);
        for(int i=0;i<a.size();i++)
        {
            if(a.get(i).pageName.equals(y))
            {
                
                LinkedList<WordEntry> temp=a.get(i).pi.allwordentries;
                for(int j=0;j<temp.size();j++)
                {
                    if(temp.get(j).word.equals(x))
                    {
                        temp.get(j).printPositionEntries();
                        break;
                    }
                }
                break;
            }
        }
        System.out.println();*/
        /*for(int i=0;i<pageEntries.size();i++)
        {
            
            
            
            if(pageEntries.get(i).pageName.equals(y)){
                PageIndex temp=pageEntries.get(i).getPageIndex();
                LinkedList<WordEntry> tempw=temp.getWordEntries();
                for(int j=0;j<tempw.size();j++)
                {
                    if(tempw.get(j).word.equals(x));
                    {   
                        check=true;
                        tempw.get(j).printPositionEntries();
                        System.out.println("");
                        break;
                    }
                }
                break;
            }
        }*/
        for(int i=0;i<pageEntries.size();i++)
        {
            if(pageEntries.get(i).pageName.equals(y))
            {
                PageIndex pp=pageEntries.get(i).getPageIndex();
                for(WordEntry ww: pp.allwordentries)
                {
                    if(ww.word.equals(x)){check=true;
                    for(int j=0;j<ww.positionentries.size();j++)
                    {
                        System.out.print(ww.positionentries.get(j).wordposition+" ");
                    }
                    System.out.println();}
                }
                
            }
        }
        if(check==false)throw new ErrorException("No position for the given word was found in the given webpage");
        
    }
    public void printAllPageEntries()
    {
        for(int i=0;i<pageEntries.size();i++)
        {
            PageIndex temp=pageEntries.get(i).getPageIndex();
            LinkedList<WordEntry> tempw=temp.getWordEntries();
            for(int j=0;j<tempw.size();j++)
            {
                System.out.print(tempw.get(j).word+" - > ");
                tempw.get(j).printPositionEntries();
                System.out.println("");
            }
        }
    }
    public void printPageIndex (String page)
    {
        for(int i=0;i<pageEntries.size();i++)
        {
            if(pageEntries.get(i).pageName.equals(page))
            {
                PageIndex pp=pageEntries.get(i).getPageIndex();
                for(WordEntry ww: pp.allwordentries)
                {
                    System.out.print(ww.word + " : ");
                    for(int j=0;j<ww.positionentries.size();j++)
                    {
                        System.out.print(ww.positionentries.get(j).wordposition+" ");
                    }
                    System.out.println();
                }
                
            }
        }
    }
    public void performAction(String actionMessage) throws IOException,ErrorException {
            //....
        try
        {
            String s[]=actionMessage.split(" ");
            String command=s[0];
            //s[1]=s[1].toLowerCase();
            switch(command)
            {
                case "addPage" : 
                //System.out.println(s[1]);
                addPage(s[1]);
                    break;
                case "queryFindPagesWhichContainWord":
                    s[1]=s[1].toLowerCase();
                    //System.out.println(s[0]+" >> "+s[1]);
                    queryFindPagesWhichContainWord(s[1]);
                    break;
                case "queryFindPositionsOfWordInAPage":
                    String x=s[1],y=s[2];//System.out.println(x);
                    queryFindPositionsOfWordInAPage(x, y);
                    break;
                case "printAllPageEntries":
                    printAllPageEntries();
                case "printPageIndex":
                    printPageIndex(s[1]);
                    
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
