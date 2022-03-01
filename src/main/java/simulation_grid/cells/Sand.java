package simulation_grid.cells;

import simulation_grid.Color;

import static simulation_grid.cells.Cell.Direction.DOWN;

public class Sand extends Cell {
    public Sand() {
        super(new Color(194, 178, 128), 2);
        availableDirections = new Direction[] {DOWN};
    }
}
