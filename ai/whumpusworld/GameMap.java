package ai.whumpusworld;

import java.util.Vector;

public class GameMap {
  //TODO replace me with a constructor to parse json file
  private final Coordinate agentCoordinates = new Coordinate(0, 0);
  private final Coordinate goldCoordinates = new Coordinate(1, 2);
  private final Coordinate whumpusCoordinates = new Coordinate(0, 2);
  private final Coordinate[] pitsCoordinates = {
    new Coordinate(2, 0),
    new Coordinate(2, 2),
    new Coordinate(3, 3)
  };

  public Cell[][] map = new Cell[4][4];

  public GameMap () {
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        map[i][j] = new Cell(false, false, false, false, false, false);

    map[agentCoordinates.x][agentCoordinates.y].agent = true;
    map[goldCoordinates.x][goldCoordinates.y].gold = true;

    map[whumpusCoordinates.x][whumpusCoordinates.y].whumpus = true;
    Vector<Coordinate> whumpusAdjacent = whumpusCoordinates.adjacentCoordinates();
    whumpusAdjacent.forEach(coordinate -> {
      map[coordinate.x][coordinate.y].stench = true;
    });

    for (Coordinate pitCoordinate : pitsCoordinates)
    {
      map[pitCoordinate.x][pitCoordinate.y].pit = true;
      Vector<Coordinate> pitAdjacent = pitCoordinate.adjacentCoordinates();
      pitAdjacent.forEach(coordinate -> {
        map[coordinate.x][coordinate.y].breeze = true;
      });
    }
  }
}