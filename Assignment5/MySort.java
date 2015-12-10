/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
class MySort
{
    
    ArrayList<SearchResult> sortThisList(ArrayList<SearchResult> c)
    {
        ArrayList<SearchResult> x=c;
        for(int i=0;i<x.size();i++)
        {
            for(int j=i+1;j<x.size();j++)
            {
                if(x.get(i).relevance<x.get(j).relevance)
                {
                    Collections.swap(x, i, j);
                }
            }
        }
        return x;
    }
}
