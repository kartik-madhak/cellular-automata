package simulation_grid;

import processing.core.PApplet;
import simulation_grid.cells.*;

import java.lang.reflect.InvocationTargetException;

public class GridMain extends PApplet {
  private final Color backgroundColor = new Color(30, 30, 30);
  private final int windowWidth = 600;
  private final int windowHeight = 600;
  private final int rows = 50;
  private final int cols = 50;
  private final Class[] inventory = new Class[] {Sand.class, Water.class, Gas.class, Stone.class};
  private int cellSize;
  private Grid grid;
  private boolean leftMouseClicked = false;
  private int inventoryIndex = 0;

  private Physics physicsHandler;

  public static void main(String[] args) {
    PApplet.main(GridMain.class);
  }

  @Override
  public void setup() {
    grid = new Grid(rows, cols);
    grid.change(10, 10, new Sand());
    grid.change(20, 10, new Sand());
    grid.commit();
    cellSize = windowWidth / rows;

    physicsHandler = new Physics();
  }

  @Override
  public void settings() {
    size(windowWidth, windowHeight);
  }

  public void renderAll() {
    clearScreen();
    Cell[][] cells = grid.getCells();
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        render(cells, i, j);
      }
    }
  }

  private void render(Cell[][] cells, int i, int j) {
    Cell cell = cells[i][j];
    fill(
        cell.getColor().red(),
        cell.getColor().green(),
        cell.getColor().blue(),
        cell.getColor().alpha());
    rect(i * cellSize, j * cellSize, cellSize, cellSize);
  }

  private void clearScreen() {
    background(
        backgroundColor.red(),
        backgroundColor.green(),
        backgroundColor.blue(),
        backgroundColor.alpha());
  }

  @Override
  public void mousePressed() {
    if (mouseButton == LEFT) leftMouseClicked = true;
  }

  @Override
  public void mouseReleased() {
    if (mouseButton == LEFT) leftMouseClicked = false;
  }

  @Override
  public void keyPressed() {
    if (key == 'w') {
      inventoryIndex++;
    }
    if (key == 's') {
      inventoryIndex--;
    }
    inventoryIndex %= inventory.length;
  }

  @Override
  public void draw() {
    if (leftMouseClicked) {
      int mX = (mouseX * rows) / windowWidth;
      int mY = (mouseY * cols) / windowHeight;
      try {
        grid.change(mX, mY, (Cell) inventory[inventoryIndex].getDeclaredConstructor().newInstance());
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
      grid.commit();
    }
    physicsHandler.stepEveryDelay(grid);
    renderAll();
  }
}
