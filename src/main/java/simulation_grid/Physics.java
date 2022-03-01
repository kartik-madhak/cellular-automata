package simulation_grid;

import simulation_grid.cells.Cell;

public class Physics {
  private final long updateDelay = 10;
  private long lastUpdated;

  public Physics() {
    this.lastUpdated = System.currentTimeMillis();
  }

  public void stepEveryDelay(Grid grid) {
    if (System.currentTimeMillis() > lastUpdated + updateDelay) {
      lastUpdated = System.currentTimeMillis();
      step(grid);
    }
  }

  public void step(Grid grid) {
    Cell[][] cells = grid.getCells();
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        tryMovingCell(grid, i, j);
      }
    }
    grid.commit();
  }

  private void tryMovingCell(Grid grid, int i, int j) {
    Cell cell = grid.getCells()[i][j];
    for (Cell.Direction availableDirection : cell.availableDirections) {
      int iNew = (int) (i + availableDirection.getDirection().x);
      int jNew = (int) (j + availableDirection.getDirection().y);
      if (isSwappable(grid, i, j, iNew, jNew)) {
        swap(grid, i, j, iNew, jNew);
        return;
      }
    }
  }

  private void swap(Grid grid, int i, int j, int iNew, int jNew) {
    Cell[][] cells = grid.getCells();
    grid.change(i, j, cells[iNew][jNew]);
    grid.change(iNew, jNew, cells[i][j]);
  }

  private boolean isSwappable(Grid grid, int i, int j, int iNew, int jNew) {
    Cell[][] cells = grid.getCells();
    return isInsideBoundary(iNew, jNew, cells.length, cells[0].length)
        && cells[iNew][jNew].getDensity() < cells[i][j].getDensity()
        && grid.isConsistent(i, j)
        && grid.isConsistent(iNew, jNew);
  }

  boolean isInsideBoundary(int i, int j, int rows, int cols) {
    return i >= 0 && j >= 0 && i < rows && j < cols;
  }
}
