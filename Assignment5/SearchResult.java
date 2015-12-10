/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class SearchResult  {
    pageEntry page;
    double relevance;
    public SearchResult(pageEntry p , double r)
    {
        page=p;
        relevance=r;
    }
    public pageEntry getPageEntry()
    {
        return page;
    }
    public double getRelevance()
    {
        return relevance;
    }
}
