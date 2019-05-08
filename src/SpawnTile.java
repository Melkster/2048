package src;

/**
 * Spawns a Tile at a random position in `state`, with either the value 2 or 4
 * (chosen randomly).
 */
public class SpawnTile implements Command {
    private int column;
    private int row;
    private State previousState;

    public SpawnTile(State state) {
        this.column = column;
        this.row = row;
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
}
