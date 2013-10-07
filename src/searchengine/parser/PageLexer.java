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
import java.io.*;
import java.util.*;
import java.net.*;

import searchengine.element.PageHref;
import searchengine.element.PageImg;
import searchengine.element.PageNum;
import searchengine.element.PageWord;


/**
 * A lexical analyzer for web documents, based on a finite-state
 * machine.  This code is incomplete.  For Part 1 of HW4, you are
 * to finish writing the code for this class.
 *
 * This class implements a lexical analyzer for web documents.  Instances
 * of this class are Iterators that produce PageElement objects (each of
 * which is a keyword, number, image, or hyperlink).  Bad hyperlinks are discarded.
 *
 */
public class PageLexer<E> implements Iterator<E> {

	/**
	 * Creates a new web page lexer.
	 * 
	 * Note that this constructor method contains missing code.  For
	 * Part 1 of HW4, please fill in the missing code and then test
	 * by using the WebReader class. (See the writeup for more details.)
	 *
	 * @param page A reader for the web page
	 * @param u The URL of this page
	 */
	public PageLexer (Reader page, URL u) throws IOException {
		// The current vector of PageElements and the current state
		elts = new Vector<E>();
		int state = 0;
		url = u;

		// The tokenizer for the given web page
		tokenStream = new HttpTokenizer(new StreamTokenizer(page));

		int lexeme=0;
		action.doit(state);

		/** Code for spitting out the lexemes and making trasitions through the states
		 *  At the same time actions are called
		 *  A '0' is the end of tokens. 
		 */

		while((lexeme = tokenStream.nextToken())!=0)
		{
			state = delta[state][lexeme];
			action.doit(state);
		}
	}

	/** Determine whether there are more PageElements in the page.
	 *
	 * @return true of there are more PageElements, else false
	 */
	public boolean hasNext () {
		return !elts.isEmpty();
	}

	/** Return the next PageElement in the page.
	 *
	 * @return the next PageElement, or null if there are none.
	 */
	public E next () {
		return (E) elts.remove(0);
	}

	/** You do NOT need to implement this for HW4.  It is here
	only to satisfy the Iterator interface.
	 */
	public void remove () {
		throw new UnsupportedOperationException();
	}

	// The url of the page we are reading
	private URL url;

	// The PageElements that were found in this web page
	private Vector<E> elts; // of PageElements

	// The current tokenizer
	private HttpTokenizer tokenStream;

	/**
	 * The state-transition table.  A transition to -1 means halt.
	 * delta[state][token] yields the next state of the finite-state machine.
	 *
	 * Note that this is a very simple FSM.  It is possible that better
	 * web indexing could be done by modifying this FSM.  However, making
	 * such improvements is optional extra credit for this assignment.
	 */
	private int delta[][] = {

			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// Table is to be with filled for identifying IMG SRC(Image Source),
			// IMG ALT(Image alternate text) text for with quotes in it to be handled as words,
			// IFRAME source links(as hyperlinks) and
			// FRAME source links (as hyperlinks) 
			// Rest of the table as of now are filled with arbitrary state values
			// Quotes Text is not handeled properly
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			//EOF NUM WRD STR  <   >   =   /   -   !   A  HRF IMG SRC FRAME IFRAME ALT
			{-1,  3,  1,  1,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},  // state 0
			{-1,  3,  1,  1,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},  // state 1
			{-1,  5,  5,  5,  5,  6,  5,  5,  5,  5,  4,  5,  5,  5,  4,  4,  0},  // state 2
			{-1,  3,  1,  1,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},  // state 3
			{-1,  5,  5,  5,  5,  6,  5,  5,  5,  5,  5,  7,  5,  7,  5,  5,  0},  // state 4
			{-1,  5,  5,  5,  5,  6,  5,  5,  5,  5,  5,  5,  5,  10,  5,  5,  0},  // state 5
			{-1,  3,  1,  1,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},  // state 6
			{-1,  5,  5,  5,  5,  5,  8,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},  // state 7
			{-1,  5,  5,  9,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},  // state 8
			{-1,  5,  5,  5,  5,  6,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},   // state 9
			{-1,  5,  5,  5,  5,  6,  11,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},   // state 10
			{-1,  5,  5,  12,  5,  6,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},  // state 11
			{-1,  5,  5,  5,  5,  6,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0},   // state 12

	};

	/**
	 * The action table.
	 * action.doit(state) performs the action for the given state.
	 */
	private Action action = new Action();

	private class Action {
		@SuppressWarnings("unchecked")
		void doit (int state) {
			switch (state) {
			case 0: break;
			// In state 1 we have parsed a keyword, so add it.
			case 1: elts.add((E) new PageWord(tokenStream.sval)); break;
			case 2: break;
			// In state 3 we have parsed a number, so add it.
			case 3: elts.add((E) new PageNum(tokenStream.nval)); break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9:
				// In state 9 we have parsed a hyperlink, so check if it
				// is OK, and if it is, then add it.
				try {
					// See if it is a valid URL
					elts.add((E) new PageHref(tokenStream.sval));
				} catch (MalformedURLException e) {
					try {
						// Or if it is valid relative URL
						elts.add((E) new PageHref(url, tokenStream.sval));
						// If in valid, then just discard it
					} catch (MalformedURLException e1) { 
						//Seriously bad URL
						//System.out.println("Unusual URL: " + tokenStream.sval);
						//*** NOTE: You may want a better fixer of this happens too much
					}
				}
				break;
			case 10: break;
			case 11: break;
			case 12: elts.add((E) new PageImg(tokenStream.sval)); break;
			}			
		}
	}

}
