package src;

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

    /*
    *   Base Constructor
    */
    public Swipe(Direction direction, State state) {
        this.direction = direction;
        this.newState = state;
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
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                if (direction == Direction.LEFT || direction == Direction.UP) {
                    push(newState.getTile(column, row), newState);
                } else if (direction == Direction.RIGHT || direction == Direction.DOWN) {
                    push(newState.getTile(size - column - 1, size - row - 1), newState);
                }
            }
        }
        collided.clear();
        playSound("src/sounds/swipe-sound2.wav");
    }

    /*
    *   Override function which creates a behaviour for the undo
    *   action for the Swipe action.
    */
    @Override
    public void undo() {
        newState = previousState; // TODO: this doesn't work
    }

    /*
    *   Override function which creates a behaviour for the redo
    *   action for the Swipe action.
    */
    @Override
    public void redo() {
        execute();
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
