import java.awt.GraphicsConfiguration;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

import src.*;
/*
*   Class Game which is the main class for the game 2048
*   By running make in cmd the game will initiate with this class
*   main method.
*/
public class Game extends JFrame implements MouseListener, KeyListener {

    /*
    *   Private variables related to a Game object
    *   The game object manages the creation of the base
    *   components such as the GameBoard, Settings, Tutorial,
    *   LayoutHandler, and CommandManager.
    *   These are then passed along to sub components which
    *   needs the Components in different ways.
    */
    private GameBoard gb;
    private Settings st;
    private SettingsScreen sts;
    private Tutorial tut;
    private LayoutHandler lh;
    private CommandManager commandManager;

    /*
    *   Base constructor
    */
    public Game() {
        setTitle("2048");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();

        this.setBackground(Color.WHITE);

        this.setLocation(150, 150);
        this.setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        this.gb = new GameBoard(4);
        this.st = new Settings();
        this.sts = new SettingsScreen(this.st);
        this.tut = new Tutorial(this.st);

        this.lh = new LayoutHandler(this.gb, this.st, this.sts, this.tut, width);
        this.lh.setOpaque(true);
        this.setContentPane(this.lh);

        this.commandManager = new CommandManager();

        addMouseListener(this);
        addKeyListener(this);

        this.pack();
        this.setVisible(true);
    }

    /*
    *   Override function to handle mouseclicks inside the
    *   window.
    *   -- Settings pressed = Open Settings
    *   -- Tutorial pressed = Start Tutorial
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        y = y - this.getInsets().top;

        System.out.println("Mouse Clicked at X: " + x + " - Y: " + y);
        if (!this.st.getActiveMenu()) {
            if(this.gb.checkInsideGB(x,y)) {
                System.out.println("Inside GameBoard");
            }
            else if (this.st.checkInsideSetting(x,y)) {
                this.lh.setActiveMenu();
                System.out.println("Inside Setting");
            }
            /*else if (this.tut.checkInsideTutorial(x,y)) {
                System.out.println("Inside Tutorial");
            }*/
            else {
                System.out.println("Outside GameBoard and Setting");
            }
        }
        else {
            int checkVal = this.sts.checkInsideSTS(x,y);
            switch (checkVal) {
                case 0:
                    System.out.println("Inside Language Menu");
                    break;
                case 1:
                    System.out.println("Inside Sound Menu");
                    break;
                case 2:
                    System.out.println("Inside Restart");
                    break;
                default:
                    this.lh.disableActiveMenu();
                    this.st.setActiveMenu(false);
                    System.out.println("Not inside STS");
                    break;
            }
        }
    }

    public void initiateGame() {
        this.gb.resetState();

        this.commandManager.executeCommand(new SpawnTile(this.gb.state, this.lh));
        this.commandManager.executeCommand(new SpawnTile(this.gb.state, this.lh));

        this.commandManager.clearUndos();

        this.lh.animateTile();
    }

    /*
    *   Function move which handles the operations when a
    *   button in a direction has been pressed
    */
    public void move(Direction direction) {
        // Should use Swipe in `direction`, and then `SpawnTile`
        // gb.state should as a side effect be updated by the executed commands
        Swipe swipe = new Swipe(direction, this.gb.state);
        commandManager.executeCommand(swipe);
        if (swipe.stateChanged()) {
            commandManager.executeCommand(new SpawnTile(this.gb.state, this.lh));
            System.out.println(this.gb.state);
        }
        else commandManager.undoCommand();
        lh.animateTile();
    }

    /*
    *   Override function necessary for KeyListener implement
    */
    @Override
    public void keyTyped(KeyEvent e) {}

    /*
    *   Override function for Key pressed handler
    *   -- Right Key pressed    = Swipe Right
    *   -- Left Key pressed     = Swipe Left
    *   -- Up Key pressed       = Swipe Up
    *   -- Down Key pressed     = Swipe Down
    */
    @Override
    public void keyPressed(KeyEvent arrow){
        switch (arrow.getKeyCode()){
            case KeyEvent.VK_UP:
                System.out.println("UP");
                move(Direction.UP);
                this.lh.animateTile();
                //commandManager.executeCommand(new Swipe(Direction.UP, gb.state));
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                // TODO: Replace with move
                move(Direction.DOWN);
                this.lh.animateTile();
                //commandManager.executeCommand(new Swipe(Direction.DOWN, gb.state));
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                // TODO: Replace with move
                move(Direction.RIGHT);
                this.lh.animateTile();
                //commandManager.executeCommand(new Swipe(Direction.RIGHT, gb.state));
                //this.lh.animateTile();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                // TODO: Replace with move
                move(Direction.LEFT);
                this.lh.animateTile();
                //commandManager.executeCommand(new Swipe(Direction.LEFT, gb.state));
                //this.lh.animateTile();
                break;
            case KeyEvent.VK_R:
                System.out.println("REDO");
                if(commandManager.isRedoAvailable()) {
                    commandManager.redoCommand();
                    commandManager.redoCommand();
                    this.lh.animateTile();
                    System.out.println(this.gb.state);
                }
                break;
            case KeyEvent.VK_U:
                System.out.println("UNDO");
                if (commandManager.isUndoAvailable()) {
                    commandManager.undoCommand();
                    commandManager.undoCommand();
                    this.lh.animateTile();
                    this.lh.animateTile(); // Without running this twice, sometimes not all tiles are drawn on undo
                    System.out.println(this.gb.state);
                }
                break;
            case KeyEvent.VK_N:
                System.out.println("NEW GAME");
                initiateGame();
                System.out.println(this.gb.state);
        }
    }

    /*
    *   Override function necessary for KeyListener implement
    */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /*
    *   Checks if undo is available in CommandManager.
    */
    public boolean isUndoAvailable() {
        return commandManager.isUndoAvailable();
    }

    /*
    *   Calls undo function in CommandManager.
    */
    public void undo() {
        commandManager.undoCommand();
    }

    /*
    *   Checks if redo is available in CommandManager.
    */
    public boolean isRedoAvailable() {
        return commandManager.isRedoAvailable();
    }

    /*
    *   Calls redo function in CommandManager.
    */
    public void redo() {
        commandManager.redoCommand();
    }


    /*
    *   Override function necessary for MouseListener implement
    */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /*
    *   Override function necessary for MouseListener implement
    */
    @Override
    public void mouseExited(MouseEvent e) {}

    /*
    *   Override function necessary for MouseListener implement
    */
    @Override
    public void mousePressed(MouseEvent e) {}

    /*
    *   Override function necessary for MouseListener implement
    */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /*
    *   Function to check if a winning situation has occurred?
    *   A win should occur when a Tile has reached a value 2048.
    *   A loss should occur when the GameBoard is full and no,
    *   actions are possible.
    */
    public boolean checkForWin() {
        if (!gb.state.hasEmptyTile()) {
            for (Direction d : Direction.values()) {
                commandManager.executeCommand(new Swipe(d, gb.state));
                if (gb.state.hasEmptyTile()) {
                    commandManager.undoCommand();
                    commandManager.clearRedos();
                    return false ;
                } else commandManager.undoCommand();
            };
            return true; //Skriv ut "Game Over!!!"
        } else return false;
    }

    /*
    *   Main function which runs the program
    */
    public static void main(String[] args) {
        Game frame = new Game();
        System.out.println(frame.gb.state);
    }
}
