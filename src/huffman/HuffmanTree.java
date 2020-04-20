package src.huffman;

import src.algorithms.SelectionSort;
import src.utilities.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HuffmanTree
{

    private FrequencyTable freqTable;

    private final HashMap<Character, String> encodings;

    private Node ROOT;

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
    }

    public HashMap<Character, String> getEncodings()
    {
    	return this.encodings;
    }


    private Node connectNodes(ArrayList<Node> nodes, int i, int j)
    {
    	if(nodes.size() < 2)
    		return null;

    	Node n1 = nodes.get(i);
		Node n2 = nodes.get(j);

		int totalFreq = n1.getFrequency() + n2.getFrequency();

		Node newNode = new Node(totalFreq);

		newNode.setLeft(n1);
		newNode.setRight(n2);
    	
    	return newNode;
    }

    public FrequencyTable getTable()
    {
		return this.freqTable;
    }

    public Node getRoot()
    {
    	return this.ROOT;
    }
}
