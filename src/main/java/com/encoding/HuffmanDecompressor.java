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
        String str = "";
        String result = "";

        for (byte data : datas) {
            //get all the bit of the byte
            for(int i = 7; i >= 0; i-- ){
                byte byt = 1;
                byt = (byte) (byt << i);
                byte tmp = (byte) (data & byt);
                //si tmp != 0 on sait que la valeur sur ce bit est 1 sinon 0
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
