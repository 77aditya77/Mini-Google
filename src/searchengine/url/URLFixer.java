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

import java.util.*;
import java.net.*;

/**
 * Helper class that is used to resolve relative url's, given a parent context url
 */
public class URLFixer{
	/**
	 * Function takes the non-host part of the string and returns
	 * the directory structure only.
	 * the directory will get returned with a '/' at the end and at the beginning!
	 * the file will be returned as the plain file
	 * @return returns an array representing the pair: directory and file
	 */

	private static String[] getDirectoryAndFile(String urlString){
		String[] arr = new String[2];
		// if you end in a '/' then you are a directory!
		if ( urlString.endsWith("/")){
			arr[0] = urlString;
			arr[1] = "";
			return arr;
		}
		StringTokenizer toks = new StringTokenizer(urlString, "/");
		String directory = "";
		String file = "";
		while (toks.hasMoreTokens()){
			String str = toks.nextToken();
			if ( toks.hasMoreTokens()){
				directory = directory + "/" + str;
			}else{
				file = str;
			}
		}
		directory = directory + "/";
		arr[0] = directory;
		arr[1] = file;
		return arr;
	}

	/**
	 * Function takes the parent level URL and the linkCandidate
	 * and attempts to resolve any relative linkage.
	 * @param URL parent - the URL page on which the link is on
	 * @param String linkCandidate - the link we are trying to fix in String form
	 * @return URL - the valid url of the fixed link
	 */
	public static URL fix(URL parent, String linkCandidate) throws MalformedURLException {
		// if it is not relative, send it back
		try{
			URL toReturn = new URL(linkCandidate);
			return toReturn;
		} catch(MalformedURLException e ){
			// this means it need fixing
			// So we fall into remainder of function
		}

		String[] arr = getDirectoryAndFile(parent.getFile());
		//String directory = arr[0];
		String file = arr[1];

		// check to see if the last item is a file or simply a directory
		StringTokenizer toks = new StringTokenizer(file, ".");
		if ((!file.equals("")) && (toks.countTokens() < 2)){

			parent = new URL(parent.toString() + "/");
			arr = getDirectoryAndFile(parent.getFile());
			//directory = arr[0];
			file = arr[1];
		}

		// you are an anchor link on this page context, that means your
		// parent page is a fully spec URL
		//	System.out.println("before  if"+linkCandidate);

		if (!linkCandidate.equals(""))
		{
			if  (linkCandidate.charAt(0) == '#'){
				String finalString = parent.toString() + linkCandidate;
				return new URL(finalString); }

			// now we know it is relative
			// link is of "/mosaic/images/index.htm"
			// we want to take the host, the old directory, and the current one
			if (linkCandidate.charAt(0) == '/') {	    
				// like http://www.cmu.edu/ strip the final '/'
				String parentString = parent.toString();
				if ( parentString.endsWith("/")){
					parentString = parentString.substring(0, parentString.length()-1);
				}
				String finalString = parentString + linkCandidate;

				return new URL(finalString);
			}
			else if (linkCandidate.endsWith("/")){
				// you know it is a directory
				String finalString = parent.getProtocol()+"://" + parent.getHost() + arr[0]  + linkCandidate;
				return new URL(finalString);
			}
			else {
				// is of the form "index.html" or ../blah
				String finalString = "";
				// if you are not at a directory, you have to add an EXTRA /
				if (linkCandidate.startsWith("..") && arr[0].equals("/")){	
					finalString = parent.getProtocol()+"://" + parent.getHost() + arr[0] + "/" + linkCandidate;
				}	    
				else {
					finalString = parent.getProtocol()+"://" + parent.getHost() + arr[0] + linkCandidate;
				}
				return new URL(finalString);
			}
		}
		else
		{	
			String finalString=null;
			return new URL(finalString);
		}
	}
}

