package DFA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockEntry {
    private Map<String, Integer> entryData;
    public BlockEntry() {
        entryData = new HashMap<>();
    }

    public Map<String, Integer> getEntries() {
        return new HashMap<>(entryData);
    }

    public void updateEntry(CodeLine codeline) {
        List<CodeAction> codeActions = codeline.getCodeActionList();
        //increment all saved entries
        for (String fieldName : entryData.keySet()) {
            entryData.put(fieldName,entryData.get(fieldName)+1);
        }
        for (CodeAction codeAction : codeActions) {
            System.out.println("CodeActionHere");
            if (codeAction.getActionType().equals(CodeAction.ActionType.READ)) {
                setEntry(codeAction.getField());
                System.out.println("setEntry " + codeAction.getField());
            }
            if (codeAction.getActionType().equals(CodeAction.ActionType.WRITE)) {
                unsetEntry(codeAction.getField());
                System.out.println("unsetEntry" + codeAction.getField());
            }
        }
    }

    public BlockEntry mergeEntryWith(BlockEntry blockEntry) {
        List<BlockEntry> blockEntries = new ArrayList<>();
        blockEntries.add(blockEntry);
        blockEntries.add(this);
        return mergeEntries(blockEntries);
    }

    public static BlockEntry mergeEntries(List<BlockEntry> blockEntries) {
        BlockEntry newBlock = new BlockEntry();
        for (BlockEntry blockEntry : blockEntries) {
            for (String field : blockEntry.entryData.keySet()) {
                if (newBlock.entryData.containsKey(field)) {
                    if (newBlock.entryData.get(field) > blockEntry.entryData.get(field)) {
                        newBlock.entryData.put(field,blockEntry.entryData.get(field));
                    }
                } else {
                    newBlock.entryData.put(field,blockEntry.entryData.get(field));
                }
            }
        }
        return newBlock;
    }

    private void setEntry(String entryName) {
        entryData.put(entryName, 0);
    }

    private void unsetEntry(String entryName) {
        if (entryData.containsKey(entryName))
            entryData.remove(entryName);
    }
}
