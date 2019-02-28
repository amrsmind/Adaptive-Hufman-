import java.util.*;

import javafx.util.Pair;

public class AdaptiveHufman{
     Tree t = new Tree();
     Tree tforencoding = new Tree();
     String str;
     int distinctcount = 0;
     int shortcodesize;
	 ArrayList<Character> shortcodes = new ArrayList<Character>();
     ArrayList<Pair<Character,String>> pairshortcodes = new ArrayList<Pair<Character,String>>();
	 String Compressed = "";
	 String Out="";

     int n;
     AdaptiveHufman(String str){
    	 this.str = str;
    	 this.n = str.length();
    	 
     }
     public void generatealistofshortcodes(){
    	 for(int i=0;i<n;i++) {
    		 if(shortcodes.contains(str.charAt(i))) {
    			 continue;
    		 }
    		 shortcodes.add(str.charAt(i));
    		 distinctcount++;
    	 }
     }
     
     public String getshortcode(char s){
    	for(int i=0;i<distinctcount;i++) {
    		if(Math.pow(2, i)>distinctcount) {
    			shortcodesize = i;
    			break;
    		}
    	} 
    	 int index = shortcodes.indexOf(s);
    	 String bin = Integer.toBinaryString(index);
    	 int binlength = bin.length();
    	 while(binlength!=shortcodesize){
    		 bin = '0' + bin;
    		 binlength++;
    	 }
    	 return bin;
     }
     
     public void genereatepairs(){
    	 for(char c:shortcodes) {
    		 pairshortcodes.add(new Pair<Character,String>(c,getshortcode(c)));
    	 }
     }
     
     public char findinpairs(String s){
    	 for(Pair<Character,String> p:pairshortcodes) {
    		 if(p.getValue().contains(s)) {
    			 return p.getKey();
    		 }
    	 }
    	 return ' ';
     }
     
	public void encoding(){
       int N = 2*n + 1;
       t.root.right = new Node();
       t.root.left = new Node();
       
        t.root.left.nyt = true;
		t.root.parent = null;
       
       t.root.right.number = N; 
       t.root.left.number = N-1; //first nyt
       t.root.right.symbol = str.charAt(0);
       t.root.right.count = 1;
       t.root.count = 1;
       t.root.left.parent = t.root;
       t.root.right.parent = t.root;
       
       //code the Rootleft and Rootright
       t.root.code = "";
       t.CodeTree(t.root);
       generatealistofshortcodes();
       //t.root.left.code = "0";
       //t.root.right.code = "1";
       Compressed+= getshortcode(str.charAt(0));
       //System.out.println(FirstShortCode);
       //for(char s:shortcodes) {
       //	   System.out.println(shortcodes);
       //}
              
       for(int i=1;i<n;i++){
    	   char s = str.charAt(i);
    	   if(t.contain(s, t.root)) {
    		   //t.print(t.root);
    		   //System.out.println("------------");
    		   t.Searchs(t.root,s);
    		   Compressed+= t.tempnyt.code;
    		   t.update2(t.root,s);
    		   t.addExistedS(t.root,s);
    		   t.incrementcount(t.tempnyt.parent);
    		   t.update1(t.tempnyt.parent);
    		   t.CodeTree(t.root);
    		   //System.out.println(i + "existed");
    	   }
    	   else {
    		   //System.out.println(i+"nonexisted");
    		   
    		   t.addNonExistedS(t.root,s);
    		   
    		   Compressed+=t.oldnyt.code;
    		   Compressed+=getshortcode(s);
    		   
    		   t.update1(t.tempnyt.parent);
    		   t.CodeTree(t.root);
    		   
    		   /////
    		   ////

    	   }
       }
       
	}
	/*public void decoding(String dstr){
		genereatepairs();
		int i = 0;
		i+=shortcodesize;
		Out+=shortcodes.get(0);
		
	       int N = 2*n + 1;
	       tforencoding.root.right = new Node();
	       tforencoding.root.left = new Node();
	       
	        tforencoding.root.left.nyt = true;
			tforencoding.root.parent = null;
	       
	       tforencoding.root.right.number = N; 
	       tforencoding.root.left.number = N-1; //first nyt
	       tforencoding.root.right.symbol = Out.charAt(0);
	       tforencoding.root.right.count = 1;
	       tforencoding.root.count = 1;
	       tforencoding.root.left.parent = tforencoding.root;
	       tforencoding.root.right.parent = tforencoding.root;
	       
	       //code the Rootleft and Rootright
	       tforencoding.root.code = "";
	       tforencoding.CodeTree(tforencoding.root);
		
		while(i<n){
			tforencoding.SearchForNyt(tforencoding.root);
			String tempt = "";
			for(int j=i;j<n;j++) {
				tempt+=dstr.charAt(j);
				if(tempt.contains(tforencoding.tempnyt.code)){
				       	i=j+1;
				       	break;
				}
				char c = findinpairs(tempt);
				if(c!=' '){
					Out+=c;
					i = j+1;
					break;
				}
				}
			
		}
		
		
		
	}*/
	
    
}
