package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

class HuffmanTree
{

    private class FrequencyTable
    {
		private ArrayList<Character> chars = new ArrayList<>();
		private ArrayList<Integer>   frequencies = new ArrayList<>();

		int SIZE = 0;

		private String text;
		
		public FrequencyTable(String t)
		{
		    int N = t.length();

		    //Create Table
		    for(int i = 0; i < N; i++)
			{
			    char letter = t.charAt(i);

			    if(exists(letter))
					increment(letter);
			    else
					add(letter, 1);

			}

			//Sort Table: Selection Sort

			for(int i = 0; i < SIZE - 1; i++)
			{
				int min = i;

				for(int j = (i+1); j < SIZE; j++)
				{

					if (frequencies.get(j) < frequencies.get(min))
						min = j;
				}

				swap(i, min);
			}


			
		   
		}

		private void swap(int i, int j)
		{
			if(i > SIZE || j > SIZE)
				throw new IllegalArgumentException("Invalid Indexes");

			//Swap The Two Entries
			int  tmpFreq = frequencies.get(j);
			char tmpChar = chars.get(j);

			frequencies.set(j, frequencies.get(i));
			chars.set(j, chars.get(i));

			frequencies.set(i, tmpFreq);
			chars.set(i, tmpChar);
		}

	    public void add(char c, int freq)
	    {
			SIZE += 1;
			
			chars.add(c);
			frequencies.add(freq);
		    }

		    public void increment(char c)
		    {
			if(!exists(c))
			    throw new IllegalArgumentException("Invalid Character");

			char tmp;
			int freq;
			
			for(int i = 0; i < SIZE; i++)
			    {
				//Get Character & Frequency
				tmp = chars.get(i);
			        freq = frequencies.get(i);

				if(c == tmp)
				    {
					//Increment Our Frequency
					//In The Table
					frequencies.set(i, freq+1);
					return;
				    }
			    }

			SIZE += 1;

	    }

		public boolean exists(char c)
		{
		    for(int i = 0; i < SIZE; i++)
			{
			    char tmp = chars.get(i);
			    
			    if(tmp == c)
				return true;
			}
		    return false;
		}

		public int getFrequency(char c)
		{
		    //Check If In Table
		    for(int i = 0; i < chars.size(); i++)
			{
			    if(chars.get(i) == c)
				return frequencies.get(i);
			}

		    //If Character Isn't In The Table
		    return 0;
			    
		}

		//Size Of Table
		public int size()
		{
		    return SIZE;
		}

		//Frequency of Character at Index i
		public Integer getFrequencyAt(int i)
		{
		    if(i >= SIZE)
			throw new IllegalArgumentException("Invalid Index");
		    
		    return frequencies.get(i);
		}

		//Frequency of Character at Index i
		public Character getCharAt(int i)
		{
		    if(i >= SIZE)
			throw new IllegalArgumentException("Invalid Index");
		    
		    return chars.get(i);
		}

		//Remove Element
		public void remove(int i)
		{
			if(i >= SIZE)
				throw new IllegalArgumentException("Invalid Character");

			chars.remove(i);
			frequencies.remove(i);

			SIZE--;
		}

       
    }

    private class Node
    {
    	private char character;
    	private int  frequency;

    	private Node left;
    	private Node right;

    	private boolean hasChar;

    	private int encoding;

    	private boolean hasLeft  = false;
    	private boolean hasRight = false;

    	public Node(char c, int f)
    	{
    		this.character = c;
    		hasChar = true;

    		this.frequency = f;
    	}

    	public Node(int f)
    	{
    		hasChar = false;

    		this.frequency = f;
    	}

    	//Encodes A Value To Node
    	public void setEncoding(int e)
    	{
    		this.encoding = e;
    	}

    	public void setRight(Node n)
    	{
    		this.right = n;
    		this.hasRight = true;
    	}

    	public void setLeft(Node n)
    	{
    		this.left = n;
    		this.hasLeft = true;
    	}

    	public Node getLeft()
    	{
    		return this.left;
    	}

    	public Node getRight()
    	{
    		return this.right;
    	}

    	public boolean hasChar()
    	{
    		return this.hasChar;
    	}

    	public char getChar()
    	{
    		if(!hasChar)
    			throw new RuntimeException("No Character");
    		else
    			return this.character;
    	}

    	public int getFrequency()
    	{
    		return this.frequency;
    	}

    	public boolean hasLeft()
    	{
    		return this.hasLeft;
    	}

    	public boolean hasRight()
    	{
    		return this.hasRight;
    	}
    }

    FrequencyTable table;

    ArrayList<Node> nodeStorage = new ArrayList<>();

    HashMap<Character, String> encodings;

