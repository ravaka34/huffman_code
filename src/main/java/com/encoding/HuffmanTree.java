package com.encoding;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class HuffmanTree  {
    private Node root;

    private List<Node> leaves;

    private Integer textSize;

    private HashMap<Character, CharEncoding> charEncoding;

    private static final String RIGHT_PATH = "1";

    private static final String LEFT_PATH = "0";


    public HuffmanTree(HashMap<Character, Integer> leaves, Integer textSize) {
        this.textSize = textSize;
        createTree(leaves);
    }

    private void createTree(HashMap<Character, Integer> leaves) {
        AvalaibleNodes avalaibleNodes = new AvalaibleNodes(leaves);
        this.leaves = new ArrayList<>(avalaibleNodes.getNodes());
        while(true){
            Node[] nodes = avalaibleNodes.getNextToSum();
            Node node = new Node('+', nodes[0].getOccurence() + nodes[1].getOccurence());
            node.setLeft(nodes[0]);
            node.setRight(nodes[1]);
            nodes[0].setParent(node);
            nodes[1].setParent(node);
            if(node.getOccurence() == textSize){
                root = node;
                break;
            }
            avalaibleNodes.add(node);
        }
    }

    public String getCharEncoding(Node leave){
        Node parent = leave.getParent();
        Node child = leave;
        String encoding = "";

        while(parent != null){
            encoding = ((parent.getLeft() == child) ? LEFT_PATH : RIGHT_PATH) + encoding;
            child = parent;
            parent = parent.getParent();
        }
        return encoding;
    }

    public HashMap<Character, CharEncoding> getCharEncoding(){
        if(charEncoding == null){
            HashMap<Character, CharEncoding> dictionnary = new HashMap<>();
            for (Node leave: leaves) {
                dictionnary.put(leave.getCharacter(),
                        new CharEncoding(getCharEncoding(leave), leave.getCharacter()));
                System.out.println("Encoding of "+leave.getCharacter()+" "+leave.getOccurence()+" is "+
                        dictionnary.get(leave.getCharacter()).representation);
            }
            this.charEncoding = dictionnary;
        }
        return this.charEncoding;
    }

    public void serializeDico(){
        try(FileOutputStream fout=new FileOutputStream("file/dico.bin")){
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(this.getCharEncoding());
            out.flush();
        }catch(Exception e){System.out.println(e);}
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {
        int bitTest = 0b1000;
        System.out.println(Integer.toBinaryString(Integer.reverse(bitTest)));
    }
}
