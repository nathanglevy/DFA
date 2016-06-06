package DFA;

import java.util.ArrayList;
import java.util.List;

public class CodeLine {
    private LineType type;
    private Integer lineNumber;
    private List<Integer> nextNumber;
    CodeLine(LineType setType, Integer lineNumber) {
        this.type = setType;
        this.lineNumber = lineNumber;
        this.nextNumber = new ArrayList<>();
        this.nextNumber.add(lineNumber+1);
    }

    CodeLine(LineType setType, Integer lineNumber, Integer jumpNumber) {
        this.type = setType;
        this.lineNumber = lineNumber;
        this.nextNumber = new ArrayList<>();
        if (this.type.equals(LineType.BRANCH))
            this.nextNumber.add(lineNumber + 1);
        this.nextNumber.add(jumpNumber);
        if ((this.nextNumber.size() > 1) && (!this.type.equals(LineType.BRANCH))) {
               throw new RuntimeException("Cannot create a codeline with more than 1 destination but not branch type");
        }
    }

    public LineType getType() {
        return type;
    }
    public Integer getLineNumber() {
        return lineNumber;
    }
    public List<Integer> getNextNumber() {
        return new ArrayList<>(nextNumber);
    }
}
