package src;

import java.util.*;

/**
 * Spawns a Tile at a random empty (Void) position in `state`, with either the value 2 or 4
 * (chosen randomly). Pre condition: Assumes that there is an empty position in `state`.
 */
public class SpawnTile implements Command {
    
    private int column;
    private int row;
    private int value;
    private State state;
    
    /*
    *   Base Constructor
    */
    public SpawnTile(State state) {
        this.state = state;

        ArrayList<Tile> voids = state.getEmptyTiles();
        Random rand = new Random();
        Tile randomEmptyTile = voids.get(rand.nextInt(voids.size())); // Selects one of the `state`s Voids at random

        this.column = randomEmptyTile.column;
        this.row = randomEmptyTile.row;
        this.value = randomValue();
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    /*
    *   Override function which handles the behaviour of
    *   the execute action
    */
    @Override
    public void execute() {
        state.addTile(column, row, value);
    }

    /*
    *   Override function which handles the behaviour of
    *   the undo action
    */
    @Override
    public void undo() {
        state.removeTile(column, row);
    }

    /*
    *   Override function which handles the behaviour of
    *   the redo action
    */
    @Override
    public void redo() {
        execute();
    }

    /*
    *   Function which generates a random value
    */
    private int randomValue() {
        int randomValue = new Random().nextInt(100);
        if (randomValue < 75) return 2;
        else return 4;
    }
}
