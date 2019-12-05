package ai.whumpusworld.Map;

import ai.whumpusworld.Cell.AgentCell;

public class AgentMap {
    //TODO replace me with a constructor to parse json file
    public AgentCell[][] map = new AgentCell[4][4];

    public AgentMap() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                map[i][j] = new AgentCell(false, false, false, false, false, false);

        map[0][0].agent.data = true;
        map[0][0].whumpus.data = false;
        map[0][0].whumpus.alreadySet = true;
        map[0][0].pit.alreadySet = true;
        map[0][0].pit.data = false;
    }

    public void printMap(){
        for(int j = 3; j >= 0; j--){
            for(int i = 0; i < 4; i++){
                System.out.print(" __");
                if(map[i][j].agent.data)
                    System.out.print('A');
                if(map[i][j].gold.data)
                    System.out.print('G');
                if(map[i][j].whumpus.data)
                    System.out.print('W');
                if(map[i][j].pit.data)
                    System.out.print('P');

                System.out.print("__ ");
            }
            System.out.println();
        }
    }
}
