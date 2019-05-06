import javax.swing.JPanel;
import java.awt.*;
import java.util.Stack;

public class CommandManager {

    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();


 	//Executes a Command instance and adds it to the list of undos that are available.
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    //Checks if there is at least one undoable Command available on the undo list.
    public boolean isUndoAvailable() {
        return !undoStack.empty();
    }

    /** Undoes the next available command to undo.
     * If four commands were executed, the undo operations for those commands will
     * happen in reverse order with four calls to this method.
     */
     public void undoCommand() {
     	assert(!undoStack.empty());
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

    //Checks if there is at least one undoable Command available on the undo list.
    public boolean isRedoAvailable() {
		return !redoStack.empty();
	}

	//Redoes the next available command to redo.
   	public void redoCommand() {
        assert(!redoStack.empty());
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }
}
