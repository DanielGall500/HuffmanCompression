package src.utilities;

public class Node
{
    private char character;
    private int  frequency;

    private Node left;
    private Node right;

    private boolean hasChar;

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

    public void increment()
    {
        this.frequency++;
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
