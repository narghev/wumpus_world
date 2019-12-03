package ai.whumpusworld;

import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.Map.GameMap;
import ai.whumpusworld.agent.Agent;
import ai.whumpusworld.agent.Percept;

public class Main {
    private static GameMap gameMap;
    private static Agent agent;

    private static boolean gameOver() {
        return gameMap.agentCoordinates.equals(new Coordinate(0, 0)) && gameMap.goldCoordinates.equals(new Coordinate(0, 0));
    }

    private static void game() {
        for (int i = 0; i <= 1; i++)
        {
//        while (!gameOver()) {
            step();
            System.out.println("---------------------------------------------------");
//        }
        }
    }

    private static void step() {
        Cell currentCell = gameMap.map[gameMap.agentCoordinates.x][gameMap.agentCoordinates.y];
        Percept currentPercepts = new Percept(currentCell.gold, currentCell.stench, currentCell.breeze);
        agent.setCurrentPercepts(currentPercepts);
        Coordinate newAgentCoordinates = agent.move();
        gameMap.agentCoordinates = newAgentCoordinates;
    }

    public static void main(String[] args) {
        gameMap = new GameMap();
        agent = new Agent();
        game();
        agent.agentMap.printMap();
    }
}
