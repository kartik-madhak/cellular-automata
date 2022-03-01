package simulation_grid.cells;

import simulation_grid.Color;

import static simulation_grid.cells.Cell.Direction.*;

public class Gas extends Cell {
    public Gas() {
        super(new Color(255, 225, 62), 3);
        availableDirections = new Direction[] {UP, LEFT, RIGHT};
    }
}
