package DFA;

import java.util.ArrayList;
import java.util.List;

public class CodeLine {
    private LineType type;
    private Integer lineNumber;
    private List<Integer> nextNumber;
    private List<CodeAction> codeActionList;

    public CodeLine(LineType setType, List<CodeAction> codeActionList, Integer lineNumber) {
        this.type = setType;
        this.lineNumber = lineNumber;
        this.nextNumber = new ArrayList<>();
        if (codeActionList != null)
            this.codeActionList =  new ArrayList<>(codeActionList);
        else
            this.codeActionList =  new ArrayList<>();

        if ((!this.getType().equals(LineType.HALT)) || (!this.getType().equals(LineType.RETURN)))
            this.nextNumber.add(lineNumber+1);
    }

    CodeLine(LineType setType, List<CodeAction> codeActionList, Integer lineNumber, Integer jumpNumber) {
        this.type = setType;
        this.lineNumber = lineNumber;
        this.nextNumber = new ArrayList<>();
        if (codeActionList != null)
            this.codeActionList =  new ArrayList<>(codeActionList);
        else
            this.codeActionList =  new ArrayList<>();

        if (this.type.equals(LineType.BRANCH))
            this.nextNumber.add(lineNumber + 1);
        this.nextNumber.add(jumpNumber);
        if ((this.nextNumber.size() > 1) && (!this.type.equals(LineType.BRANCH))) {
               throw new RuntimeException("Cannot create a codeline with more than 1 destination but not branch type");
        }
    }

    LineType getType() {
        return type;
    }
    Integer getLineNumber() {
        return lineNumber;
    }
    List<Integer> getNextNumber() {
        return new ArrayList<>(nextNumber);
    }
    List<CodeAction> getCodeActionList() { return new ArrayList<>(codeActionList); }
}
