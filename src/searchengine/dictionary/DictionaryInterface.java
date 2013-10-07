package searchengine.dictionary;

/**  
 * @DictionaryInterface.java
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender K on 03-10-2009
 */ 

public interface DictionaryInterface <K extends Comparable<K>, V>
{
	/** This class is an interface for the Dictionary Data Structures used in Mini Google project
	 * */

	/**
	 * This method inserts a key val pair into the Dictionary Data Structure
	 * @params K, V
	 * @return NULL
	 */
	public void insert(K key, V value);

	/**
	 * This method takes a key value as input and retrives the corresponding value of it
	 * @param str
	 * @return V
	 */
	public V getValue(K str);

	/**
	 * This method takes a key and removes a particular key,value pair from the Dictionary Structure
	 * @param obj
	 */
	public void remove(K key);

	/**
	 * This method returns all the keys as an array.
	 * @return
	 */
	public K[] getKeys();	

}
