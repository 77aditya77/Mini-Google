package searchengine.dictionary;

import java.io.Serializable;
import java.util.ArrayList;

public class AVLDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>, Serializable{

	public AVLDictionary()
	{
		root=null;
		keys=new ArrayList<String>();
		Size=0;
	}
	
	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		String k[]=new String[Size];
		this.keys.clear();
		inorder(root);
		Object arr[]=new Object[Size];
		arr=this.keys.toArray();
		for(int i=0;i<Size;i++)
		{
			k[i]=(String)arr[i];
		}
		if(Size==0){return null;}
		return (K[])k;
	}

	public void inorder(TreeNode<K,V> n)
	{
		if(n!=null)
		{
			inorder(n.left);
			this.keys.add((String)n.key);
			inorder(n.right);
		}
	}
	
	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		TreeNode<K,V> temp=root;
		while(temp!=null)
		{
			if(temp.key.compareTo(str)==0)
			{
				return temp.value;
			}
			else
			{
				if(temp.key.compareTo(str)<0)
				{
					temp=temp.right;
				}
				else
				{
					temp=temp.left;
				}
			}
		}
		return null;
	}
	
	public boolean balance(TreeNode<K,V> n)
	{
		if(n.left!=null && n.right!=null)
		{
			if((n.left.height-n.right.height)==-1 || (n.left.height-n.right.height)==0 || (n.left.height-n.right.height)==1)
			{
				return true;
			}
		}
		else
		{
			if(n.height<=1)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		if(root==null)
		{
			root=new TreeNode<K,V>(key,value);
		}
		else
		{
			TreeNode<K,V> traverse=root,prev=root;
			while(traverse!=null)
			{
				prev=traverse;
				if(traverse.key.compareTo(key)>0)
				{
					traverse=traverse.left;
				}
				else
				{
					if(traverse.key.compareTo(key)<0)
					{
						traverse=traverse.right;
					}
					else
					{
						String val=(String)prev.value+"^^"+(String)value;
						prev.value=(V)val;
						return;
					}
				}
			}
			if(prev.key.compareTo(key)>0)
			{
				prev.left=new TreeNode<K,V>(key,value,prev);
			}
			else
			{
				prev.right=new TreeNode<K,V>(key,value,prev);
			}
			TreeNode<K,V> modify=prev;
			while(modify!=null)
			{
				modify.updateHeight();
				modify=modify.parent;
			}
		}
		Size++;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		TreeNode<K,V> temp=root;
		int found=0;
		while(temp!=null)
		{
			if(temp.key.compareTo(key)==0)
			{
				if(temp.left==null && temp.right==null)
				{
					Size--;
					TreeNode<K,V> p=temp.parent;
					if(p!=null)
					{
						if(p.left==temp)
						{
							p.left=null;
						}
						else
						{
							p.right=null;
						}
						return;
					}
					else
					{
						root=null;
						return;
					}
				}
				else
				{
					if(temp.left!=null)
					{
						int i=0;
						TreeNode<K,V> trav=temp,prev=temp;
						trav=trav.left;
						while(trav.right!=null)
						{
							i++;
							prev=trav;
							trav=trav.right;
						}
						if(i>0)
						{
							prev.right=null;
							if(trav.left!=null)
							{
								TreeNode<K,V> wing=prev;
								TreeNode<K,V> tempParent=wing.parent;
								TreeNode<K,V> attach=trav.left;
								while(attach.left!=null)
								{
									attach=attach.left;
								}
								attach.left=wing;
								wing.parent=attach;
								TreeNode<K,V> resolveParent=trav.left;
								resolveParent.parent=tempParent;
								if(i>1)
								{
									tempParent.right=resolveParent;
								}
								else
								{
									tempParent.left=resolveParent;
								}
							}
							temp.key=trav.key;
							temp.value=trav.value;
						}
						else
						{
							prev.left=trav.left;
							prev.key=trav.key;
							prev.value=trav.value;
						}
					}
					else
					{
						int i=0;
						TreeNode<K,V> trav=temp,prev=temp;
						trav=trav.right;
						while(trav.left!=null)
						{
							i++;
							prev=trav;
							trav=trav.left;
						}
						if(i>0)
						{
							prev.left=null;
							if(trav.right!=null)
							{
								TreeNode<K,V> wing=prev;
								TreeNode<K,V> tempParent=wing.parent;
								TreeNode<K,V> attach=trav.right;
								while(attach.right!=null)
								{
									attach=attach.right;
								}
								attach.right=wing;
								wing.parent=attach;
								TreeNode<K,V> resolveParent=trav.right;
								resolveParent.parent=tempParent;
								if(i>1)
								{
									tempParent.left=resolveParent;
								}
								else
								{
									tempParent.right=resolveParent;
								}
							}
							temp.key=trav.key;
							temp.value=trav.value;
						}
						else
						{
							prev.right=trav.right;
							prev.key=trav.key;
							prev.value=trav.value;
						}
					}
				}
				found=1;
				Size--;
			}
			else
			{
				if(temp.key.compareTo(key)<0)
				{
					temp=temp.right;
				}
				else
				{
					temp=temp.left;
				}
			}
		}
		if(found==0)
		{
				System.out.println("Entered key is invalid!");
		}
	}
	int Size;
	TreeNode<K,V> root;
	ArrayList<String> keys;

}
