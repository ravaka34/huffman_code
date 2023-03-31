package com.encoding;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanDecompressor {

    public HashMap<String, Character> reverseDico( HashMap<Character, CharEncoding> dico){
        HashMap<String, Character> reverseDico = new HashMap<>();

        for (Map.Entry<Character, CharEncoding> entry : dico.entrySet()
             ) {
            reverseDico.put(entry.getValue().getRepresentation(), entry.getKey());
        }
        return reverseDico;
    }

    public String decompress() throws IOException {
        HashMap<String, Character> reverseDico = reverseDico(HuffmanTree.deserializeDico());
        byte[] datas = FileUtil.readBytes("file/compress.bin");
        int size = datas.length;
        int indexToStop = datas[size-1];
        String str = "";
        String result = "";

        for (int j = 0; j < size - 1; j++ ) {
            byte data = datas[j];
            //get all the bit of the byte
            for(int i = 7; i >= 0; i--){
                if(j == size - 2 && i == indexToStop){break;}
                byte byt = 1;
                byt = (byte) (byt << i);
                byte tmp = (byte) (data & byt);
                //if tmp != 0 it means that the value of the bit on i is 1 otherwise it is 0
                str = str + (tmp != 0 ? "1" : "0");
                if(reverseDico.containsKey(str)){
                    result += reverseDico.get(str);
                    str = "";
                }
            }
        }
        return result;
    }
}
