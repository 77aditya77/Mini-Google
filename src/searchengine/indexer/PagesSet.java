package searchengine.indexer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;

public class PagesSet implements SetADT<Object>{

	public PagesSet()
	{
		a=new Vector<Object>();
	}
	
	@Override
	public void add(Object element) {
		// TODO Auto-generated method stub
		a.add(element.toString());
	}

	@Override
	public Object remove(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetADT<Object> union(SetADT<Object> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetADT<Object> intersection(SetADT<Object> set) {
		// TODO Auto-generated method stub
		PagesSet p=new PagesSet();
		return p;
	}

	@Override
	public SetADT<Object> difference(SetADT<Object> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(SetADT<Object> set) {
		// TODO Auto-generated method stub
		Set<String> a=new HashSet<String>();
		Set<String> b=new HashSet<String>();
		while(iterator().hasNext())
		{
			a.add(iterator().next().toString());
		}
		while(set.iterator().hasNext())
		{
			b.add(set.iterator().next().toString());
		}
		return a.containsAll(b);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return a.size()==0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return a.size();
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		Iterator<Object> ret=a.iterator();
		return ret;
	}

	Vector<Object> a;
	
}
