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

    void addLine(LineType lineType, Integer lineNumber) {
        codeLines.add(new CodeLine(lineType, lineNumber));
    }

    public void addLine(LineType lineType, Integer lineNumber, Integer jumpNumber) {
        codeLines.add(new CodeLine(lineType, lineNumber, jumpNumber));
    }

    public int getSize() {
        return codeLines.size();
    }

    public List<CodeLine> getCodeLines() {
        return new ArrayList<>(codeLines);
    }
}
