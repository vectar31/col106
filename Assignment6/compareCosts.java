/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment6;
import java.util.*;
public class compareCosts implements Comparator<Pair>{

    @Override
    public int compare(Pair t, Pair t1) {
        return t.second-t1.second;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    
}
