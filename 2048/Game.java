import java.awt.GraphicsConfiguration;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements ActionListener,
                                            MouseListener {
	
    static GraphicsConfiguration gc;
    
    private GameBoard gb;
    private Settings st;

    private TrialPane tp;

    private CommandManager commandManager;

    public Game() {
        setTitle("2048");


        /*JLayeredPane basePlatform = new JLayeredPane();
        basePlatform.setPreferredSize(new Dimension(500, 500));
        basePlatform.addMouseListener(this);
        
        Point origin1 = new Point(50, 50);
        Point origin2 = new Point(300, 300);*/

        //this.gb = new GameBoard("GameBoard", Color.yellow, origin1);
        //this.st = new Settings("Settings", Color.black, origin2);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();

        this.setBackground(Color.WHITE);

        this.setLocation(((width/2)- 400),100);
        this.setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        tp = new TrialPane(width);
        tp.setOpaque(true);
        this.setContentPane(tp);

        commandManager = new CommandManager();
        
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
        if(this.gb.checkInsideGB(x,y)) {
            System.out.println("Inside GameBoard");
        }
        else if (this.st.checkInsideSetting(x,y)) {
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

    //Handle user interaction with the check box and combo box.
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        /*if (ON_TOP_COMMAND.equals(cmd)) {
            if (onTop.isSelected())
                layeredPane.moveToFront(dukeLabel);
            else
                layeredPane.moveToBack(dukeLabel);

        } else if (LAYER_COMMAND.equals(cmd)) {
            int position = onTop.isSelected() ? 0 : 1;
            layeredPane.setLayer(dukeLabel,
                                 position,
                                 layerList.getSelectedIndex());
        }*/
    }

	public static void main(String[] args){
        Game frame= new Game();
        //Settings panel2 = new Settings();

        //frame.add(panel2);
	}
}
