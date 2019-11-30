package ai.whumpusworld.Map;

import ai.whumpusworld.Coordinate;

import java.util.Vector;

public class GameMap extends EmptyMap {

  public GameMap () {
      super();
      agentCoordinates = new Coordinate(0, 0);
      goldCoordinates = new Coordinate(1, 2);
      whumpusCoordinates = new Coordinate(0, 2);
      Coordinate[] pitsCoordinates = {
          new Coordinate(2, 0),
          new Coordinate(2, 2),
          new Coordinate(3, 3)
      };

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