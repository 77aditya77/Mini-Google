package searchengine.dictionary;

import java.io.Serializable;

public class TreeNode<K extends Comparable<K>, V> implements Serializable{
	K key;
	V value;
	int height;
	TreeNode<K, V> left;
	TreeNode<K, V> right;
	TreeNode<K, V> parent;
	TreeNode(K key, V value)
	{
		this.key=key;
		this.value=value;
		this.height=0;
		this.left=this.right=this.parent=null;
	}
	TreeNode(K key, V value,TreeNode<K,V> n)
	{
		this.key=key;
		this.value=value;
		this.height=0;
		this.left=this.right=null;this.parent=n;
	}
	void updateHeight()
	{
		int x,y;
		if(left!=null)
		{
			x=left.height;
		}
		else
		{
			x=-1;
		}
		if(right!=null)
		{
			y=right.height;
		}
		else
		{
			y=-1;
		}
		if(x!=y)
		{
			if(x<y)
			{
				height=y+1;
			}
			else
			{
				height=x+1;
			}
		}
		else
		{
			height=x+1;
		}
	}
}