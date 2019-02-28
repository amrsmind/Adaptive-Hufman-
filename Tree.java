import java.util.*;

public class Tree {
	Node root = new Node();
	Node tempnyt = null;
	Node oldnyt = null;
	  ArrayList<Node> temparray = new ArrayList<Node>();
	Tree(){
	       root = new Node();
	}
	
	public boolean contain(char s,Node n){
		if(n == null) {
			return false;
		}
		if(n.symbol == s) {
			return true;
		}
		
	     return (contain(s,n.left) || contain(s,n.right));
	}
	
	public void SearchForNyt(Node n){
		if(n == null)
		    return;
		if(n.nyt){
			 tempnyt = n;
			 return;
		}
		 SearchForNyt(n.left);
		 SearchForNyt(n.right); //////////////////////////////////
	}
	
	public void addinNyt(Node oldnyt,char s) {
		oldnyt.left = new Node();
		oldnyt.right = new Node();
		
		oldnyt.left.count = 0;
		oldnyt.nyt = false;
		oldnyt.left.nyt = true;
		oldnyt.right.symbol = s;
		oldnyt.right.count = 1;
		
		int oldnytnumber = oldnyt.number;
		
		oldnyt.right.number = oldnytnumber-1;
		oldnyt.left.number = oldnytnumber-2;
		
		oldnyt.left.parent = oldnyt.right.parent = oldnyt;
	}
	
	public void incrementcount(Node nyt){
        if(nyt.parent==null) {
        	 nyt.count++;
             return;
        }
        nyt.count++;
        incrementcount(nyt.parent);
	}
	
	public void Searchs(Node n,char s){ //is this passing by reference? 
	      if(n == null){
	    	  return;
	      }
	      if(n.symbol == s) { //not sure if this means char == char 
	    	  //System.out.println(n.number);
	    	  tempnyt = n;
	    	  return;
	      }
	      Searchs(n.left,s);
	      Searchs(n.right,s);
	}
	
	
	public void addExistedS(Node n,char s){ //is this passing by reference? 
	      if(n == null){
	    	  return;
	      }
	      if(n.symbol == s) { //not sure if this means char == char 
	    	  //System.out.println(n.number);
	    	  tempnyt = n;
	    	  n.count++;
	    	  return;
	      }
	      
	      addExistedS(n.left,s);
	      addExistedS(n.right,s);
	}
	
	public void addNonExistedS(Node n,char s){
		SearchForNyt(n);
		oldnyt = tempnyt;
		addinNyt(tempnyt,s);
		incrementcount(tempnyt);
	}
	
	/*public void Update1(Node n) {
		if(n.left == null && n.right == null) {
			return;
		}
		if(n.left.count >= n.right.count) {
			Node temp = n.left;
			n.left = n.right;
			n.right = temp;
			
			int tempnum = n.left.number;
			n.left.number = n.right.number;
			n.right.number = tempnum;
			
			int tempcode = n.left.code;
			n.left.code = n.right.code;
			n.right.code = tempcode;
		}
		Update1(n.left);
		Update1(n.right);
	}*/
	public void update1(Node n) {
		if(n==null) {
			return;
		}
		if(n.left.count > n.right.count) {
			Node temp = n.left;
			n.left = n.right;
			n.right = temp;
			
			int tempnum = n.left.number;
			n.left.number = n.right.number;
			n.right.number = tempnum;
			
			String tempcode = n.left.code;
			n.left.code = n.right.code;
			n.right.code = tempcode;
		}
		update1(n.parent);
	}
	public void update2p1(Node n) {
		if(n==null) {
			return;
		}
		if(n.left.count >= tempnyt.count && ((int)n.left.symbol) != 0) {
			temparray.add(n.left);
		}
		if(n.right.count >= tempnyt.count && ((int)n.right.symbol) != 0) {
			temparray.add(n.right);
		}
		update2p1(n.parent);
	}
	public void upate2p2() {
		Node greaterNumber = new Node();
		greaterNumber.number = 0;
        for(Node n:temparray) {
        	if(n.number > greaterNumber.number) {
        		greaterNumber = n;
        	}
        }
        char tempc = tempnyt.symbol;
        tempnyt.symbol = greaterNumber.symbol;
        greaterNumber.symbol = tempc;
        
        int tempcount = tempnyt.count;
        tempnyt.count = greaterNumber.count;
        greaterNumber.count = tempcount;
	}
	public void update2(Node r,char s){
	       Searchs(r,s);
	       update2p1(tempnyt.parent);
	       upate2p2();
	}


	public void CodeTree(Node root){
		if(root.left == null || root.right == null) {
			return;
		}
	     root.left.code = root.code + '0';
	     root.right.code = root.code + '1';
	     CodeTree(root.left);
	     CodeTree(root.right);
	}
	
	public void print(Node n){
		if(n == null) {
			return;
		}
		print(n.left);
		System.out.println(n.symbol +"  "+n.count+"  "+n.number + "  "+ n.code);
		print(n.right);
	}
	

	
	
}