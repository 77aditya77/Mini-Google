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

import java.net.*;

import searchengine.url.URLFixer;

/** A hyperlink in a web page.
*
*/
public class PageHref implements PageElementInterface {

    public PageHref (String h) throws MalformedURLException {
	href = new URL(h);
    }

    public PageHref (URL context, String h) throws MalformedURLException {
	href = URLFixer.fix(context, h);
    }

    public String toString () {
		if(href == null )
			return null;
	return href.toString();
    }

    private URL href;
}
