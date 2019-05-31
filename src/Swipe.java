package src;

import javax.swing.JFrame;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.*;

/*
*   Class that constructs a Swiping action.
*   These actions are put into the stack.
*/
public class Swipe implements Command  {

    private Direction direction;
    private State previousState;
    private State newState;
    private ArrayList<Tile> collided = new ArrayList<Tile>();

    private JFrame gm;
    private Settings st;
    private GameBoard gb;
    private LayoutHandler lh;

    /*
    *   Base Constructor
    */
    public Swipe(Direction direction, JFrame game, GameBoard gameB, State state, Settings theST, LayoutHandler lhe) {
        this.direction = direction;
        this.newState = state;
        this.gm = game;
        this.st = theST;
        this.gb = gameB;
        this.lh = lhe;

        try {
            this.previousState = newState.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /*
    *   Override function which creates a behaviour for the execute
    *   action for the Swipe action.
    */
    @Override
    public void execute() {
        int size = previousState.size;
        ArrayList<Tile> older = new ArrayList<Tile>();
        ArrayList<Tile> newer = new ArrayList<Tile>();

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                try {
                    Tile tile;
                    if (direction == Direction.UP) {
                        tile = newState.getTile(column, row);
                    }
                    else if (direction == Direction.DOWN) {
                        tile = newState.getTile(column, size - row - 1);
                    }
                    else if (direction == Direction.LEFT) {
                        tile = newState.getTile(column, row);
                    }
                    else {
                        tile = newState.getTile(size - column - 1, row);
                    }

                    Tile tmp = tile.clone();
                    push(tile, newState);

                    if ((!(tmp instanceof Void)) && (!(tile instanceof Void))) {
                        older.add(tmp);
                        newer.add(tile);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        animate(older, newer);

        collided.clear();
        if (this.st.getSound()) {
            playSound("src/sounds/swipe-sound2.wav");
        }
    }

    public void animate(ArrayList<Tile> older, ArrayList<Tile> newer) {
        int theSize = older.size();
        
        int[] oldX = new int[theSize];
        int[] oldY = new int[theSize];
        int[] newX = new int[theSize];
        int[] newY = new int[theSize];

        ArrayList<Boolean> notDone = new ArrayList<Boolean>();

        System.out.println("HERE");

        for (int i = 0; i<theSize; i++) {
            notDone.add(true);
            oldX[i] = (older.get(i).getTileX(this.gb.getRecSize()));
            oldY[i] = (older.get(i).getTileY(this.gb.getRecSize()));
            newX[i] = (newer.get(i).getTileX(this.gb.getRecSize()));
            newY[i] = (newer.get(i).getTileY(this.gb.getRecSize()));
        }

        /*for (int k=0; k<theSize; k++) {
            System.out.println("Element " + k);
            System.out.println(notDone.get(k));
            System.out.println("OldX " + oldX[k]);
            System.out.println("OldY " + oldY[k]);
            System.out.println("NewX " + newX[k]);
            System.out.println("NewY " + newY[k]);
        }*/

        this.lh.removeAllTiles();
        //this.lh.repaintLayoutHandler();
        this.gm.revalidate();
        this.gm.repaint();

        while(notDone.contains(true)) {
            this.lh.addAllTiles(older, oldX, oldY);
            //this.lh.repaintLayoutHandler();
            this.gm.revalidate();
            this.gm.repaint();

            for (int x=0; x<theSize; x++) {
                if (notDone.get(x)) {
                    if (direction == Direction.UP) {
                        oldY[x] += -1;
                        if (oldY[x] <= newY[x]) {
                            notDone.set(x, false);
                        }
                    }
                    else if (direction == Direction.DOWN) {
                        oldY[x] += 1;
                        if (oldY[x] >= newY[x]) {
                            notDone.set(x, false);
                        }
                    }
                    else if (direction == Direction.LEFT) {
                        oldX[x] += -1;
                        if (oldX[x] <= newX[x]) {
                            notDone.set(x, false);
                        }
                    }
                    else if (direction == Direction.RIGHT) {
                        oldX[x] += 1;
                        if (oldX[x] >= newX[x]) {
                            notDone.set(x, false);
                        }
                    }
                }
            }

            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.lh.removeAllTiles();
            //this.lh.repaintLayoutHandler();
            this.gm.revalidate();
            this.gm.repaint();
        }

        this.lh.removeAllTiles();
        //this.lh.repaintLayoutHandler();
        this.gm.revalidate();
        this.gm.repaint();
    }

    /*
    *   Override function which creates a behaviour for the undo
    *   action for the Swipe action.
    */
    @Override
    public void undo() {
        newState.set(previousState);
    }

    /*
    *   Override function which creates a behaviour for the redo
    *   action for the Swipe action.
    */
    @Override
    public void redo() {
        execute();
    }

    public boolean stateChanged() {
        return !(newState.equals(previousState));
    }

    /**
     * Merges `tile` into its neighbour in `this.direction` if they are equal,
     * given a `state`.  Otherwise do nothing.
     */
    private void collide(Tile tile, State state) {
        Tile neighbour = neighbourTile(tile, state);
        if (tile.equals(neighbour) && collidable(neighbour)) {
            state.removeTile(tile);
            neighbour.increment();
            collided.add(neighbour);
        }
    }

    /**
     * Moves `tile` as far as possible in `this.direction` given a `state`. If
     * `tile` collides with a tile with the same value they will be merged.
     */
    private void push(Tile tile, State state) {
        if (tile instanceof Void) return;
        Tile neighbour;
        do {
            neighbour = neighbourTile(tile, state);
            if (neighbour == null) return; // Stop if we reach an edge of the game board
            if (neighbour instanceof Void) state.moveTile(tile, neighbour.column, neighbour.row);
        } while (neighbour instanceof Void);
        collide(tile, state);
    }

    /**
     * Returns `true` if `tile` has already been collided with a tile during
     * this Swipe, otherwise `false`.
     */
    private boolean collidable(Tile tile) {
        return collided.indexOf(tile) == -1; // Checks if tile exist in `collided`
    }

    /**
     * Returns the Tile next to `tile` in a `direction`, given `state`. If
     * `tile` is next to an edge and has no neighbour in `direction`, return
     * `null`.
     */
    private Tile neighbourTile(Tile tile, State state) {
        if (direction == Direction.RIGHT) {
            return state.getTile(tile.column + 1, tile.row);
        } else if (direction == Direction.LEFT) {
            return state.getTile(tile.column - 1, tile.row);
        } else if (direction == Direction.UP) {
            return state.getTile(tile.column, tile.row - 1);
        } else {
            return state.getTile(tile.column, tile.row + 1);
        }
    }

    /*
    *   Function to play a sound when a Swipe action is executed
    */
    private void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        Clip clip;
        AudioInputStream audioIn;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
