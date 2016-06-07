package DFA;

public class CodeAction {
    public enum ActionType {WRITE,READ}
    private ActionType actionType;
    private String field;
    public CodeAction(ActionType actionType, String field) {
        this.actionType = actionType;
        this.field = field;
    }

    ActionType getActionType() {
        return actionType;
    }

    String getField() {
        return field;
    }
}
