/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */

package searchengine.spider;


import java.io.*;
import java.net.URL;

import searchengine.indexer.Indexer;


/** A user interface for web-crawling objects.
 * Usage : java CrawlerDriver url saveFile [list|custom] [limit]
 */
public class CrawlerDriver 
{
	/** A main entry point, to be used by web administrators, for
	creating web indexes.
	 */
	public static void main (String[] args) {
		try {
			URL u;
			FileOutputStream isaveFile;

			PriorityBasedSpider web = null;
			//BreadthFirstSpider web = null;
			
			if (args.length >= 2) {
				// Create a web crawler
				u = new URL(args[0]);
				String indexMode = args[2];
				indexMode = indexMode.toLowerCase();
				
				// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
				// list - Dictionary Structure based on Linked List 
				// myhash - Dictionary Structure based on a Hashtable implemented by the students
				// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
				// avl - Dictionary Structure based on AVL Tree implemented by the students

				if (indexMode.equals("list") || indexMode.equals("hash") || indexMode.equals("myhash")
						|| indexMode.equals("bst") || indexMode.equals("avl")) 
					web = new PriorityBasedSpider(u, new Indexer(indexMode));

				//web = new BreadthFirstSpider(u, new Indexer(indexMode));

				else {
					System.out.println("Invalid index mode - use either \"list\" or \"hash\"");
					System.exit(1);
				}

				// Open the index save file
				isaveFile = new FileOutputStream(args[1]);

			}
			else 
			{
				System.out.println("Usage: CrawlerDriver url index-saveFile [hash | list] [crawl limit]");
				return;
			}

			// Check for a page limit
			if (args.length > 3)
				web.crawlLimitDefault = Integer.parseInt(args[3]);

			// Crawl the web site
			Indexer index = web.crawl();

			// Save the index to the specified file
			index.save(isaveFile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Bad page limit.");
		}
	}
}

