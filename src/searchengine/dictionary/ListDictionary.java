package searchengine.dictionary;

import java.io.Serializable;



public class ListDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V> , Serializable{
	
	public ListDictionary()
	{
		head=null;
		tail=null;
		traverse=null;
		Size=0;
	}
	
	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		String[] keys=null;
		if(Size!=0)
		{
			keys=new String[Size];
			traverse=head;
			int i=0;
			while(traverse.next!=null)
			{
				keys[i]=(String)traverse.key;
				traverse=traverse.next;
				i++;
			}
			keys[i]=(String)traverse.key;
		}
		return (K[])keys;
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		traverse=head;
		while(traverse.key!=str)
		{
			traverse=traverse.next;
		}
		return traverse.value;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		if(head==null)
		{
			tail=new ListNode<K,V>(key,value);
			head=tail;
		}
		else
		{
			ListNode<K,V> trav=head;
			while(trav.next!=null)
			{
				if(trav.key.compareTo(key)==0)
				{
					String val=(String)trav.value+"^^"+(String)value;
					trav.value=(V)val;
					return;
				}
				else
				{
					trav=trav.next;
				}
			}
			tail=new ListNode<K,V>(key,value,tail);
		}
		Size++;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		if(head!=null)
		{
			ListNode<K,V> previous=null;
			traverse=head;
			previous=traverse;
			while(traverse.next!=null && traverse.key.compareTo(key)!=0)
			{
				previous=traverse;
				traverse=traverse.next;
			}
			if(traverse.key.compareTo(key)==0)
			{
				if(previous!=traverse)
				{
					if(previous==head)
					{
						if(traverse.next==null)
						{
							head.next=null;
							tail=head;
						}
						else
						{
							previous.next=traverse.next;
							head=previous;
						}
					}
					else
					{
						previous.next=traverse.next;
						if(previous.next==null)
						{
							tail=previous;
						}
					}
				}
				else
				{
					if(head.next==null)
					{
						head=null;tail=null;
					}
					else
					{
						head=head.next;
					}
				}
				Size--;
			}
			else
			{
				System.out.println("Key is not present.");
			}
		}
		else
		{
			System.out.println("Dictionary is empty.");
		}
	}
	ListNode<K,V> head;
	ListNode<K,V> tail;
	ListNode<K,V> traverse;
	int Size;
}
