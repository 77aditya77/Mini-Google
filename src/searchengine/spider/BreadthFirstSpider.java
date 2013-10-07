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

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageImg;
import searchengine.element.PageNum;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 

	public BreadthFirstSpider (URL u, Indexer i) {
		this.u = u;
		this.i = i;
	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	public Indexer crawl (int limit) 
	{
		////////////////////////////////////////////////////////////////////
	    //  Code as part of Breadth First Spider assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		URL temp=u;
		int count=0;

		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> dq=new ArrayList<String>();
		Vector<String> elements=new Vector<String>();
		ObjectIterator<String> o;
		dq.add(temp.toString());
		while(count<limit && temp!=null)
		{
				try 
				{
					URLTextReader TR = new URLTextReader(temp);
					PageLexer<PageElementInterface> elts = new PageLexer<PageElementInterface>(TR, temp);
					while (elts.hasNext()) 
					{
							PageElementInterface elt = (PageElementInterface)elts.next();
							if (elt instanceof PageHref)
							{
								if(!list.contains(elt.toString()) && !dq.contains(elt.toString()) && (elt.toString().contains("file:") || elt.toString().contains("http://") || elt.toString().contains("https://")))
								{
										list.add(elt.toString());
								}
							}
							else
							{
								if(elt instanceof PageWord)
								{
									elements.add(elt.toString());
								}
							}
					}
					o=new ObjectIterator<String>(elements);
					i.addPage(temp, o);elements.clear();
					if(list.size()!=0)
					{
							String mod=list.remove(0);
							dq.add(mod);
							temp=new URL(mod);
							count++;
							if(count<limit)
							{
								System.out.println("\nCrawling: "+temp.toString()+"\n");
							}
					}
					else
					{
						temp=null;
					}
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("\n\nCrawling done.\n");
		return i;
	}

	/** Crawl the web, up to the default number of web pages.
	 */
	public Indexer  crawl() {
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString()+"\n");
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
