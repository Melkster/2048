package src;

import java.lang.*;

/*
*   Interface class to describe what can be done with a Command
*/
public interface Command {
    public void execute();
    public void undo();
    public void redo();
}