    private Node ROOT;

    public HuffmanTree(String input)
    {
		table = new FrequencyTable(input);

		//Form Nodes
		for(int i = 0; i < table.size(); i++)
		{
			char c = table.getCharAt(i);
			int f = table.getFrequencyAt(i);

			nodeStorage.add(new Node(c, f));
		}

		//Sort Nodes In Node Storage List
		sortNodes(nodeStorage);


		//While Nodes Are Left
		while(nodeStorage.size() > 1)
		{
			//Connect The Nodes With The
			//Smallest Frequency
			Node n = connectNodes(nodeStorage, 0, 1);

			//Remove First Two Nodes (Smallest Two)
			nodeStorage.remove(0);
			nodeStorage.remove(0);

			//Add Connection To Nodes
			nodeStorage.add(n);

			//Sort the Nodes
			sortNodes(nodeStorage);

		}

		this.ROOT = nodeStorage.get(0);
		nodeStorage.remove(0);


		//Assign Encodings
		encodings = new HashMap<>();

		assignEncodings(encodings, ROOT, "");





    }

    private void assignEncodings(HashMap<Character, String> encodings, 
    	Node root, String encoding)
    {
    	Node l, r;
    	String newEnc;

    	if(root.hasLeft())
    	{
    		l = root.getLeft();
    		newEnc = encoding + "0";

    		l.setEncoding(0);

    		if(l.hasChar())
    			encodings.put(l.getChar(), newEnc);

    		assignEncodings(encodings, l, newEnc);
    	}

    	if(root.hasRight())
    	{
    		r = root.getRight();
    		newEnc = encoding + "1";

    		r.setEncoding(1);

    		if(r.hasChar())
    			encodings.put(r.getChar(), newEnc);

    		assignEncodings(encodings, r, newEnc);
    	}
    }

    public HashMap<Character, String> getEncodings()
    {
    	return this.encodings;
    }



    private void sortNodes(ArrayList<Node> list)
    {
    	//Sort Table: Selection Sort

    	int N = list.size();

		for(int i = 0; i < N - 1; i++)
		{
			int min = i;

			for(int j = (i+1); j < N; j++)
			{
				Node n 		 = list.get(j);
				Node currMin = list.get(min);

				if (n.getFrequency() < currMin.getFrequency())
					min = j;
			}

			swap(list, i, min);
		}
    }

    private void swap(ArrayList<Node> list, int i, int j)
	{
		int N = list.size();

		if(i > N || j > N)
			throw new IllegalArgumentException("Invalid Indexes");

		//Swap The Two Entries
		Node tmp = list.get(i);

		list.set(i, list.get(j));

		list.set(j, tmp);
	}

    private Node connectNodes(ArrayList<Node> nodes, int i, int j)
    {
    	if(nodes.size() < 2)
    		return null;

    	Node n1 = nodes.get(i);
		Node n2 = nodes.get(j);

		int totalFreq = n1.getFrequency() + 
							n2.getFrequency();

		Node newNode = new Node(totalFreq);

		newNode.setLeft(n1);
		newNode.setRight(n2);
    	
    	return newNode;
    }

    private Node connect(Node left, Node right)
    {
    	int freq = left.getFrequency() +
    			    right.getFrequency();

    	Node n = new Node(freq);
    	n.setLeft(left);
    	n.setRight(right);

    	return n;
    }

    public FrequencyTable getTable()
    {
		return this.table;
    }

    public Node getRoot()
    {
    	return this.ROOT;
    }

    public void print(Node root)
    {
    	System.out.println("New Node");

    	if(root.hasChar())
    		System.out.println(root.getChar());
    	else
    		System.out.println(root.getFrequency());

    	if(root.hasLeft())
    	{
    		System.out.println("Moving Left To " + root.getLeft().getFrequency());
    		print(root.getLeft());
    	}
    	if(root.hasRight())
		{
			System.out.println("Moving Right To " + root.getRight().getFrequency());
			print(root.getRight());
		}

		if(!root.hasChar())
		{
			System.out.println("Finished With Node NONE");
			System.out.println("Frequency: " + root.getFrequency());
		}
		else if(root.hasChar())
		{
			System.out.println("Finished With Node " + root.getChar());
			System.out.println("Frequency: " + root.getFrequency());
		}
	
    }
	
    public static void main(String[] args)
    {
		String test = "loiosoeelllllleur";
		
		HuffmanTree tree = new HuffmanTree(test);

		HashMap<Character, String> codes = tree.getEncodings();

		Set<Character> chars = codes.keySet();

		for(Character c : chars)
		{
			System.out.println(c + ": " + codes.get(c));
		}
	}

    

    
    
    
}
