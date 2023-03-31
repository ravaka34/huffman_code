package com.encoding;

import java.io.Serializable;
import java.util.List;

public class CharEncoding implements Serializable {
    public String representation;

    public char character;

    public CharEncoding(String representation, char character) {
        this.representation = representation;
        this.character = character;
    }

    public Object[] writeIn(List<Byte> bytes, byte part, Integer shiftIndex){
        Object[] objs = new Object[2];
        for(char character : representation.toCharArray()){
            // need to create a new part
            if(shiftIndex == -1){
              bytes.add(part);
              part = 0;
              shiftIndex = 7;
            }
            // if char = 0 don t need to write anything
            if(character == '1'){
                byte newPart = 1;
                newPart = (byte) (newPart << shiftIndex);
                part = (byte) (part | newPart);
            }
            shiftIndex --;
        }
        objs[0] = part;
        objs[1] = shiftIndex;
        return objs;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
