package Testing;

import DFA.CodeBlock;
import DFA.CodeBlockGraph;
import DFA.CodeLineSplitter;
import DFA.LineType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CodeLineSplitterTests {
    @Test
    public final void makeCodeLineSplitter() {
        CodeLineSplitter lineSplitter = new CodeLineSplitter();
        lineSplitter.addLine(LineType.BLANK);
        lineSplitter.addLine(LineType.BLANK);
        lineSplitter.addLine(LineType.BLANK);
        lineSplitter.addLine(LineType.BLANK);
        lineSplitter.addLine(LineType.BLANK);
        lineSplitter.addLine(LineType.BLANK);
        Assert.assertEquals(lineSplitter.getCurrentLineCount(), 6);
        Assert.assertEquals(lineSplitter.getCurrentLine(), 6);
    }

    @Test
    public final void makeCodeLineSplitBlocks() {
        CodeLineSplitter lineSplitter = new CodeLineSplitter();
        lineSplitter.addLine(LineType.BLANK); //1 <-start
        lineSplitter.addLine(LineType.BLANK); //1 <- branched to
        lineSplitter.addLine(LineType.BRANCH, 1); //2 #END
        lineSplitter.addLine(LineType.BLANK); //1/3 <- branched to
        lineSplitter.addLine(LineType.BLANK); //2/3
        lineSplitter.addLine(LineType.BRANCH, 7); //3/3 #END
        lineSplitter.addLine(LineType.BLANK); //1 <-
        lineSplitter.addLine(LineType.BLANK); //1 <-
        lineSplitter.addLine(LineType.HALT);
        CodeBlockGraph codeBlockGraph = lineSplitter.analyzeCode();
        Assert.assertEquals(codeBlockGraph.getBlockList().size(), 6);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B0").getSize(), 1);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B1").getSize(), 2);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B2").getSize(), 3);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B3").getSize(), 1);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B4").getSize(), 2);
    }


}
