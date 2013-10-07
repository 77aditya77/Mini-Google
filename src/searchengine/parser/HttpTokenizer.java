/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 12-10-2009
 */ 


package searchengine.parser;

import java.util.*;
import java.io.*;

/** A simple tokenizer for web pages.
 *
 * Object of this class provide a low-level, stream-based parsing of
 * web pages.  Given a Reader object that reads a web page, an
 * HttpTokenizer will provide a stream of tokens, in exactly the same
 * style as the StreamTokenizer class in java.io.
 *
 * The possible tokens are as follows:
 *
 * HT_EOF: The end of file
 * HT_NUMBER: A number, converted to a double
 * HT_WORD: A word, converted to all lowercase
 * HT_STRING: A quoted string
 * HT_TAGOPEN: A "<" character
 * HT_TAGCLOSE: A ">" character
 * HT_EQUALS: A "=" character
 * HT_SLASH: A "/" character
 * HT_DASH: A "-" character
 * HT_BANG: A "!" character
 * HT_A: The keyword "a"
 * HT_HREF: The keyword "href"
 * HT_IMG: The keyword "img"
 *
 * When an HT_NUMBER is returned by the next() method, the instance
 * variable nval contains the double representation of the number.
 * When an HT_WORD or STRING is returned by the next() method, the instance
 * variable sval contains its string representation.
 *
 */

public class HttpTokenizer {

    /** A constant indicating the end of the web document has been reached. */
    public static final int HT_EOF = 0;
    /** A constant indicating a number token has been read. */
    public static final int HT_NUMBER = 1;
    /** A constant indicating a word token has been read. */
    public static final int HT_WORD = 2;
    /** A constant indicating a string token has been read. */
    public static final int HT_STRING = 3;
    /** A constant indicating a '<' has been read. */
    public static final int HT_TAGOPEN = 4;
    /** A constant indicating a '>' has been read. */
    public static final int HT_TAGCLOSE = 5;
    /** A constant indicating a '=' has been read. */
    public static final int HT_EQUALS = 6;
    /** A constant indicating a '/' has been read. */
    public static final int HT_SLASH = 7;
    /** A constant indicating a '-' has been read. */
    public static final int HT_DASH = 8;
    /** A constant indicating a '!' has been read. */
    public static final int HT_BANG = 9;
    /** A constant indicating an "a" has been read. */
    public static final int HT_A = 10;
    /** A constant indicating an "href" has been read. */
    public static final int HT_HREF = 11;
    /** A constant indicating an "img" has been read. */
    public static final int HT_IMG = 12;
    /** A constant indicating an "src" of an img has been read. */
    public static final int HT_SRC = 13;
    /** A constant indicating an "src" of a frame has been read. */
    public static final int HT_FRAME = 14;
    /** A constant indicating an "src" of an iframe has been read. */
    public static final int HT_IFRAME = 15;
    /** A constant indicating an "alt" of an image has been read. */
    public static final int HT_ALT = 16;


    ////////////////////////////////////////////////////////////////////
    //  Write your Code here as part of Webreader assignment
    //  Extend the Number of Tags Here
    ///////////////////////////////////////////////////////////////////
    
    /** If the current token is a word or string, this field gives the
	string. */
    public String sval;
    /** If the current token is a number, this field contains the value
	of that number. */
    public double nval;

    /** Create an HTTP tokenizer, given a StreamTokenizer for the web page. */
    
    public HttpTokenizer (StreamTokenizer tokens) throws IOException {
	// Create a stream tokenizer
	this.tokens = tokens;

	// Set up the appropriate defaults
	tokens.eolIsSignificant(false);
	tokens.lowerCaseMode(true);
	tokens.wordChars('<','<');
	tokens.wordChars('>','>');
	tokens.wordChars('/','/');
	tokens.wordChars('=','=');
	tokens.wordChars('@','@');
	tokens.wordChars('!','!');
	tokens.wordChars('-','-');
	tokens.ordinaryChar('.');
	tokens.ordinaryChar('?');
    }

    /** Parses the next token from the web page.
	@return The code of the next token.
     */
    public int nextToken() throws IOException {
	// First, check if the last word we read still has more tokens in it
	if (word != null && word.hasMoreTokens()) {
	    sval = word.nextToken();
	    if (sval.equals("<")) return HT_TAGOPEN;
	    if (sval.equals(">")) return HT_TAGCLOSE;
	    if (sval.equals("=")) return HT_EQUALS;
	    if (sval.equals("/")) return HT_SLASH;
	    if (sval.equals("-")) return HT_DASH;
	    if (sval.equals("!")) return HT_BANG;
	    if (sval.equals("a")) return HT_A;
	    if (sval.equals("href")) return HT_HREF;
	    if (sval.equals("img")) return HT_IMG;
	    if (sval.equals("src")) return HT_SRC;
	    if (sval.equals("frame")) return HT_FRAME;
	    if (sval.equals("iframe")) return HT_IFRAME;
	    if (sval.equals("alt")) return HT_ALT;
    
	    ////////////////////////////////////////////////////////////////////
	    //  Write your code here as part of WebReader assignment
	    //  Check for the new tags you have extended and return it
	    ///////////////////////////////////////////////////////////////////
	    
	    return HT_WORD;
	}

	// Otherwise, read in a new token from the stream
	word = null;
	int t = tokens.nextToken();

	if (t == StreamTokenizer.TT_WORD) {
	    word = new StringTokenizer(tokens.sval, "<>/=!-", true);
	    return this.nextToken();
	}
	if (t == StreamTokenizer.TT_NUMBER) {
	    nval = tokens.nval;
	    return HT_NUMBER;
	}
	if (t == 34) { // The magic number for string tokens
	    sval = tokens.sval;
	    return HT_STRING;
	}
	if (t == StreamTokenizer.TT_EOF)
	    return HT_EOF;

	// If nothing interesting found, then go for the next token
	return this.nextToken();
    }

    // The stream tokenizer
    private StreamTokenizer tokens;

    // Each word returned by the stream tokenizer must be further
    // parsed.  This is done by creating a string tokenizer for
    // each word.
    private StringTokenizer word;
    public String toString(){
    	return tokens.toString();
    }
}

