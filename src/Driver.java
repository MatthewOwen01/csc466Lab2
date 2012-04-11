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
		v1.add(3); v1.add('y');
		v2.add(3); v2.add('n');
		v3.add(4); v3.add('y');
		v4.add(4); v4.add('n');


		
		c45.add((Vector<Comparable>)v2.clone(), "n");
		c45.add((Vector<Comparable>)v1.clone(), "y");
		c45.add((Vector<Comparable>)v1.clone(), "n");
		c45.add((Vector<Comparable>)v1.clone(), "n");
		c45.add((Vector<Comparable>)v2.clone(), "n");
		c45.add((Vector<Comparable>)v1.clone(), "y");
		c45.add((Vector<Comparable>)v1.clone(), "n");
		c45.add((Vector<Comparable>)v2.clone(), "n");

		c45.add((Vector<Comparable>)v4.clone(), "y");
		c45.add((Vector<Comparable>)v3.clone(), "n");
		c45.add((Vector<Comparable>)v3.clone(), "y");
		c45.add((Vector<Comparable>)v4.clone(), "n");
		c45.add((Vector<Comparable>)v4.clone(), "y");
		c45.add((Vector<Comparable>)v3.clone(), "y");
		c45.add((Vector<Comparable>)v4.clone(), "n");
		c45.add((Vector<Comparable>)v3.clone(), "n");
		

		
		ArrayList<String> labels = new ArrayList<String>();
		labels.add("Bedrooms");
		labels.add("Basement");
		c45.setAtribuuteLables(labels);
		C45.Node n = c45.buildTree();
		System.out.print(n.toString());
		//System.out.print(c45.entropy(c45.allData));
		
	}

}
