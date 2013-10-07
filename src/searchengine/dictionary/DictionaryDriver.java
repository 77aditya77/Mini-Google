package searchengine.dictionary;

/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 04-10-2009
 */ 



import java.io.*;



public class DictionaryDriver 
{
	String type  = "";
	DictionaryDriver(String str)
	{
		type = str;
	}


	public void test() throws Exception
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		//	bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students
		// minpq - Structure for priority min queue (not dictionary)
		// maxpq - Structure for priority max queue (not dictionary)

		DictionaryInterface<String, String> di = null;
		PQueueADT<Integer> pq=null; 

		if(type.equals("list"))
			di = new ListDictionary<String, String>();
		else if(type.equals("hash"))
			di = new HashDictionary<String, String>();
		else if(type.equals("myhash"))
			di = new MyHashDictionary<String, String>();
		else if(type.equals("bst"))
			di = new BSTDictionary<String, String>();
		else if(type.equals("avl"))
			di = new AVLDictionary<String, String>();

		int choice =0;

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String key=null;
			do
			{
				System.out.println("\nSelect one");
				System.out.println("1) Insert <key,val> pair");
				System.out.println("2) Remove <key,val> pair");
				System.out.println("3) Display all");
				System.out.println("4) Quit");
				choice = Integer.parseInt(input.readLine());

				switch(choice)
				{
				case 1:
					System.out.println("Enter the <key>: ");
					key = input.readLine();
					System.out.println("Enter the <value>: ");
					String value = input.readLine();
					System.out.println("Entered Values:"+key+"\t"+value);
					di.insert(key,value);
					break;
				case 2:
					System.out.println("Enter the <key>: ");
					key = input.readLine();
					System.out.println("Entered Values:"+key);
					di.remove(key);
					break;
				case 3:
					System.out.println("Displaying all the contents");

					String[] keys = di.getKeys();
					int i=0;
					if(keys!=null)
					{
						while(i<keys.length)
						{
							System.out.print(i+1);
							System.out.println(") " +keys[i] +", "+di.getValue(keys[i]));
							i++;
						}
					}
					else
					{
						System.out.println("Empty dictionary");
					}
					break;
				case 4:
					break;
				}
			}
			while(!(choice==4));
			System.out.println("Testing over");
		}

	public static void main(String args[]) throws Exception
	{
		if(args.length<1)
			System.out.println("Usage java DictionaryDriver <type of Dictionary>");
		else if(args[0].equals("list") || args[0].equals("hash") || args[0].equals("myhash") || args[0].equals("bst") || args[0].equals("avl") || args[0].equals("minpq") || args[0].equals("maxpq"))
		{
			DictionaryDriver dd = new DictionaryDriver(args[0]);
			dd.test();
		}
		else
		{
			System.out.println("Invalid type entered");
		}
	}
}
