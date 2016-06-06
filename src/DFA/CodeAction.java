package DFA;

public class CodeAction {
    enum ActionType {WRITE,READ}
    ActionType actionType;
    String destination;
    CodeAction(ActionType actionType, String destination) {
        this.actionType = actionType;
        this.destination = destination;
    }
}
