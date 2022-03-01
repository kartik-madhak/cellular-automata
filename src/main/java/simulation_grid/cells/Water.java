package simulation_grid.cells;

import simulation_grid.Color;

import static simulation_grid.cells.Cell.Direction.*;

public class Water extends Cell {
    public Water() {
        super(new Color(0, 0, 255), 1);
        availableDirections = new Direction[] {DOWN, LEFT, RIGHT};
    }
}
