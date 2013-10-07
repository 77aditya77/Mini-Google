/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */

package searchengine.indexer;



import java.io.*;
import java.net.URL;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageWord;




/** A suggested interface for a class of web-indexing objects.
 *
 */
public interface IndexerInterface
{
	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */
	public void addPage(URL url, ObjectIterator<?> keywords);

	/** Remove a given web page from the index
	 * 
	 * @return
	 */
	public void removePage(URL url);

	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString();

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?> retrievePages(PageWord keyword);

	/** Retrieve all of the web pages that contain all of the given keywords.
	 *
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords);

	/** Save the index to a file.
	 *
	 * @param stream The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException;

	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException;
};

