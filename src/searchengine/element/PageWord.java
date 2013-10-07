/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */

package searchengine.element;

/** A text word in a web page.
 */
public class PageWord implements PageElementInterface {

	public PageWord (String w) {
		word = w;
	}

	public String toString () {
		return word;
	}

	private String word;
}
