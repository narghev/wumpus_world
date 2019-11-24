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
  public void printMap(){
//      String [][] map = new String[4][4];
//      map[agentCoordinates.x][agentCoordinates.y] = "A";
//      map[goldCoordinates.x][goldCoordinates.y] = "G";
//      map[whumpusCoordinates.x][whumpusCoordinates.y] = "W";
//      map[pitsCoordinates[0].x][pitsCoordinates[0].y] = "P";
//      map[pitsCoordinates[1].x][pitsCoordinates[1].y] = "P";
//      map[pitsCoordinates[2].x][pitsCoordinates[2].y] = "P";
//


      for(int j =3; j>=0; j--){
          for(int i = 0; i<4; i++){
              System.out.print(" __");
              if(agentCoordinates.x ==i && agentCoordinates.y ==j){
                  System.out.print('A');
              }
              if(goldCoordinates.x ==i && goldCoordinates.y ==j){
                  System.out.print('G');
              }
              if(whumpusCoordinates.x ==i && whumpusCoordinates.y==j){
                  System.out.print('W');
              }
              if(pitsCoordinates[0].x == i && pitsCoordinates[0].y ==j){
                  System.out.print('P');
              }
              if(pitsCoordinates[1].x == i && pitsCoordinates[1].y ==j){
                  System.out.print('P');
              }
              if(pitsCoordinates[2].x == i && pitsCoordinates[2].y ==j){
                  System.out.print('P');
              }
              System.out.print("__ ");
          }
          System.out.println();
      }
  }
}