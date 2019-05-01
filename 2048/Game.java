import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements MouseListener {
	
    static GraphicsConfiguration gc;
    
    private GameBoard gb;

    private CommandManager commandManager;

    public Game() {
        setTitle("2048");
        addMouseListener(this);
        this.gb = new GameBoard();
        this.add(gb);

        commandManager = new CommandManager();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();

        this.setLocation(((width/2)- gb.getGameBoardSize()),100);
        this.setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    public GameBoard getGameBoard() {
        return this.gb;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
        if(this.gb.checkInsideGB(x,y)) {
            System.out.println("Inside GameBoard");
        }
        else if (this.gb.checkInsideSetting(x,y)) {
            System.out.println("Inside Setting");
        }
        else {
            System.out.println("Outside GameBoard and Setting");
        }
    }
 
    //Checks if undo is available in CommandManager.
    public boolean isUndoAvailable() {
        return commandManager.isUndoAvailable();
    }

    //Calls undo function in CommandManager.
    public void undo() {
        commandManager.undoCommand();
    }

    //Checks if redo is available in CommandManager.
    public boolean isRedoAvailable() {
        return commandManager.isRedoAvailable();
    }

    //Calls redo function in CommandManager.
    public void redo() {
        commandManager.redoCommand();
    }
    

    @Override
    public void mouseEntered(MouseEvent e) {}
 
    @Override
    public void mouseExited(MouseEvent e) {}
 
    @Override
    public void mousePressed(MouseEvent e) {}
 
    @Override
    public void mouseReleased(MouseEvent e) {}

	public static void main(String[] args){
        Game frame= new Game();
        Settings panel2 = new Settings();

        //frame.add(panel2);
	}
}
