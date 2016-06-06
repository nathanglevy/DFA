package DFA;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock {
    private List<CodeLine> codeLines;
    private String name;

    CodeBlock(String blockName) {
        codeLines = new ArrayList<>();
        name = blockName;
    }

    public String getName() {
        return this.name;
    }

    void addLine(CodeLine codeline) {
        codeLines.add(codeline);
    }

    public void addLine(LineType lineType, List<CodeAction> codeActionList, Integer lineNumber) {
        codeLines.add(new CodeLine(lineType, codeActionList, lineNumber));
    }

    public void addLine(LineType lineType, List<CodeAction> codeActionList, Integer lineNumber, Integer jumpNumber) {
        codeLines.add(new CodeLine(lineType, codeActionList, lineNumber, jumpNumber));
    }

    public int getSize() {
        return codeLines.size();
    }

    public List<CodeLine> getCodeLines() {
        return new ArrayList<>(codeLines);
    }
}
