import static Direction.*;

class Swipe implements Command  {
    private Direction direction;
    private State previousState;
    private State newState;

    public Swipe(Direction direction, State state) {
        this.direction = direction;
        this.previousState = state;
    }

    @Override
    public void execute() {
        // TODO
    }

    @Override
    public void undo() {
        // TODO
    }

    @Override
    public void redo() {
        // TODO
    }

    /**
     * Merges `tile1` and `tile2` based on a `direction`.
     */
    private void collide(Tile tile1, Tile tile2, Direction direction) {}

    /**
     * Returns the Tile next to `tile` given a `direction` and a `state`.
     */
    private Tile neighbourTile(Tile tile, Direction direction, State state) {
        // TODO
    }

    /**
     * Moves `tile` as far as possible in a `direction` given a `state`.
     */
    private void push(Tile tile, Direction direction, State state) {
        // TODO
    }
}
