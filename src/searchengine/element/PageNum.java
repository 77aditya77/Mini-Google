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

/** A number in a web page.
 */
public class PageNum implements PageElementInterface {

	public PageNum (double n) {
		num = n;
	}

	public String toString () {
		return Double.toString(num);
	}

	private double num;
}
