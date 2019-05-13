package src;

import java.util.*;

public class State implements Cloneable {
    private ArrayList<ArrayList<Tile>> layout;
    public int size; // Dimensionality of the game board

    public State(int size) {
        this.size = size;
        layout = new ArrayList<ArrayList<Tile>>(size);

        // A new state is initiated with empty tiles (Voids) on all positions.
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            layout.add(row);
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                row.add(new Void(columnIndex, rowIndex));
            }
        }
    }

    /**
     * Returns the Tile at a position given a `column` and a `row`. If the
     * position is outisde `this.layout`, return `null`.
     */
    public Tile getTile(int column, int row) {
        if (column < 0 || row < 0 || column >= size || row >= size) return null;
        else return layout.get(row).get(column);
    }

    /**
     * Adds Tile with `value` to a position given a `column` and `row` and
     * returns `true` if an empty spot is available. Otherwise return `false`.
     */
    public boolean addTile(int column, int row, Tile tile) {
        if (layout.get(row).get(column) instanceof Void) {
            layout.get(row).set(column, tile);
            tile.column = column;
            tile.row = row;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overloaded version of addTile.
     *
     * Adds `tile` to a position given a `column` and `row` and returns `true` if
     * an empty spot is available. Otherwise return `false`.
     */
    public boolean addTile(int column, int row, int value) {
        return addTile(column, row, new Tile(column, row, value));
    }

    /**
     * Overloaded version of addTile.
     *
     * Adds `tile` to a position based on `tile.column` and `tile.row` and
     * returns `true` if an empty spot is available. Otherwise return `false`.
     */
    public boolean addTile(Tile tile) {
        return addTile(tile.column, tile.row, tile);
    }

    /**
     * Removes the Tile at a position given a `column` and `row`, and returns
     * `true` if that position was non-empty. Otherwise return `false`.
     */
    public boolean removeTile(int column, int row) {
        if (layout.get(row).get(column) instanceof Void) return false;
        else {
            layout.get(row).set(column, new Void(column, row));
            return true;
        }
    }


    /**
     * Overloaded version of removeTile.
     *
     * Removes `tile`, and returns `true` if that position was non-empty.
     * Otherwise return `false`.
     */
    public boolean removeTile(Tile tile) {
        return removeTile(tile.column, tile.row);
    }

    /**
     * Moves `tile`, to a new position given `newColumn` and `newRow`. Returns
     * `true` if the move was executed successfully, otherwise `false`.
     */
    public boolean moveTile(Tile tile, int newColumn, int newRow) {
        return removeTile(tile.column, tile.row) && addTile(newColumn, newRow, tile);
    }

    @Override
    public String toString() {
        String output = "";
        for (ArrayList<Tile> row : layout) {
            for (Tile tile : row) {
                output += tile + " ";
            }
            output += "\n";
        }
        return output;
    }

    @Override
    public State clone() throws CloneNotSupportedException {
        ArrayList<ArrayList<Tile>> layoutClone = new ArrayList<ArrayList<Tile>>(size);
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            layoutClone.add(row);
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                row.add((Tile) getTile(columnIndex, rowIndex).clone());
            }
        }
        State stateClone = new State(size);
        stateClone.layout = layoutClone;
        return stateClone;
    }

    public boolean hasEmptyTile() {
        for (int i = 0; i < state.size; i++) {
            for (int k = 0; k < state.size; k++) {
                tile = state.getTile(k, i);
                if (tile instanceof Void) {
                    return true;
                }
            }

            return false;
        }
    }
}
