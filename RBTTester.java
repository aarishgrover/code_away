package trees;
import static org.junit.Assert.*;

import org.junit.Test;


public class RBTTester {

	@Test
	public void test() {
		RBtree rbt = new RBtree();
        rbt.insertintree("D");
        rbt.insertintree("B");
        rbt.insertintree("A");
        rbt.insertintree("C");
        rbt.insertintree("F");
        rbt.insertintree("E");
        rbt.insertintree("H");
        rbt.insertintree("G");
        rbt.insertintree("I");
        rbt.insertintree("J");
      //  rbt.printtree(rbt);
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     
"Color: 1, Key:D Parent: "
+ "\n"+
"Color: 1, Key:B Parent: D\n"+
"Color: 1, Key:A Parent: B\n"+
"Color: 1, Key:C Parent: B\n"+
"Color: 1, Key:F Parent: D\n"+
"Color: 1, Key:E Parent: F\n"+
"Color: 0, Key:H Parent: F\n"+
"Color: 1, Key:G Parent: H\n"+
"Color: 1, Key:I Parent: H\n"+
"Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
	@Test
	public void TestGPAuntSiblinglookup() {
		RBtree rbt = new RBtree();
        rbt.insertintree("D");
        rbt.insertintree("B");
        rbt.insertintree("A");
        rbt.insertintree("C");
        rbt.insertintree("F");
        rbt.insertintree("E");
        rbt.insertintree("H");
        rbt.insertintree("G");
        rbt.insertintree("I");
        rbt.insertintree("J");
	    String strr = "B";//Aunt of H test
	    assertEquals(strr, rbt.findaunt("H").data);
	    strr = "H";//Lookup test
		assertEquals(strr, rbt.lookup("H").data);
		strr = "D";//GP of H
		assertEquals(strr, rbt.findgp("H").data);
		strr = "E";//Sibling of H
		assertEquals(strr, rbt.findsibling("H").data);
    }
	

    
    public static String makeString(RBtree t)
    {
       class MyVisitor implements RBtree.Visitor {
          String result = "";
          public void visit(RBtree.rbnode n)
          {
             result = result + n.data;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RBtree t) {
    	{
    	       class MyVisitor implements RBtree.Visitor {
    	          String result = "";
    	          public void visit(RBtree.rbnode n)
    	          {
    	        	  if(!(n.data).equals(""))
    	        		  result = result +"Color: "+n.color+", Key:"+n.data+" Parent: "+n.p.data+"\n";
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }
  
    
    
 }
  
