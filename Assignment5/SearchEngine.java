/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;
import java.io.IOException;
import java.util.*;
public class SearchEngine{
    LinkedList<String> connectors;
    LinkedList<pageEntry> pageEntries;
    LinkedList<String> addedpages;
    InvertedPageIndex ipi;
    public SearchEngine() {
            // ...
        
        ipi=new InvertedPageIndex();
        addedpages=new LinkedList();
        pageEntries=new LinkedList<>();
        connectors=new LinkedList();
        connectors.add("a");
        connectors.add("an");
        connectors.add("the");
        connectors.add("they");
        connectors.add("these");
        connectors.add("this");
        connectors.add("for");
        connectors.add("is");
        connectors.add("are");
        connectors.add("was");
        connectors.add("of");
        connectors.add("or");
        connectors.add("and");
        connectors.add("does");
        connectors.add("will");
        connectors.add("whose");
    }
    public String printArray(Object a[])
    {
        String s="''";
        for(int i=0;i<a.length;i++)
        {
            s=s+" "+a[i];
        }
        return s+" ''";
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
                    if(ww.word.equals(x)){
                        check=true;
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
    class comparing implements Comparator<SearchResult>
    {
        @Override
        public  int compare(SearchResult a, SearchResult b)
        {
            if(a.relevance<b.relevance)
                return 1;
            else
                return -1;
        }
    }
    public void queryFindPagesWhichContainAnyOfTheseWords(String s[]) throws ErrorException
    {
        ArrayList<SearchResult> ORquery=new ArrayList();
        for(int i=0;i<pageEntries.size();i++)
        {
            boolean f=false;
            pageEntry a=pageEntries.get(i);
            ArrayList<String> words=new ArrayList();
            for(int j=0;j<s.length;j++)
            {
                for(int k=0;k<a.pi.allwordentries.size();k++)
                {
                    WordEntry w=a.pi.allwordentries.get(k);
                    if(w.word.equals(s[j]))
                    {
                        f=true;
                        words.add(s[j]);
                        break;
                    }
                }
            }
            if(f)
            {
                String str[]=new String[words.size()];
                for(int l=0;l<words.size();l++)
                    str[l]=words.get(l);
                double rel=a.getRelevanceOfPage(str, false);
                SearchResult temp=new SearchResult(a, rel);
                ORquery.add(temp);
            }  
        }
        
        MySort dd=new MySort();
        ArrayList<SearchResult> res=dd.sortThisList(ORquery);
        System.out.println("The given ORquery for the words "+printArray(s)+" returns the following result : ");
        if(ORquery.size()==0)
            throw new ErrorException(">>>>>>No page was found");
        for(int i=0;i<res.size();i++)
        {
            System.out.println((i+1)+" : "+res.get(i).page.pageName+" with relevance value = "+res.get(i).relevance);
        }
        
    }
    public void queryFindPagesWhichContainAllWords(String s[]) throws ErrorException
    {
        
        ArrayList<SearchResult> ANDquery=new ArrayList();
        for(int i=0;i<pageEntries.size();i++)
        {
            
            boolean f=true;
            pageEntry a=pageEntries.get(i);
            
            ArrayList<String> words=new ArrayList();
            for(int j=0;j<s.length;j++)
            {
                
                boolean check=false;
                for(int k=0;k<a.pi.allwordentries.size();k++)
                {
                    WordEntry w=a.pi.allwordentries.get(k);
                    
                    if(w.word.equals(s[j]))
                    {
                        check=true;
                        words.add(s[j]);
                        break;
                    }
                }
                if(check==false)
                {
                    f=false;
                    break;
                }
            }
            if(f)
            {
                String str[]=new String[words.size()];
                for(int l=0;l<words.size();l++)
                    str[l]=words.get(l);
                double rel=a.getRelevanceOfPage(str, false);
                SearchResult temp=new SearchResult(a, rel);
                ANDquery.add(temp);
            }  
        }
        MySort dd=new MySort();
        ArrayList<SearchResult> res=dd.sortThisList(ANDquery);
        System.out.println("The given ANDquery for the words "+printArray(s)+" returns the following result : ");
        if(ANDquery.size()==0)
            throw new ErrorException(">>>>>>No page was found");
        
        for(int i=0;i<res.size();i++)
        {
            System.out.println((i+1)+" : "+res.get(i).page.pageName+" with relevance value = "+res.get(i).relevance);
        }
        
    }
    public int wordpresent(int k , String s , pageEntry p)//checks if s is present at k k+1 or k+2 with already considered leaving connector words.
    {
       LinkedList<WordEntry> a=p.pi.allwordentries;
       for(int i=0;i<a.size();i++)
       {
           AVLTree T=a.get(i).positionentriesastree;
           if(T.search(k))
           {
               if(a.get(i).word.equals(s))return k;
               if(!connectors.contains(a.get(i).word))return -1;
               break;
           }
       }
       for(int i=0;i<a.size();i++)
       {
           AVLTree T=a.get(i).positionentriesastree;
           if(T.search(k+1))
           {
               if(a.get(i).word.equals(s))return k+1;
               if(!connectors.contains(a.get(i).word))return -1;
               break;
           }
       }
       for(int i=0;i<a.size();i++)
       {
           AVLTree T=a.get(i).positionentriesastree;
           if(T.search(k+2))
           {
               if(a.get(i).word.equals(s))return k+2;
               
               break;
           }
       }
       return  -1;
    }
    public double phraserelevance(Vector v)
    {
        double res=0;
        for(int i=0;i<v.size();i++)
        {
            res=res+(1.0/(((int) v.get(i)*(int) v.get(i))));
        }
        return res;
    }
    public void queryFindPagesWhichContainPhrase(String phrase[]) throws ErrorException
    {
        ArrayList<SearchResult> PHRASEquery=new ArrayList();
        for(int i=0;i<pageEntries.size();i++)
        {
            Vector v=new Vector();
            
            boolean f=false;
            ArrayList<Position> phrasestartindices=new ArrayList<>();
            
            pageEntry a=pageEntries.get(i);
            
            ArrayList<String> words=new ArrayList();
            for(int k=0;k<a.pi.allwordentries.size();k++)
            {
                WordEntry w=a.pi.allwordentries.get(k);
                if(w.word.equals(phrase[0]))
                {
                    for(int j=0;j<w.positionentries.size();j++)
                    {
                        phrasestartindices.add(w.positionentries.get(j));
                    }
                    break;
                }
            }
            if(phrasestartindices.size()>0)
            {
                int c=0;
                while(c!=phrasestartindices.size())
                {
                    boolean check=true;
                    int d=phrasestartindices.get(c).wordposition;
                    int index=d;
                    //indices[0]=d;
                    //for(int l=0;l)
                    for(int j=1;j<phrase.length;j++)
                    {
                        String str=phrase[j];
                        int k=wordpresent(d+1, str, a);
                        if(k==-1)
                        {
                            check=false;
                            break;
                        }
                        else
                        {
                            //indices[j]=k;
                            d=k;
                        }
                    }
                    if(check)
                    {
                        f=true;
                        v.add(index);
                        //break;
                    }
                    c++;
                }
                if(f)
                {
                    //for(int m=0;m<phrase.length;m++)
                      //  System.out.println(phrase[m]);
                    //System.out.println(v);
                    double rel=phraserelevance(v);
                    SearchResult temp=new SearchResult(a, rel);
                    PHRASEquery.add(temp);
                        
                }
            }
                  
        }
        MySort dd=new MySort();
        ArrayList<SearchResult> res=dd.sortThisList(PHRASEquery);
        System.out.println("The given PHRASEquery for the words "+printArray(phrase)+" returns the following result : ");
       if(PHRASEquery.size()==0)
            throw new ErrorException(">>>>>>No page was found"); 
        for(int i=0;i<res.size();i++)
        {
            System.out.println((i+1)+" : "+res.get(i).page.pageName+" with relevance value = "+res.get(i).relevance);
        }
    }
    public void performAction(String actionMessage) throws IOException,ErrorException {
            //....
        try
        {
            String s[]=actionMessage.split(" ");
            String command=s[0];
            //s[1]=s[1].toLowerCase();
            String searchword[]=new String[s.length-1];
            for(int i=1;i<s.length;i++)
                searchword[i-1]=s[i].toLowerCase();
            switch(command)
            {
                case "addPage" : 
                //System.out.println(s[1]);
                addPage(s[1]);
                    break;
                case "queryFindPagesWhichContainWord":
                    s[1]=s[1].toLowerCase();
                    queryFindPagesWhichContainWord(s[1]);
                    break;
                case "queryFindPositionsOfWordInAPage":
                    String x=s[1],y=s[2];//System.out.println(x);
                    queryFindPositionsOfWordInAPage(x, y);
                    break;
                case "printAllPageEntries":
                    printAllPageEntries();
                    break;
                case "printPageIndex":
                    printPageIndex(s[1]);
                    break;
                case "queryFindPagesWhichContainAnyOfTheseWords":
                    
                    queryFindPagesWhichContainAnyOfTheseWords(searchword);
                    break;
                case "queryFindPagesWhichContainAllWords":
                    //for(int i=0;i<searchword.length;i++)
                        
                    queryFindPagesWhichContainAllWords(searchword);
                    
                    break;
                case "queryFindPagesWhichContainPhrase":
                    queryFindPagesWhichContainPhrase(searchword);
                    break;
                    
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
