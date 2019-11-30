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

    for (int i = -1; i <= 1; i++)
    {
      for (int j = -1; j <= 1; j++)
      {
        if ((x + i <= 3 && x + i >= 0) && (y + j <= 3 && y + j >= 0) && ( !(i == 0 && j == 0) ))
        {
          adjacentCoordinates.addElement(new Coordinate(x+i, y+j));
        }
      }
    }

    return adjacentCoordinates;
  }

  public boolean equals(Coordinate coordinateToCompare) {
    return this.x == coordinateToCompare.x && this.y == coordinateToCompare.y;
  }
}