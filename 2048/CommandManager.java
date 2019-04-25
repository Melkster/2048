import javax.swing.JPanel;
import java.awt.*;

public class CommandManager {
 
    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();

    //private Command lastCommand;
    //private Command lastCommandUndone;
 
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

     public void undoCommand() {

    
    }

	public void redoCommand() {

	    
	    }
   
 