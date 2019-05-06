import java.lang.*;
// Any number of import statements

public interface Command {
    public void execute();
    public void undo();
    public void redo();
}
