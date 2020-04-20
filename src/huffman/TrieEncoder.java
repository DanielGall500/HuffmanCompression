package src.huffman;

import src.utilities.Node;

import java.util.HashMap;

public class TrieEncoder {

    HashMap<Character, String> encodings;

    public TrieEncoder(Node root)
    {
        encodings = new HashMap<>();

        assignEncodings(root, "");
    }

    public HashMap<Character, String> getEncodings()
    {
        return this.encodings;
    }

    private void assignEncodings(Node root, String encoding)
    {
        Node l, r;
        String newEnc;

        if(root.hasLeft())
        {
            l = root.getLeft();
            newEnc = encoding + "0";

            if(l.hasChar())
                encodings.put(l.getChar(), newEnc);

            assignEncodings(l, newEnc);
        }

        if(root.hasRight())
        {
            r = root.getRight();
            newEnc = encoding + "1";

            if(r.hasChar())
                encodings.put(r.getChar(), newEnc);

            assignEncodings(r, newEnc);
        }
    }
}
