package src;

import java.util.*;

/*
*   Class which handles the information of how the current
*   State looks and what it contains.
*/
public class State implements Cloneable, Iterable<Tile> {
    private ArrayList<ArrayList<Tile>> layout;
    public int size; // Dimensionality of the game board

    /*
    *   Base Constructor
    */
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

    /*
    *   Override function which determines what is printed out
    *   for the Object.
    */
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
    public boolean equals(Object o) { 
        
        State tmp = (State) o;

        Iterator<Tile> theIter = this.iterator();
        Iterator<Tile> checkIter = tmp.iterator();

        boolean check = true;

        while(theIter.hasNext()) {
            Tile thisTile = theIter.next();
            Tile checkTile = checkIter.next();

            if (thisTile.value != checkTile.value) {
                check = false;
                break;
            }
        }

        return check;
    }

    /*
    *   Override function which handles the Cloning behaviour
    *   for the object.
    */
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

    /*
    *   Function to check if the State has any empty slots.
    */
    public boolean hasEmptyTile() {
        return getEmptyTiles().size() > 0;
    }

    /*
    *   Function to fetch an ArrayList of all the Empty
    *   Tiles in the State.
    */
    public ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> voids = new ArrayList<Tile>();
        for (Tile tile : this) {
            if (tile instanceof Void) {
                voids.add(tile);
            }
        }
        return voids;
    }

    /*
    *   Override function which Creates an Iterator for the State.
    */
    @Override
    public Iterator<Tile> iterator() {
        return new StateIterator(this);
    }

    /*
    *   Inner Class that is an Iterator for the Object.
    */
    class StateIterator implements Iterator<Tile> {
        Tile current;
        State state;

        /*
        *   Base Constructor
        */
        public StateIterator(State state) {
            this.state = state;
            current = state.getTile(0, 0);
        }

        /*
        *   Function to check is the Iterator has something
        *   next in itself.
        */
        public boolean hasNext() {
            return current != null;
        }

        /*
        *   Function to return the next Tile in the Iterator.
        */
        public Tile next() {
            Tile tile = current;
            if (current.column < size - 1) {
                current = state.getTile(current.column + 1, current.row);
            } else {
                current = state.getTile(0, current.row + 1);
            }
            return tile;
        }
    }
}