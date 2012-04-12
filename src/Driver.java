import java.util.ArrayList;
import java.util.Vector;



public class Driver {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		C45<Comparable, String, String> c45 = new C45<Comparable, String, String>();
		Vector<Comparable> v1 = new Vector<Comparable>();
		Vector<Comparable> v2 = new Vector<Comparable>();
		Vector<Comparable> v3 = new Vector<Comparable>();
		Vector<Comparable> v4 = new Vector<Comparable>();
		v1.add(1); v1.add('a');
		v2.add(1); v2.add('b');
		v3.add(2); v3.add('a');
		v4.add(2); v4.add('b');

	
		
		c45.add(v1, "y");
		c45.add(v2, "n");
		c45.add(v3, "n");
		c45.add(v4, "y");
		

		
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("number");
		labels.add("leter");
		c45.setAtributeLables(labels);
		c45.setThreshold(0);
		C45.Node n = c45.buildTree();
		
		Vector<Comparable> v5 = new Vector<Comparable>();
		v5.add(2); v5.add('a');

		
		System.out.println(c45.clasify(v5).toString());
		
	}

}
