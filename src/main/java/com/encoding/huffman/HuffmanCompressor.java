package com.encoding.huffman;

import com.encoding.FileUtil;
import com.encoding.huffman.tree.CharEncoding;
import com.encoding.huffman.tree.HuffmanTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuffmanCompressor {

    private HuffmanTree tree;
    private String text;
    private int textSize;
    private String compressPath;
    private String dicoPath;

    public HuffmanCompressor(String textPath, String compressPath, String dicoPath) throws IOException {
        this.compressPath = compressPath;
        this.dicoPath = dicoPath;
        this.text = FileUtil.readFile(textPath);
        this.textSize = text.length();
    }

    public HuffmanCompressor(String textPath) throws IOException {
        this.compressPath = "file/compress.bin";
        this.dicoPath = "file/dico.bin";
        this.text = FileUtil.readFile(textPath);
        this.textSize = text.length();
    }

    public HashMap<Character, Integer> getLetterOccurence(){
        char[] chars = text.toCharArray();
        HashMap<Character, Integer> numbers = new HashMap<>();

        for (char character : chars
             ) {
            if(!numbers.containsKey(character)){
                numbers.put(character, 1);
            }else{
                numbers.put(character, numbers.get(character) + 1);
            }
        }
        return numbers;
    }

    public List<Byte> getCompressedBytes(){
        HashMap<Character, CharEncoding> dictionary = getTree().getCharEncoding();
      List<Byte> bytes = new ArrayList<>();
      byte part = 0;
      int shiftIndex = 7;

      for(char character : text.toCharArray()){
        Object[] objs = dictionary.get(character).writeIn(bytes, part, shiftIndex);
        part = (byte) objs[0];
        shiftIndex = (int) objs[1];
      }
      bytes.add(part);
        System.out.println("Last part "+(shiftIndex));
      bytes.add((byte) (shiftIndex));
      return bytes;
    }

    public void compress(){
        System.out.println("Text to compress: "+ text);
        FileUtil.writeBytes(getCompressedBytes(), compressPath);
        getTree().serializeDico(dicoPath);
    }


    public HuffmanTree getTree(){
        if(this.tree == null){
            this.tree =  new HuffmanTree(getLetterOccurence(), textSize);
        }
        return this.tree;
    }

}
