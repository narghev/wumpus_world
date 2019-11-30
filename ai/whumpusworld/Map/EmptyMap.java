package ai.whumpusworld.Map;

import ai.whumpusworld.Cell;
import ai.whumpusworld.Coordinate;

public class EmptyMap {
    //TODO replace me with a constructor to parse json file
    public Cell[][] map = new Cell[4][4];
    public Coordinate agentCoordinates;
    public Coordinate goldCoordinates;
    public Coordinate whumpusCoordinates;
    public Coordinate[] pitsCoordinates;

    public EmptyMap() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                map[i][j] = new Cell(false, false, false, false, false, false);
    }

    public void printMap(){
        for(int j = 3; j >= 0; j--){
            for(int i = 0; i < 4; i++){
                System.out.print(" __");
                if(agentCoordinates != null && agentCoordinates.x == i && agentCoordinates.y == j)
                    System.out.print('A');
                if(goldCoordinates != null && goldCoordinates.x == i && goldCoordinates.y == j)
                    System.out.print('G');
                if(whumpusCoordinates != null && whumpusCoordinates.x ==i && whumpusCoordinates.y==j)
                    System.out.print('W');
                if (pitsCoordinates != null)
                    for (Coordinate pitCoordinates: pitsCoordinates)
                        if(pitCoordinates.x == i && pitCoordinates.y ==j)
                            System.out.print('P');

                System.out.print("__ ");
            }
            System.out.println();
        }
    }
}
