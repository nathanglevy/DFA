package Testing;

import DFA.CodeBlock;
import DFA.CodeBlockGraph;
import DFA.CodeLine;
import DFA.LineType;
import Graph.Graph;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CodeBlockGraphTests {
    @Test(expected = RuntimeException.class)
    public final void addBlockTwice() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CFG.addBlock("Block1");
        CFG.addBlock("Block1");
    }

    @Test
    public final void doesCodeBlockExistByName() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CFG.addBlock("Block1");
        Assert.assertEquals(CFG.doesBlockExist("Block1"),true);
        Assert.assertEquals(CFG.doesBlockExist("Block2"),false);
    }

    @Test
    public final void doesCodeBlockExistByBlock() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CodeBlockGraph CFG2 = new CodeBlockGraph();
        CodeBlock codeBlock = CFG.addBlock("Block1");
        CodeBlock codeBlock2 = CFG2.addBlock("Block2");
        Assert.assertEquals(CFG.doesBlockExist("Block1"),true);
        Assert.assertEquals(CFG.doesBlockExist(codeBlock),true);
        Assert.assertEquals(CFG.doesBlockExist(codeBlock2),false);
    }

    @Test
    public final void getBlockByName() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CodeBlock codeBlock1 = CFG.addBlock("Block1");
        CodeBlock codeBlock2 = CFG.addBlock("Block2");
        Assert.assertEquals(CFG.getBlockByName("Block1"),codeBlock1);
        Assert.assertEquals(CFG.getBlockByName("Block2"),codeBlock2);
    }

    @Test
    public final void getBlockList() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CodeBlock codeBlock1 = CFG.addBlock("Block1");
        CodeBlock codeBlock2 = CFG.addBlock("Block2");
        List<CodeBlock> codeBlockList = new ArrayList<>();
        codeBlockList.add(codeBlock1);
        codeBlockList.add(codeBlock2);
        Assert.assertArrayEquals(codeBlockList.toArray(), CFG.getBlockList().toArray());
    }

    @Test
    public final void getBlockNameList() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CodeBlock codeBlock1 = CFG.addBlock("Block1");
        CodeBlock codeBlock2 = CFG.addBlock("Block2");
        List<String> codeBlockList = new ArrayList<>();
        codeBlockList.add(codeBlock1.getName());
        codeBlockList.add("Block2");
        Assert.assertArrayEquals(codeBlockList.toArray(), CFG.getBlockNameList().toArray());
    }

    @Test
    public final void makeSimpleBlockGraph() {
        CodeBlockGraph CFG = new CodeBlockGraph();
        CodeBlock codeBlock1 = CFG.addBlock("Block1");
        codeBlock1.addLine(LineType.BLANK, null, 0);
        codeBlock1.addLine(LineType.BLANK, null, 1);
    }
}
