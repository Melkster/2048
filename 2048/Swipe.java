class Swipe implements Command  {
    public Direction direction;
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

    private void collide(Tile tile1, Tile tile2)
}
