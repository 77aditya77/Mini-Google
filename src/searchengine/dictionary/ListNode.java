package searchengine.dictionary;

import java.io.Serializable;

class ListNode<K extends Comparable<K>, V> implements Serializable{
	K key;
	V value;
	ListNode<K, V> next;
	ListNode(K key,V value,ListNode<K,V> n)
	{
		this.key=key;
		this.value=value;
		this.next=null;
		n.next=this;
	}
	ListNode(K key, V value)
	{
		this.key=key;
		this.value=value;
		this.next=null;
	}
}