package src.huffman;

import src.utilities.Node;

import java.util.ArrayList;

public class FrequencyTable extends ArrayList<Node>
{
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
    }

    public void add(char c, int freq)
    {
        Node n = new Node(c, freq);

        this.add(n);
    }

    public void increment(char c)
    {
        if(!exists(c))
            throw new IllegalArgumentException("Invalid Character");

        for(Node n : this)
        {
            if(n.getChar() == c)
                n.increment();
        }

    }

    private boolean exists(char c)
    {
        for(Node n : this)
        {
            char tmp = n.getChar();

            if(tmp == c)
                return true;
        }

        return false;
    }

    public int getFrequency(char c)
    {

        for(Node n : this)
        {
            if(n.getChar() == c)
                return n.getFrequency();
        }

        //If Character Isn't In The Table
        return 0;

    }

    //Frequency of Character at Index i
    public Integer getFrequencyAt(int i)
    {
        if(i >= size())
            throw new IllegalArgumentException("Invalid Index");

        return this.get(i).getFrequency();
    }

    //Frequency of Character at Index i
    public Character getCharAt(int i)
    {
        if(i >= size())
            throw new IllegalArgumentException("Invalid Index");

        return this.get(i).getChar();
    }
}