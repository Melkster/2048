package src;

import java.util.*;

class State {
    private ArrayList<ArrayList<Tile>> layout;
    public int size; // Dimensionality of the game board

    public State(int size) {
        this.size = size;
    }

    /**
     * Returns the Tile at a position given a `column` and a `row`.
     */
    public Tile getTile(int column, int row) {
        return layout.get(row).get(column);
    }

    /**
     * Adds `tile` to a position given a `column` and `row` and returns `true` if
     * an empty spot is available. Otherwise return `false`.
     */
    public boolean addTile(int column, int row, Tile tile) {
        if (layout.get(row).get(column) instanceof Void) {
            if (layout.get(row).add(tile) == false) return false;
            else {
                tile.column = column;
                tile.row = row;
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Removes the Tile at a position given a `column` and `row`, and returns
     * `true` if that position was non-empty. Otherwise return `false`.
     */
    public boolean removeTile(int column, int row) {
        Tile tile = layout.get(row).get(column);

        if (tile instanceof Void) {
            return false;
        } else {
            return layout.get(row).remove(tile);
        }
    }

    /**
     * Moves the Tile at a position given a `column` and `row`, to a new
     * position given `newColumn` and `newRow`. Returns `true` if the move was
     * executed successfully, otherwise `false`.
     */
    public boolean moveTile(int column, int row, int newColumn, int newRow) {
        // TODO
    }
}
