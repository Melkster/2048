package src;

/*
*   Class that acts as a Void TIle.
*   A Void Tile is an empty space in the
*   State and the GameBoard.
*/
public class Void extends Tile {

    /*
    *   Base Constructor
    */
    public Void(int column, int row) {
        super(column, row, 0);
    }

    /*
    *   Increment function for the Void
    */
    public void increment() {}

    /*
    *   Decrement function for the Void
    */
    public void decrement() {}

    /*
    *   Override function to handle what is
    *   printed for the Void Tile
    */
    @Override
    public String toString() {
        return "[ ]";
    }
}