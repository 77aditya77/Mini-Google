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

/** A img src in a web page.
 *
 */
public class PageImg implements PageElementInterface {

	public PageImg (String h) /* perhaps throw an invalid filename error? */{
		img = h;
	}

	public String toString () {
		return img;
	}

	private String img;
}
