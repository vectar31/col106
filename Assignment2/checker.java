/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class checker
{
	public static void main ( String args []) throws Exception
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			                 
                                             String actionString;
			br = new BufferedReader(new FileReader("actions.txt"));
			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
                /*catch (ErrorException e1)
                {
                    System.out.println("Error Occured" + e1.getMessage());
                }*/
                finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
