/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.search;


import java.util.*;
import java.io.*;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;


/**
 * The user interface for the index structure.
 *
 * This class provides a main program that allows users to search a web
 * site for keywords.  It essentially uses the index structure generated
 * by WebIndex or ListWebIndex, depending on parameters, to do this.
 *
 * To run this, type the following:
 *
 *    % java SearchDriver indexfile list|custom keyword1 [keyword2] [keyword3] ...
 *
 * where indexfile is a file containing a saved index and list or custom indicates index structure.
 *
 */
public class SearchDriver
{
    public static void main(String [] args)
    {
        Vector<String> v=new Vector<String>();
	
	if(args.length<3)
	    System.out.println("Usage: java SearchDriver indexfile list|hash keyword1 [keyword2] [keyword3] [...]");
	else
	    {
		Indexer w = null;
		
		// Take care to use the right usage of the Index structure
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		if(args[1].equalsIgnoreCase("list") || args[1].equals("hash") || args[1].equals("myhash") || args[1].equals("bst") 
				|| args[1].equals("avl")){
		    w = new Indexer(args[1]);
		}
		else
		{
			System.out.println("Invalid Indexer mode \n");
		}
		
		try{
		    FileInputStream indexSource=new FileInputStream(args[0]);
		    w.restore(indexSource);
		}
		catch(IOException e){
		    System.out.println(e.toString());
		}
		
		for(int i=2;i<args.length;i++)
		    v.addElement(args[i]);
		
		ObjectIterator<?> i= w.retrievePages(new ObjectIterator<String>(v));

		if(i!=null)
		{
			////////////////////////////////////////////////////////////////////
		    //  Write your Code here as part of Sorting based on Rank Assignment
		    //  
		    ///////////////////////////////////////////////////////////////////
			int j=0;
			String[] results=new String[i.size()];
			float[] n=new float[i.size()];
			while(i.hasNext())
			{
				String k=i.next().toString();
				String k1[]=k.split(" ");
				n[j]=Float.parseFloat(k1[2]);
				results[j]=k1[1];
				j++;
			}
			for(int t1=0;t1<j-1;t1++)
			{
				for(int t2=t1+1;t2<j;t2++)
				{
					if(n[t1]<n[t2])
					{
						float temp=n[t1];
						n[t1]=n[t2];
						n[t2]=temp;
						String temp1=results[t1];
						results[t1]=results[t2];
						results[t2]=temp1;
					}
				}
			}
			ArrayList<String> rep=new ArrayList<String>();
			for(int t1=0;t1<j;t1++)
			{
				if(!rep.contains(results[t1]))
				{
					System.out.println(results[t1]+" - Rank-"+n[t1]);
					rep.add(results[t1]);
				}
			}
			System.out.println("Search complete.");
			System.out.println("---------------\n");
		}
		else
		{
			System.out.println("Search complete.  0  hits found.");
		}
	    }
    }
};


