package searchengine.indexer;

/**
 *  SetADT.java       Author:  Mahender
 *  Defines the interface to a set collection using generics
 */
 
import java.util.Iterator;
 
public interface SetADT<T>
{
  /** Adds the element to this set, ignoring duplicates. */
  public void add(T element);  
 
  /** Removes and returns the specified element from this set. */
  public T remove(T element);
 
  /**  Returns the union of this set with the set passed */
  public SetADT<T> union(SetADT<T> set);
 
  /**  Returns the Intersection of this set with the set passed */
  public SetADT<T> intersection(SetADT<T> set);
 
  /**  Returns the Difference of this set with the set passed */
  public SetADT<T> difference(SetADT<T> set);
 
  /**  Returns true if this set contains the parameter */
  public boolean contains(T target);
 
  /**  Returns true if this set and the parameter contain exactly
       the same elements */
  public boolean equals(SetADT<T> set);
 
  /**  Returns true if this set contains no elements */
  public boolean isEmpty();
 
  /**  Returns the number of elements in this set */
  public int size();
 
  /**  Returns an iterator for the elements in this set */
  public Iterator<T> iterator();
 
  /**  Returns a string representation of this set */
  public String toString();
}
