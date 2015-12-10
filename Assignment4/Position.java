/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment4;

public class Position {
    pageEntry page;
    int wordposition;
    public Position(pageEntry p, int w)
    {
        page=p;
        wordposition=w;
    }
    public pageEntry getpageEntry()
    {
        return page;
    }
    public int getwordPosition()
    {
        return wordposition;
    }
    
    
    
}
