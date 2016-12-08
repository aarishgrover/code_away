package trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RBtree {

	public class rbnode {

		public String data;
		rbnode left;
		rbnode right;
		rbnode p;
		int color;
		public rbnode(String x)
		{
			color = 1;
			left = nil;
			right = nil;
			p = nil;
			this.data=x;
		}
		public rbnode() {
			
				}
	}
	public rbnode nil = new rbnode("");
	public rbnode root = nil;
	RBtree()
	{
		root.left = nil;
		root.right=nil;
        root.p = nil;

	}
	public void insertintree(String x)
	{ 
		rbnode y =new rbnode(x);
		insertnode(y);
	}
	

	public void insertnode(rbnode z)
	{
		//System.out.println("here"+z.data);
		rbnode y = nil;
		rbnode x = root;
		while(x!=nil)
		{
			y=x;
			if(z.data.compareTo(x.data) < 0)
			{
				x=x.left;
			}
			else x=x.right;
			
		}
		//System.out.println("here");
		z.p = y;
		//System.out.println("here");
		if(y==nil)
		{
			//System.out.println("here");
			root = z;
		}
		else if (z.data.compareTo(y.data) < 0)
		{
			y.left=z;
		}
		else y.right = z;
		z.left = nil;
		z.right = nil;
		z.color = 0;
		rbfixup(z);
	}
	
	public void printtree(RBtree t)
	{
		printnode(t.root);
	}
	public void printnode(rbnode currentnode)
	{
		//System.out.println("root empty?");
		if(currentnode==nil)
		{
			return;
		}
		printnode(currentnode.left);
		System.out.println(currentnode.data);
		printnode(currentnode.right);
	}
	
	public rbnode findsibling(String r)
	{
		rbnode parent = lookup(r).p;
		if (parent!=null)
			{
				if(parent.data.compareTo(r)<0)
					return parent.left;
					else if(parent.data.compareTo(r)>0)
						return parent.right;
			}
		return null;
	}
private void rotateleft(rbnode x)
{
	rbnode y = x.right;
	x.right = y.left;
	if(y.left!=nil)
	{
		y.left.p=x;
	}
	y.p = x.p;
	if(x.p==nil)
	{
		root = y;
	}
	else if(x.p.left==x)
	{
		x.p.left = y;
	}
	else
		x.p.right = y;
	y.left = x;
	x.p = y;	
}
private void rotateright(rbnode y)
{
	rbnode x = y.left;
	y.left = x.right;
	if(x.right!=nil)
	{
		x.right.p=y;
	}
	x.p = y.p;
	if(y.p==nil)
	{
		root = x;
	}
	else if(y==y.p.right)
	{
		y.p.right = x;
	}
	else
		y.p.left = x;
	x.right = y;
	y.p = x;
	
}
public void rbfixup(rbnode z)
{
	rbnode y = nil;
	//System.out.println(z.p.color);
	while (z.p.color == 0)
	{
		//System.out.println("here");
		if (z.p == z.p.p.left)
		{
			y = z.p.p.right;
			if (y.color == 0)
			{
				z.p.color =1;
				y.color = 1;
				z.p.p.color =  0;
				z = z.p.p;
			}
			else if (z == z.p.right){
				z = z.p;
				rotateleft(z);
			}
			else{
				z.p.color = 1;
				z.p.p.color =0;
				rotateright(z.p.p);
			}
		}
		else{
			y = z.p.p.left;
			if (y.color == 0){
				z.p.color = 1;
				y.color = 1;
				z.p.p.color = 0;
				z = z.p.p;
			}

			else if (z == z.p.left){
				z = z.p;
				rotateright(z);
			}
			else{
				z.p.color = 1;
				z.p.p.color = 0;
				rotateleft(z.p.p);
			}
		}
	}
root.color = 1;
}
public rbnode findgp(String r)
{
	rbnode parent = lookup(r).p;
	return parent.p;
}

public rbnode findaunt(String r)
{
	rbnode grandparent = findgp(r);
if (grandparent!=null)
{
	if(grandparent.data.compareTo(r)<0)
		return grandparent.left;
	else if(grandparent.data.compareTo(r)>0)
		return grandparent.right;
}
return null;
}
public rbnode lookup(String string)
{
	return lookupnode(root,string);
	
}
private rbnode lookupnode(rbnode currentnode, String string) {

	if(currentnode==nil||string.compareTo(currentnode.data)==0)
	{
		return currentnode;
	}
	else if(string.compareTo(currentnode.data)<0)
	{
		return lookupnode(currentnode.left, string);
	}
	else if(string.compareTo(currentnode.data)>0)	
	{
		return lookupnode(currentnode.right, string);
	}
	return null;
}
//dictionary method
public static void main(String[] args) {
	try {
		File file = new File("Dictionary.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		RBtree rbt = new RBtree();
		double start = System.currentTimeMillis();
		while ((line = bufferedReader.readLine()) != null) {
			rbt.insertintree(line);
		}
		double end = System.currentTimeMillis();
	    System.out.println("\nRuntime for Insertion"+(end-start));   
		fileReader.close();
		start = System.currentTimeMillis();
		RBtree.rbnode r = rbt.lookup("abandonee");
		end = System.currentTimeMillis();
	    System.out.println("\nRuntime for Lookup"+(end-start));   
if(r==rbt.nil)
{
	System.out.println("Word does not exist in dictionary");
}
else
		System.out.println("found "+r.data+ " with color "+r.color+" with parent " +r.p.data);
	} catch (IOException e) {
		e.printStackTrace();
	}
}  public static interface Visitor
  {
  	/**
     This method is called at each node.
     @param n the visited node
  	 */
  	void visit(rbnode n);
  }


 public void preOrderVisit(Visitor v)
  {
  	preOrderVisit(root, v);
  }


 private void preOrderVisit(rbnode n, Visitor v)
  {
  	if (n == nil)
  		{
  		return;
  		}
  	v.visit(n);
  	preOrderVisit(n.left, v);
  	preOrderVisit(n.right, v);
  }


}