package Testing;

import DFA.*;
import DFA.CodeAction.ActionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockEntryTests {
    @Test
    public final void createEntryTest() {
        List<CodeAction> codeActionList = new ArrayList<>();
        codeActionList.add(new CodeAction(ActionType.READ,"R1"));
        BlockEntry blockEntry1 = new BlockEntry();
        CodeLine codeLine1 = new CodeLine(LineType.BLANK,codeActionList ,0);
        blockEntry1.updateEntry(codeLine1);

        List<CodeAction> codeActionList2 = new ArrayList<>();
        //codeActionList2.add(new CodeAction(ActionType.WRITE,"R1"));
        BlockEntry blockEntry2 = new BlockEntry();
        blockEntry2.updateEntry(codeLine1);
        CodeLine codeLine2 = new CodeLine(LineType.BLANK,codeActionList2 ,1);
        blockEntry2.updateEntry(codeLine2);
        blockEntry2.updateEntry(codeLine2);

        Assert.assertEquals(blockEntry1.mergeEntryWith(blockEntry2).getEntries().get("R1"), new Integer(0));

    }

    @Test
    public final void testParsing() {
        List<CodeLine> code = FileParser.parseFile("C:\\Users\\nglevy\\IdeaProjects\\DFA\\src\\Testing\\codeTest.txt");
        System.out.println(code);
        CodeBlockGraph codeGraph = CodeBlockGraphGenerator.analyzeCode(code);
        for (CodeBlock codeBlock : (codeGraph.getBlockList())) {
            List<CodeLine> codeLinesList = new ArrayList<>(codeBlock.getCodeLines());
            Collections.reverse(codeLinesList);
            BlockEntry blockEntry = new BlockEntry();
            for (CodeLine codeLine : codeLinesList) {
                blockEntry.updateEntry(codeLine);
            }
            System.out.print(blockEntry.getEntries());
        }
    }

}
