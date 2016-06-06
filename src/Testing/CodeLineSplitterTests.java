package Testing;

import DFA.CodeBlock;
import DFA.CodeBlockGraph;
import DFA.CodeLineSplitter;
import DFA.LineType;
import org.junit.Assert;
import org.junit.Test;
import Graph.*;
import java.util.ArrayList;
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

    @Test
    public final void makeCodeLineSplitWithJAL() {
        CodeLineSplitter lineSplitter = new CodeLineSplitter();
        lineSplitter.addLine(LineType.BLANK); //1 <-start B0            //0
        lineSplitter.addLine(LineType.BLANK); //1 <- branched to B1     //1
        lineSplitter.addLine(LineType.BRANCH, 1); //2 #END of B1        //2
        lineSplitter.addLine(LineType.BLANK); //1/3 <- branched to B2   //3
        lineSplitter.addLine(LineType.BLANK); //2/3 B2                  //4
        lineSplitter.addLine(LineType.BRANCH, 6); //3/3 #END B2         //5
        lineSplitter.addLine(LineType.JAL, 11); //1 <- B3               //6
        lineSplitter.addLine(LineType.BLANK); //1 <- B4                 //7
        lineSplitter.addLine(LineType.JAL, 11); //1 <- B4               //8
        lineSplitter.addLine(LineType.BLANK); //1 <- B5                 //9
        lineSplitter.addLine(LineType.HALT); //<- B5                    //10
        lineSplitter.addLine(LineType.BLANK); //1/4 <- B6               //11
        lineSplitter.addLine(LineType.BLANK); //2/4 <- B6               //12
        lineSplitter.addLine(LineType.BLANK); //3/4 <- B6               //13
        lineSplitter.addLine(LineType.RETURN); //4/4 <- B6              /14

        CodeBlockGraph codeBlockGraph = lineSplitter.analyzeCode();
        Assert.assertEquals(codeBlockGraph.getBlockList().size(), 8);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B0").getSize(), 1);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B1").getSize(), 2);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B2").getSize(), 3);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B3").getSize(), 1);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B4").getSize(), 2);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B5").getSize(), 2);
        Assert.assertEquals(codeBlockGraph.getBlockByName("B6").getSize(), 4);
        //check connectivity
        List<String> testList = new ArrayList<>();
        testList.add("B5");
        testList.add("B4");
        Assert.assertEquals(codeBlockGraph.getConnectedCodeBlockNames("B6").containsAll(testList),true);
        Assert.assertEquals(testList.containsAll(codeBlockGraph.getConnectedCodeBlockNames("B6")),true);

        //compare graphs function
        Graph testGraph = new Graph();
        testGraph.addVertex("START");
        for (int i = 0; i < 7; i++) {
            testGraph.addVertex("B"+i);
        }
        testGraph.connectVertices("B0","B1");
        testGraph.connectVertices("B1","B1");
        testGraph.connectVertices("B1","B2");
        testGraph.connectVertices("B2","B3");
        testGraph.connectVertices("B3","B6");
        testGraph.connectVertices("B4","B6");
        testGraph.connectVertices("B6","B4");
        testGraph.connectVertices("B6","B5");
        Assert.assertEquals(testGraph.isIdenticalTo(codeBlockGraph.getBlockGraphRep()),true);
        testGraph.connectVertices("B6","B6");
        Assert.assertEquals(testGraph.isIdenticalTo(codeBlockGraph.getBlockGraphRep()),false);
    }


}
