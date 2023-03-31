package com.encoding;

import java.util.*;

public class AvalaibleNodes {
    private List<Node> nodes;

    public AvalaibleNodes(HashMap<Character, Integer> leaves){
        getNodesFrom(leaves);
    }

    private void getNodesFrom(HashMap<Character, Integer> leaves){
        nodes = new ArrayList<>();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(leaves.entrySet());
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for (Map.Entry<Character, Integer> entry : list) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
    }

    public Node[] getNextToSum(){
        Node[] results = new Node[2];
        results[0] = nodes.get(0);
        results[1] = nodes.get(1);
        //it is no more available
        nodes.remove(0);
        nodes.remove(0);
        return results;
    }

    public void add(Node node){
        for (int i = 0;i < nodes.size() ; i++) {
            if(nodes.get(i).getOccurence() >= node.getOccurence()){
                nodes.add(i, node);
                return;
            }
        }
        nodes.add(nodes.size(), node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
