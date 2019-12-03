package ai.whumpusworld.Map;

import ai.whumpusworld.Cell.Cell;
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
}
