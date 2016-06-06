package DFA;

import java.util.ArrayList;
import java.util.List;
import Graph.*;

public class CodeBlockGraph {
    private List<CodeBlock> codeBlockList;
    Graph connectionGraph;
    public CodeBlockGraph() {
        codeBlockList = new ArrayList<>();
        connectionGraph = new Graph();
    }

    public CodeBlock addBlock(String blockName) {
        CodeBlock newBlock = new CodeBlock(blockName);
        if (doesBlockExist(blockName)) throw new RuntimeException("Cannot add block: " + blockName + " - block with same name already exists");
        codeBlockList.add(newBlock);
        connectionGraph.addVertex(blockName);
        return newBlock;
    }

    public boolean doesBlockExist(String blockName) {
        for (CodeBlock codeBlock : codeBlockList) {
            if (codeBlock.getName().equals(blockName)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesBlockExist(CodeBlock block) {
        for (CodeBlock codeBlock : codeBlockList) {
            if (codeBlock.equals(block)) {
                return true;
            }
        }
        return false;
    }

    public CodeBlock getBlockByName(String blockName) {
        for (CodeBlock codeBlock : codeBlockList) {
            if (codeBlock.getName().equals(blockName)) {
                return codeBlock;
            }
        }
        throw new RuntimeException("Block: " + blockName + " does not exist");
    }

    public List<CodeBlock> getBlockList() {
        return new ArrayList<>(codeBlockList);
    }

    public List<String> getBlockNameList() {
        List<String> blockNames = new ArrayList<>();
        for (CodeBlock codeBlock : codeBlockList) {
            blockNames.add(codeBlock.getName());
        }
        return blockNames;
    }

    public void connectBlocks(String blockNameA, String blockNameB) {
        connectionGraph.connectVertices(blockNameA,blockNameB);
    }

    public List<String> getConnectedCodeBlockNames(String codeBlockName) {
        List<String> codeBlockNames = new ArrayList<>();
        for (String neighbours : connectionGraph.getNeighboursNames(codeBlockName)) {
            codeBlockNames.add(neighbours);
        }
        return codeBlockNames;
    }

    //TODO: fix this so that it COPIES the graph!
    public Graph getBlockGraphRep() {
        return connectionGraph;
    }
}
