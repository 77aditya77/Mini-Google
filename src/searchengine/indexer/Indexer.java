/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */ 

package searchengine.indexer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import searchengine.dictionary.*;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;


/**
 * Web-indexing objects.  This class implements the Indexer interface
 * using a list-based index structure.

A Hash Map based implementation of Indexing 

 */
public class Indexer implements IndexerInterface
{
	/** The constructor for ListWebIndex.
	 */

	// Index Structure 
	DictionaryInterface<String,String> index;
	ArrayList<String> a;
	// This is for calculating the term frequency
	HashMap<String,Integer> wordFrequency;

	public Indexer(String mode)
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students

		if (mode.equals("hash")) 
			index = new HashDictionary();
		else if(mode.equals("list"))
			index = new ListDictionary();
		else if(mode.equals("myhash"))
			index = new MyHashDictionary();
		else if(mode.equals("bst"))
			index = new BSTDictionary();
		else if(mode.equals("avl"))
			index = new AVLDictionary();
		a=new ArrayList<String>();
		wordFrequency = new HashMap<String,Integer>();
	}

	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */
	public void addPage(URL url, ObjectIterator<?> keywords)	
	{
	    ////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		System.out.println("Words in this page:\n");
		int i=0;
		String[] keys=new String[keywords.size()];
		while(keywords.hasNext())
		{
			keys[i]=keywords.next().toString();
			i++;
		}
		for(int j=0;j<i;j++)
		{
			if(wordFrequency.containsKey(keys[j]))
			{
				int n=wordFrequency.get(keys[j]);
				n++;
				wordFrequency.put(keys[j],n);
			}
			else
			{
				wordFrequency.put(keys[j],1);
			}
		}
		for(int j=0;j<i;j++)
		{
			String value=url.toString()+" "+wordFrequency.get(keys[j]).toString();
			if(!a.contains(keys[j]))
			{
				System.out.println(keys[j]);
				index.insert(keys[j], value);
			}
			a.add(keys[j]);
		}
		wordFrequency.clear();
		a.clear();
	}
	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString()
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		return "You dont need to implement it\n";
	}

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?> retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    ///////////////////////////////////////////////////////////////////
		return null;
	}

	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    ///////////////////////////////////////////////////////////////////
		try
		{
			Vector v= new Vector();
			int j=0;
			PagesSet[] s=null;
			while(keywords.hasNext())
			{
				String te=keywords.next().toString();
				String val=index.getValue(te).toString();
				String[] arr=val.split("`");
				for(int i=0;i<arr.length;i++)
				{
						String arr1[]=arr[i].split(" ");
						float rank=0;
						for(int g=0;g<arr1[0].length();g++)
						{
							if(arr1[0].charAt(g)=='/')
							{
								rank++;
							}
						}
						rank=1/rank;
						rank=100*rank*Float.parseFloat(arr1[1]);
						String req=te+" "+arr1[0]+" "+rank;
						v.add(req);
						s[j]=new PagesSet();
						s[j].add(req);
				}
				j++;
			}
			if(j!=1)
			{
				
			}
			ObjectIterator<PageElementInterface> oi=new ObjectIterator<PageElementInterface>(v);
			return oi;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}

	/** Save the index to a file.
	 *
	 * @param stream The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		ObjectOutputStream in=new ObjectOutputStream(stream);
		in.writeObject(index);
		in.close();
		
	}

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		ObjectInputStream ois=new ObjectInputStream(stream);
		try {
			index=(DictionaryInterface<String,String>) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}
};
