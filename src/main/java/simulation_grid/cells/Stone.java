package simulation_grid.cells;

import simulation_grid.Color;

import static simulation_grid.cells.Cell.Direction.DOWN;

public class Stone extends Cell {
    public Stone() {
        super(new Color(100, 100, 100), 10);
        availableDirections = new Direction[] {};
    }
}
