package DFA;


import java.util.*;

public class CodeBlockGraphGenerator {
    private List<CodeLine> codeLines;
    private Integer currentLine;
    public CodeBlockGraphGenerator() {
        codeLines = new ArrayList<>();
        currentLine = 0;
    }
    public void addLine(LineType lineType, List<CodeAction> codeActionList) {
        codeLines.add(new CodeLine(lineType, codeActionList, currentLine++));
    }
    public void addLine(LineType lineType, List<CodeAction> codeActionList, Integer jumpLine) {
        codeLines.add(new CodeLine(lineType, codeActionList, currentLine++, jumpLine));
    }
    public void addLine(LineType lineType) {
        codeLines.add(new CodeLine(lineType, null, currentLine++));
    }
    public void addLine(LineType lineType, Integer jumpLine) {
        codeLines.add(new CodeLine(lineType, null, currentLine++, jumpLine));
    }
    //not needed? think of refactoring out
    public int getCurrentLineCount() {
        return codeLines.size();
    }
    public int getCurrentLine() {
        return currentLine;
    }
    public CodeBlockGraph analyzeCode() {
        Map<Integer,Integer> blockNumber = new HashMap<>();
        Set<Integer> isBlockHead = new HashSet<>();
        //add line 0 as head
        isBlockHead.add(0);
        for (CodeLine codeLine : codeLines) {
            switch (codeLine.getType()) {
                case BLANK: break;
                case JAL:   isBlockHead.add(codeLine.getLineNumber()+1); //JAL is special
                case BRANCH:
                case JUMP:
                case HALT:
                case RETURN:
                    for (Integer nextLineNumber : codeLine.getNextNumber()) {
                        isBlockHead.add(nextLineNumber);
                    }
                    break;
                case SET: break;
                case UNSET: break;
                default: break;
            }
        }

        int currentBlockNumber = -1;
        CodeBlockGraph newGraph = new CodeBlockGraph();
        for (CodeLine codeLine : codeLines) {
            if ( isBlockHead.contains(codeLine.getLineNumber()) ) {
                currentBlockNumber++;
                newGraph.addBlock("B" + currentBlockNumber);
            }
            blockNumber.put(codeLine.getLineNumber(),currentBlockNumber);
        }

        currentBlockNumber = -1;
        CodeBlock currentBlock = newGraph.addBlock("START");
        for (CodeLine codeLine : codeLines) {
            if ( isBlockHead.contains(codeLine.getLineNumber()) ) {
                currentBlockNumber++;
                currentBlock = newGraph.getBlockByName("B" + currentBlockNumber);
            }
            currentBlock.addLine(codeLine);
            int currentLineNumber = codeLine.getLineNumber();
            if ( isBlockHead.contains(currentLineNumber+1)) {
                switch (codeLine.getType()) {
                    case BRANCH:
                    case JUMP:
                        for (Integer nextLine : codeLine.getNextNumber()) {
                            newGraph.connectBlocks(currentBlock.getName(), "B" + blockNumber.get(nextLine));
                        }
                    case JAL:
                    case RETURN:
                    case HALT: break;
                    default: newGraph.connectBlocks(currentBlock.getName(), "B" + blockNumber.get(currentLineNumber+1));
                        break;
                }
            }
        }

        //create returnList map for RETURN jumps
        Map<String,List<String>> returnList = new HashMap<>();
        for (CodeLine codeLine : codeLines) {
            if (codeLine.getType().equals(LineType.JAL)) {
                for (Integer nextNumber : codeLine.getNextNumber()) {
                    if (!returnList.containsKey("B" + blockNumber.get(nextNumber))) {
                        returnList.put("B" + blockNumber.get(nextNumber), new ArrayList<String>());
                    }
                    returnList.get("B" + blockNumber.get(nextNumber)).add("B" + blockNumber.get(codeLine.getLineNumber()+1));
                }
            }
        }

        //propogate the return links
        for (String returnBlock : returnList.keySet()) {
            List<String> connectedVertices = newGraph.connectionGraph.getConnectedVerticesNames(returnBlock);
            for (String vertex : connectedVertices) {
                List<CodeLine> codeLines = newGraph.getBlockByName(vertex).getCodeLines();
                for (CodeLine codeLine : codeLines) {
                    if (codeLine.getType().equals(LineType.RETURN)) {
                        for (String destination : returnList.get(returnBlock)) {
                            newGraph.connectBlocks(vertex,destination);
                        }
                    }
                }
            }
        }

        //now link returns and jals
        for (CodeLine codeLine : codeLines) {
            if (codeLine.getType().equals(LineType.JAL)) {
                for (Integer nextNumber : codeLine.getNextNumber()) {
                    newGraph.connectBlocks("B" + blockNumber.get(codeLine.getLineNumber()), "B" + blockNumber.get(nextNumber));
                }
            }
        }
        return newGraph;
    }
}
