package src.main;

import src.huffman.HuffmanTree;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //--BEFORE COMPRESSION--

        String INPUT = "Quarantine";

        System.out.println("Input String: " + INPUT + "\n");

        System.out.println("Before Compression:");
        for(byte b : INPUT.getBytes())
        {
            System.out.print(Integer.toBinaryString(b));
        }
        System.out.println();

        //--COMPRESSION--

        //Pass A String To The Tree
        HuffmanTree tree = new HuffmanTree(INPUT);

        //Retrieve The Bits After Compression
        String compressedBits = tree.getCompressedBits();
        System.out.println("\nAfter Compression:\n" + compressedBits + "\n");

        //--DECOMPRESSION--

        String decompression = tree.decompressBits(compressedBits);

        System.out.println("Pass Bits Through Decompression:");
        System.out.println(decompression);
    }
}
