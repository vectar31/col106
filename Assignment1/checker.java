/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assignment1;

public class checker
{
	public static void main ( String args [])
	{
		int inputsA[][] = {  {1}, {3,2,1}, {1,2,3}, {2,3,1} , {} , {5,1,2,4,3} , {1,2,10,5,4,3,9,8,7,6} , {4,1,5,3,2}};
		boolean oracle[] = {  true, true, true, false , true , true , true , false};

		for(int i=0; i<inputsA.length; i++) {
			int inputA[]=inputsA[i];
			boolean oracle_output = oracle[i];

			test t = new test();

			myqueue q = createQueueFromArray(inputA);

			boolean b = t.isStackSortablePermutation(q);
			if(b==oracle_output) {
				System.out.println("test passed for " + toString(inputA));
			} else {
				System.out.println("test failed for " + toString(inputA));
			}
		}
	}
	
	private static myqueue createQueueFromArray(int a[]) {
		myqueue q = new myqueue();

		for(int i=0; i<a.length; i++) {
			int element = a[i];
			q.enqueue(element);
		}

		return q;
	}

	private static String toString(int array[])
	{
		String str = "{";
		for(int i=0; i<array.length; i++) {
			int element = array[i];
			if(i==array.length-1) {
				str+= element;
			} else {
				str+=element + ",";
			}
		}
		str+="}";
		return str;
	}

	private static boolean areEqual(int a[], int b[])
	{
		if(a!=null && b!=null && a.length==b.length) {
			for(int i=0; i<a.length; i++) {
				int elementA = a[i];
				int elementB = b[i];

				if(elementA!=elementB) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
