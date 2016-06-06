package DFA;


import java.util.*;

public class CodeLineSplitter {
    private List<CodeLine> codeLines;
    private Integer currentLine;
    public CodeLineSplitter() {
        codeLines = new ArrayList<>();
        currentLine = 0;
    }
    public void addLine(LineType lineType) {
        codeLines.add(new CodeLine(lineType, currentLine++));
    }
    public void addLine(LineType lineType, Integer jumpLine) {
        codeLines.add(new CodeLine(lineType, currentLine++, jumpLine));
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
                case BRANCH:
                case JUMP:
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
                        break;
                    case HALT: break;
                    default: newGraph.connectBlocks(currentBlock.getName(), "B" + blockNumber.get(currentLineNumber+1));
                        break;
                }
            }
        }

        return newGraph;
    }
}
