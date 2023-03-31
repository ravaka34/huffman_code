package com.encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuffmanEncoding {

    private HuffmanTree tree;
    private String text;
    private int textSize;
    public HuffmanEncoding (String text){
        this.text = text;
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
        FileUtil.writeBytes(getCompressedBytes(), "file/compress.bin");
        getTree().serializeDico();
    }


    public HuffmanTree getTree(){
        if(this.tree == null){
            this.tree =  new HuffmanTree(getLetterOccurence(), textSize);
        }
        return this.tree;
    }

}
