package simulation_grid;

import simulation_grid.cells.Cell;
import simulation_grid.cells.Empty;

import java.util.Arrays;

public class Grid {
  public final int rows;
  public final int cols;
  private final Cell[][] doubleBuffer;
  private final Cell[][] cells;

  public Grid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;

    cells = new Cell[rows][cols];
    doubleBuffer = new Cell[rows][cols];

    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        cells[i][j] = new Empty();
        doubleBuffer[i][j] = new Empty();
      }
    }
  }

  public Cell[][] getCells() {
    return cells;
  }

  public void change(int i, int j, Cell newValue) {
    doubleBuffer[i][j] = newValue;
  }

  public void commit() {
    for (int i = 0; i < cells.length; i++) {
      cells[i] = Arrays.copyOf(doubleBuffer[i], cells.length);
    }
  }

  public boolean isConsistent(int i, int j) {
    return cells[i][j] == doubleBuffer[i][j];
  }
}
