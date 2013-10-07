/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * 
 */

package searchengine.url;

import java.io.*;
import java.net.*;

/**
 * Read text from the Internet, given a URL.
 *
 * Reading is performed by a java.io.BufferedReader so as to
 * provide efficient reading of characters, arrays, and lines.
 *
 */
public class URLTextReader extends Reader {

	/** Create a buffering URL text reader, given a URL.
	@param u The URL to read.
	 */
	public URLTextReader (URL u) {
		try {
			URLConnection c = u.openConnection();
			String t = c.getContentType();
			if (t == null) return;

			InputStream in = c.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			//System.out.println(t);
			// If we know the type is not text, then close the connection.
			if (!t.startsWith("text/html"))  {
				reader.close();
				System.out.println("Type is not text");
				reader = null;
			}
		} catch (IOException e) {

			reader = null;
		}
	}

	/** Read characters into a portion of an array.
	@param cbuf The array in which to put the read characters.
	@param off The starting offset into the array.
	@param len The number of characters to read.
	@return The number of characters actually read.
	 */
	public int read (char[] cbuf, int off, int len){
		try
		{
			if (reader != null)
				return reader.read(cbuf, off, len);
		}
		catch(IOException e)
		{
			System.out.println("Cannot read");
		}
		return -1;
	}

	/** Read a line of text.
	@return The line of text that was read.
	 */
	public String readLine () throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return null;
	}

	/** Close the stream. */
	public void close () throws IOException {
		reader.close();
	}

	private BufferedReader reader;
}
