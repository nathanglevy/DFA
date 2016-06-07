package DFA;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public FileParser() {

    }

    public static List<CodeLine> parseFile(String path) {
        List<CodeLine> parsedCodeLines = new ArrayList<>();
        try {
            Scanner in = new Scanner(new FileReader(path));
            int currentLine = 0;
            while (in.hasNext()) {
                String line = in.nextLine();
                String fields[] = line.split(" ");
                List<CodeAction> codeLineActions = new ArrayList<>();
                LineType codeLineType = LineType.BLANK;
                switch (fields[0]) {
                    case "ADD2I":
                    case "SUB2I":
                        codeLineActions.add(new CodeAction(CodeAction.ActionType.WRITE, fields[1]));
                        if (isFieldRegister(fields[2]))
                            codeLineActions.add(new CodeAction(CodeAction.ActionType.READ, fields[2]));
                        if (isFieldRegister(fields[3]))
                            codeLineActions.add(new CodeAction(CodeAction.ActionType.READ, fields[3]));
                        codeLineType = LineType.RW;
                        break;
                    case "PRNTI":
                        if (isFieldRegister(fields[1]))
                            codeLineActions.add(new CodeAction(CodeAction.ActionType.READ, fields[1]));
                        codeLineType = LineType.READ;
                        break;
                    case "READI":
                        if (isFieldRegister(fields[1]))
                            codeLineActions.add(new CodeAction(CodeAction.ActionType.WRITE, fields[1]));
                        codeLineType = LineType.WRITE;
                        break;
                    default: break;
                }

                parsedCodeLines.add(new CodeLine(codeLineType, codeLineActions, currentLine++));
            }
        } catch(java.io.FileNotFoundException e) {
            throw new RuntimeException("File does not exist!");
        }
        return parsedCodeLines;
    }

    private static boolean isFieldRegister(String field) {
        return ((field.toCharArray()[0] == 'R') || (field.toCharArray()[0] == 'I'));
    }
}
