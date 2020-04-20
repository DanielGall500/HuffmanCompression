package src.main;

import src.huffman.HuffmanTree;

import java.util.HashMap;
import java.util.Set;

public class Main {
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
