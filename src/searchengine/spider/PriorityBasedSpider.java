/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.text.Document;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLTextReader;
/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in Priority-first order.
 */
public class PriorityBasedSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 

	public PriorityBasedSpider (URL u, Indexer i) {
		this.u = u;
		this.i = i;

	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	public Indexer crawl (int limit) {
	
		////////////////////////////////////////////////////////////////////
	    //  Code as part of Priority Based Spider assignment
		//
	    ///////////////////////////////////////////////////////////////////
		URL temp=u;
		int count=0,n,d1,d2,d3;
		ObjectIterator<String> o;
		ArrayList<String> PQ=new ArrayList<String>();
		ArrayList<String> dq=new ArrayList<String>();
		Vector<String> elements=new Vector<String>();
		PQ.add(null);
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
							if(!PQ.contains(elt.toString()) && !dq.contains(elt.toString()) && (elt.toString().contains("file:") || elt.toString().contains("http://") || elt.toString().contains("https://")))
							{
									PQ.add(elt.toString());
									if(PQ.size()!=2)
									{
										n=PQ.size()-1;
										while(n>1)
										{
											String x=PQ.get(n);
											String y=PQ.get(n/2);
											d1=0;d2=0;
											for(int l=0;l<x.length();l++){if(x.charAt(l)=='/'){d1++;}}
											for(int l=0;l<y.length();l++){if(y.charAt(l)=='/'){d2++;}}
											if(d1<d2)
											{
												PQ.remove(n);
												PQ.add(n,y);
												PQ.remove(n/2);
												PQ.add(n/2,x);
											}
											n=n/2;
										}
									}
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
				if(PQ.size()>1)
				{
						if(PQ.size()==2)
						{
							temp=new URL(PQ.remove(1));
							dq.add(temp.toString());
						}
						else
						{
							String x=PQ.get(1);
							String y=PQ.get(PQ.size()-1);
							temp=new URL(PQ.remove(1));
							dq.add(temp.toString());
							PQ.add(1,y);
							PQ.remove(PQ.size()-1);
							boolean factor=true;int n1=1;
							while(factor)
							{
								if(n1>=PQ.size())
								{
									break;
								}
								if(2*n1>=PQ.size())
								{
									x=PQ.get(n1);
								}
								else
								{
									x=PQ.get(2*n1);
								}
								if(((2*n1)+1)>=PQ.size())
								{
									y=PQ.get(n1);
								}
								else
								{
									y=PQ.get((2*n1)+1);
								}
								d1=0;d2=0;d3=0;
								for(int l=0;l<x.length();l++){if(x.charAt(l)=='/'){d1++;}}
								for(int l=0;l<y.length();l++){if(y.charAt(l)=='/'){d2++;}}
								for(int l=0;l<PQ.get(n1).length();l++){if(PQ.get(n1).charAt(l)=='/'){d3++;}}
								if(d1>d2)
								{
									if(d2<d3)
									{
										String t=PQ.get(n1);
										PQ.remove(n1);
										PQ.add(n1,y);
										PQ.remove((2*n1)+1);
										PQ.add((2*n1)+1,t);
										n1=(2*n1)+1;
									}
									else
									{
										factor=false;
									}
								}
								else
								{
									if(d1<d3)
									{
										String t=PQ.get(n1);
										PQ.remove(n1);
										PQ.add(n1,x);
										PQ.remove(2*n1);
										PQ.add(2*n1,t);
										n1=2*n1;
									}
									else
									{
										factor=false;
									}
								}
							}
						}
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
		System.out.println("\nCrawling done.\n");
		
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
	public Document doc;
}
