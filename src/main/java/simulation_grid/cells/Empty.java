package simulation_grid.cells;

import simulation_grid.Color;

public class Empty extends Cell {
  public Empty() {
    super(new Color(0, 0, 0, 0), 0);
    availableDirections = new Direction[] {};
  }
}
