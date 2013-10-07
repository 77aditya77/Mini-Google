package searchengine.dictionary;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class HashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>, Serializable{

	
	public HashDictionary()
	{
		h=new Hashtable<K,V>();
	}
	
	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		Enumeration<K> key=h.keys();
		if(h.size()!=0)
		{
			String[] names = new String[h.size()];

		    for (int i = 0; key.hasMoreElements(); i++) {
		        names[i] = (String)key.nextElement();
		    }
			return (K[])names;
		}
		else
		{
			return null;
		}
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		return h.get(str);
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		h.put(key, value);
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		V v=h.remove(key);
		if(v==null)
		{
			System.out.println("The given key isn't valid!");
		}
		else
		{
			System.out.println("The given key with the value '"+v+"' is deleted.");
		}
	}
	Hashtable<K,V> h;
}
