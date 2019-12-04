package ai.whumpusworld.Map;

import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.Coordinate;

import java.util.Vector;

public class GameMap {

    public Cell[][] map = new Cell[4][4];
    public Coordinate agentCoordinates;
    public Coordinate goldCoordinates;
    public Coordinate whumpusCoordinates;

      public GameMap () {

          for (int i = 0; i < 4; i++)
              for (int j = 0; j < 4; j++)
                  map[i][j] = new Cell(false, false, false, false, false, false);

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

    public void printMap(){
        for(int j = 3; j >= 0; j--){
            for(int i = 0; i < 4; i++){
                System.out.print(" __");
                if(map[i][j].agent)
                    System.out.print('A');
                if(map[i][j].gold)
                    System.out.print('G');
                if(map[i][j].whumpus)
                    System.out.print('W');
                if(map[i][j].pit)
                    System.out.print('P');

                System.out.print("__ ");
            }
            System.out.println();
        }
    }

  public void setGoldCoordinates(Coordinate newGoldCoordinates) {
    this.map[this.goldCoordinates.x][this.goldCoordinates.y].gold = false;
    this.map[newGoldCoordinates.x][newGoldCoordinates.y].gold = true;
    this.goldCoordinates = newGoldCoordinates;
  }

    public void setAgentCoordinates(Coordinate newAgentCoordinates) {
        this.map[this.agentCoordinates.x][this.agentCoordinates.y].agent = false;
        this.map[newAgentCoordinates.x][newAgentCoordinates.y].agent = true;
        this.agentCoordinates = newAgentCoordinates;
    }
}