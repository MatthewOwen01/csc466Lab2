import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;



public class C45<A, T, C> {
	public ArrayList<C45Data> allData = new ArrayList<C45Data>();
	private Node head = null;
	private final double log2 = Math.log(2);
	private ArrayList<C> catagories = new ArrayList<C>();
	private ArrayList<ArrayList> atributes = new ArrayList<ArrayList>();
	private ArrayList<T> atributeLables = new ArrayList<T>();
	
	public void add(Vector<A> a, C c){
		allData.add(new C45Data(a,c));
	}
	
	public void setAtribuuteLables(ArrayList<T> a){
		atributeLables = a;		
	}
	
	public Node buildTree(){
		buildCaragories(allData);
		buildAtrCount(allData);
		//System.out.println(entropyOnRow(0, allData));
		//System.out.println(chooseAtrib(allData));
		head = addTree(allData, atributeLables);

		return head;
	}
	
	private void buildCaragories(ArrayList<C45Data> data){
		for (final C45Data d : data){
			if (!catagories.contains(d.classified)){
				catagories.add((C) d.classified);
			}
			
		}
		//System.out.println(catagories);
	}
	private void buildAtrCount(ArrayList<C45Data> data){
		
		for (A a : data.get(0).atributes){
			ArrayList<A> tmpArray = new ArrayList<A>();
			tmpArray.add(a);
			atributes.add(tmpArray);
		}
		
		
		for (int i = 1; i < data.size(); i++){
			for (int j = 0; j < data.get(i).atributes.size(); j++){
				if (!atributes.get(j).contains(data.get(i).atributes.get(j))){
					atributes.get(j).add(data.get(i).atributes.get(j));
				}
			}
			
			
		}
		//System.out.println(atributes);
	}
	
	private boolean isHomogeneous(ArrayList<C45Data> data){
		C first =  data.get(0).classified;
		for (C45Data d : data){
			if (d.classified != first){
				return false;
			}
		}
		return true;
	}
	
	public Node addTree(ArrayList<C45Data> data, ArrayList<T> aLabels){
		//check for termination
		if (aLabels.size() == 0){
			return new Node(getConcensus(data));
		}
		//test for homogonous data
		if (isHomogeneous(data)){
			return new Node(data.get(0).classified);
		}
		
		ArrayList<ArrayList<C45Data>> splitData = new ArrayList<ArrayList<C45Data>>();
		
		
		int splitOn = chooseAtrib(data);
		for (int i = 0; i < atributes.get(splitOn).size(); i++){
			splitData.add(new ArrayList<C45Data>());
		}
		Node node = new Node();
		node.atribute = aLabels.remove(splitOn);
		
		while (data.size()>0){
			C45Data d = data.remove(0);
			splitData.get(atributes.get(splitOn).indexOf(d.atributes.get(splitOn))).add(d);
			d.atributes.remove(splitOn);
		}
		for (int i = 0; i < atributes.get(splitOn).size(); i++){
			T t = (T) atributes.get(splitOn).get(i);
			Node tmpNode = addTree(splitData.get(i), aLabels);
			node.edges.put( t, tmpNode);
		}
		
		return node;
	}
		
	
	private C getConcensus(ArrayList<C45Data> data) {
		HashMap<C,Integer> cats = new HashMap<C,Integer>();
		int max = 0;
		C catagory = null;
		for (C c : catagories){
			cats.put(c,  0);
		}
		for (C45Data d : data){
			cats.put(d.classified, cats.get(d.classified)+1);
		}
		for (C c : cats.keySet()){
			int tmpMax=0;
			if ((tmpMax = cats.get(c))> max){
				max = tmpMax;
				catagory = c;
			}
		}
		
		return catagory;
	}

	private int chooseAtrib(ArrayList<C45Data> data){
		double baseEntropy = entropy(data);
		double gain = Double.MAX_VALUE;
		int best = -1;
		for (int i = 0; i < atributes.size(); i++){
			double tmpGain = baseEntropy - entropyOnRow(i, data);
			if (tmpGain < gain){
				gain = tmpGain;
				best = i;
			}
		}
		return best;
	}

	
	private double entropyOnRow(int row, ArrayList<C45Data> data){
		double sum = 0.0;
		ArrayList<ArrayList<C45Data>> splitData = new ArrayList<ArrayList<C45Data>>();
		for (int i = 0; i < atributes.get(row).size(); i++){
			splitData.add(new ArrayList<C45Data>());
		}
		for (C45Data d : allData){
			splitData.get(atributes.get(row).indexOf(d.atributes.get(row))).add(d);
		}
		
		for (ArrayList<C45Data> d : splitData){
			double e = entropy(d);
			double ratio = (double) d.size() / data.size();
			
			sum += ratio * e;
		}
		
		return sum;
	}
	
	public double entropy (ArrayList<C45Data> data){
		double sum = 0.0;
		HashMap<C,Double> h = new HashMap<C,Double>();
		for (C c : catagories){
			h.put(c,  0.0);
		}
		for (C45Data d : data){
			h.put(d.classified, h.get(d.classified)+1);
		}
		for (final C c : catagories){
			double tmpVal = h.get(c)/data.size() * Math.log(h.get(c)/data.size())/ log2;
			if (!Double.isNaN(tmpVal)){
				sum+= h.get(c)/data.size() * Math.log(h.get(c)/data.size())/ log2 ;
			}
		}
		return - sum;
	}
	
	public class Node{
		T atribute = null;
		HashMap<T, Node> edges = new HashMap<T, Node>();
		C classified = null;
		public Node(C c) {
			classified = c;
		}
		public Node() {
			// TODO Auto-generated constructor stub
		}
	}

	public class C45Data{
		Vector<A> atributes = null;
		C classified = null;
		
		public C45Data(Vector<A> v, C c){
			atributes = v;
			classified = c;
		}
		public C45Data(C c){
			atributes = null;
			classified = c;
		}
		
		
	}
	
	
}
