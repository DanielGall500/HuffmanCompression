package src.huffman;

import src.algorithms.SelectionSort;
import src.utilities.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HuffmanTree
{
	//Table Of How Frequent Each Letter is
    private FrequencyTable freqTable;

    //Encodings For Char => Binary Value
    private final HashMap<Character, String> encodings;

    //Our Tree's Root Node
    private Node ROOT;

    //Our Compressed Bit-string
    private String compressedBits = "";

    public HuffmanTree(String input)
    {
		freqTable = new FrequencyTable(input);

		int N = freqTable.size();

		//While Nodes Are Left
		while(N > 1)
		{
			/*
			Use selection sort to sort the nodes
			in the list.
			 */
			new SelectionSort().sort(freqTable, N);

			//Connect The Nodes With The
			//Smallest Frequency
			Node n = connectNodes(freqTable, 0, 1);

			//Remove First Two Nodes (Smallest Two)
			freqTable.remove(0);
			freqTable.remove(0);

			//Add Connection To Nodes
			freqTable.add(n);

			//Update Nodes Left In Storage
			N = freqTable.size();
		}

		this.ROOT = freqTable.get(0);
		freqTable.remove(0);

		//Assign Encodings
		encodings = new TrieEncoder(ROOT).getEncodings();

		//Create Actual Output After Compression
		char[] text = input.toCharArray();

		for(char c : text)
		{
			String encodedChar = encodings.get(c);
			compressedBits += encodedChar;
		}
    }

    public String decompressBits(String input)
	{
		char nxt;
		boolean canMoveLeft, canMoveRight, endOfInput;

		Node   n = ROOT;
		int    size = input.length();
		String translation = "";

		for(int i = 0; i < size; i++)
		{
			//Get Next Character
			nxt = input.charAt(i);

			//Error Handling: Input Must Be Binary
			if(nxt != '0' && nxt != '1')
				throw new IllegalArgumentException("Input Must Be A Bit-String");

			/*
			Boolean Variables:
			- Can we move left in the tree to a another node.
			- Can we move right in the tree to another node.
			- Are we on the final character in our input
			 */
			canMoveLeft  = (nxt == '0' && n.hasLeft());
			canMoveRight = (nxt == '1' && n.hasRight());
			endOfInput   = (i == (size - 1));

			if(canMoveLeft)
			{
				//N Moves Left
				n = n.getLeft();

				//Check If We're On Final Character
				if(endOfInput)
					translation += n.getChar();

			}
			else if (canMoveRight)
			{
				//N Moves Right
				n = n.getRight();

				//Check If We're On Final Character
				if(endOfInput)
					translation += n.getChar();
			}
			else
			{
				/*
				We can't move left or right in the
				trie. This means that we have arrived
				at our decompressed letter.

				We add this to the translation,
				then reset our node and take our for
				loop back to begin from the root node
				for our next pattern.
				 */

				//Add Decompressed Character
				if(n.hasChar())
					translation += n.getChar();

				//Reset Node & Begin Next Pattern
				n = ROOT;
				i--;
			}
		}

		return translation;
	}

	/* Create a new node that contains
	   Node i and Node j as it's left
	   and right variable. */
    private Node connectNodes(ArrayList<Node> nodes, int i, int j)
    {
    	//Invalid Node Array
    	if(nodes.size() < 2)
    		return null;

    	//Get The Nodes
    	Node n1 = nodes.get(i);
		Node n2 = nodes.get(j);

		//Combined Frequency of Two Nodes
		int totalFreq = n1.getFrequency() + n2.getFrequency();

		//Create New Node
		Node newNode = new Node(totalFreq);

		//Connect The Nodes
		newNode.setLeft(n1);
		newNode.setRight(n2);
    	
    	return newNode;
    }

	//Accessor Methods
	public HashMap<Character, String> getEncodings() { return this.encodings; }
	public String getCompressedBits()				 { return this.compressedBits; }
	public FrequencyTable getTable()				 { return this.freqTable; }
}
