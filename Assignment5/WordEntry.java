/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment5;
import  java.util.*;
//re
class AVLtreeNode
 {
     public Position data;
     public int height;
     public AVLtreeNode left, right;
     public AVLtreeNode()
     {
         data = null;
         height = 0;
         left = null;
         right = null;
     }
     public AVLtreeNode(Position n)
     {
         data = n;
         height = 0;
         left = null;
         right = null;
     }     
 }
class AVLTree
 {
     AVLtreeNode root;     
     public AVLTree()
     {
         root = null;
     }
     public boolean isEmpty()
     {
         return root == null;
     }
     private int max(int a, int b)
     {
         if(a>b)
             return a;
         else
             return b;
     }
     public void insert(Position data)
     {
         root = insert(data, root);
     }
     private int height(AVLtreeNode t )
     {
         if(t == null) 
             return -1;
         else
             return t.height;
     }
     
     private AVLtreeNode insert(Position x, AVLtreeNode t)
     {
         if (t == null)
             t = new AVLtreeNode(x);
         else if (x.wordposition < t.data.wordposition)
         {
             t.left = insert( x, t.left );
             if( height( t.left ) - height( t.right ) == 2 )
                 if( x.wordposition < t.left.data.wordposition )
                     t = rotateWithLeftChild( t );
                 else
                     t = doubleWithLeftChild( t );
         }
         else if( x.wordposition > t.data.wordposition )
         {
             t.right = insert( x, t.right );
             if( height( t.right ) - height( t.left ) == 2 )
                 if( x.wordposition > t.right.data.wordposition)
                     t = rotateWithRightChild( t );
                 else
                     t = doubleWithRightChild( t );
         }
         else
           ;  // Duplicate; do nothing
         t.height = max( height( t.left ), height( t.right ) ) + 1;
         return t;
     }
     private AVLtreeNode rotateWithLeftChild(AVLtreeNode k2)
     {
         AVLtreeNode k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
         k1.height = max( height( k1.left ), k2.height ) + 1;
         return k1;
     }
     private AVLtreeNode rotateWithRightChild(AVLtreeNode k1)
     {
         AVLtreeNode k2 = k1.right;
         k1.right = k2.left;
         k2.left = k1;
         k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
         k2.height = max( height( k2.right ), k1.height ) + 1;
         return k2;
     }
     private AVLtreeNode doubleWithLeftChild(AVLtreeNode k3)
     {
         k3.left = rotateWithRightChild( k3.left );
         return rotateWithLeftChild( k3 );
     }
     private AVLtreeNode doubleWithRightChild(AVLtreeNode k1)
     {
         k1.right = rotateWithLeftChild( k1.right );
         return rotateWithRightChild( k1 );
     }    
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(AVLtreeNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
     public boolean search(int val)
     {
         return search(root, val);
     }
     private boolean search(AVLtreeNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.data.wordposition;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
 }
public class WordEntry {
    LinkedList<Position> positionentries;
    AVLTree positionentriesastree;
    String word;
   double relevance;

    public WordEntry(String w) {
        word=w;
        positionentries=new LinkedList();
        relevance=0.0;
        positionentriesastree=new AVLTree();
    }
    public void addPosition(Position p)
    {
        positionentries.add(p);   
        positionentriesastree.insert(p);
    }
    public void addPositions(LinkedList<Position> a)
    {
        for(int i=0;i<a.size();i++)
        {
            positionentries.add(a.get(i));
            positionentriesastree.insert(a.get(i));
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
