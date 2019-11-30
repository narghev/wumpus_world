package ai.whumpusworld;

import java.util.Vector;

public class Coordinate {
  public int x;
  public int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Vector adjacentCoordinates () {
    Vector<Coordinate> adjacentCoordinates = new Vector<>(2, 1);
    int x = this.x;
    int y = this.y;

    for (int i = -1; i<= 1; i+=2) {
      if (x + i <= 3 && x + i >= 0)
        adjacentCoordinates.addElement(new Coordinate(x + i, y));
      if (y+ i <= 3 && y + i >= 0)
        adjacentCoordinates.addElement(new Coordinate(x, y + i));
    }

    return adjacentCoordinates;
  }

  public boolean equals(Coordinate coordinateToCompare) {
    return this.x == coordinateToCompare.x && this.y == coordinateToCompare.y;
  }
}