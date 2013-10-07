/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */ 

package searchengine.spider;

import searchengine.indexer.Indexer;

/** An interface for web-crawling objects.
 *
 */

public interface SpiderInterface {
	/** Crawl the web, up to a certain number of web pages.
	 *
	 *  @param limit The maximum number of pages to crawl.
	 * @return The web index resulting from this crawl (and any previous ones).
	 */
	public Indexer crawl (int limit);

	/** Crawl the web, up to the default number of web pages.
	@return The web index resulting from this crawl.
	 */
	public Indexer crawl ();

}
