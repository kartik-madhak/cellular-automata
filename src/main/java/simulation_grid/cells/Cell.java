package simulation_grid.cells;

import processing.core.PVector;
import simulation_grid.Color;

public abstract class Cell {
  public Direction[] availableDirections;
  private Color color;
  private int density;

  protected Cell(Color color, int density) {
    this.color = color;
    this.density = density;
  }

  public Color getColor() {
    return color;
  }

  public int getDensity() {
    return density;
  }

  public enum Direction {
    LEFT(new PVector(-1, 0)),
    RIGHT(new PVector(1, 0)),
    UP(new PVector(0, -1)),
    DOWN(new PVector(0, 1));

    private final PVector direction;

    Direction(PVector direction) {
      this.direction = direction;
    }

    public PVector getDirection() {
      return direction;
    }
  }
}
