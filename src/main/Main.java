package src.main;

import src.huffman.HuffmanTree;

import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args)
    {
        String test = "loiosoeelllllleur";

        HuffmanTree tree = new HuffmanTree(test);

        System.out.println(tree.getCompressedOutput());
    }
}
