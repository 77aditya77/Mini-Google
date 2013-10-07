package searchengine.dictionary;

import java.io.Serializable;

public class MyHashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>, Serializable{

	public MyHashDictionary()
	{
		head=new ListNode[10];
		tail=new ListNode[10];
		Size=0;
	}
	
	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		if(Size!=0)
		{
			String[] keys=new String[Size];
			ListNode<K,V> traverse;
			int i=0,j=0;
			while(i!=10)
			{
				if(head[i]!=null)
				{
					traverse=head[i];
					while(traverse.next!=null)
					{
						keys[j]=(String)traverse.key;
						traverse=traverse.next;
						j++;
					}
					keys[j]=(String)traverse.key;
					j++;
				}
				i++;
			}
			return (K[])keys;
		}
		else
		{
			return null;
		}
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
			ListNode<K,V> traverse=head[(String.valueOf(str).length())%9];
		
			while(traverse.key!=str)
			{
				traverse=traverse.next;
			}
			return traverse.value;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		int i=0;
		i=(String.valueOf(key).length())%9;
		if(head[i]==null)
		{
			head[i]=new ListNode<K,V>(key,value);
			tail[i]=head[i];
		}
		else
		{
			ListNode<K,V> trav=head[i];
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
			tail[i]=new ListNode<K,V>(key,value,tail[i]);
		}
		Size++;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
			int i=(String.valueOf(key).length())%9;
			ListNode<K,V> traverse=head[i];
			if(traverse!=null)
			{
				ListNode<K,V> previous=null;
				previous=traverse;
				while(traverse.next!=null && traverse.key!=key)
				{
					previous=traverse;
					traverse=traverse.next;
				}
				if(traverse.key.compareTo(key)==0)
				{
					if(previous!=traverse)
					{
						if(previous==head[i])
						{
							if(traverse.next==null)
							{
								head[i].next=null;
								tail=head;
							}
							else
							{
								previous.next=traverse.next;
								head[i]=previous;
							}
						}
						else
						{
							previous.next=traverse.next;
							if(previous.next==null)
							{
								tail[i]=previous;
							}
						}
					}
					else
					{
						if(head[i].next==null)
						{
							head[i]=null;tail[i]=null;
						}
						else
						{
							head[i]=head[i].next;
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
				System.out.println("Given key is invalid!");
			}
	}
	ListNode<K,V>[] head;
	ListNode<K,V>[] tail;
	int Size;
}
