import java.util.*;

public class UI {

	public static void main(String[] args) {
        //Scanner In = new Scanner(System.in);
        //String str = In.nextLine();
        AdaptiveHufman a = new AdaptiveHufman("abcc");
       // System.out.println(a.str);
 
        a.encoding();
        a.t.print(a.t.root);
        System.out.println(a.Compressed);

       // System.out.println("UI");
        
      
	}

}
