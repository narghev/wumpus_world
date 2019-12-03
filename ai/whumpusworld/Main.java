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
        while (!gameOver()) {
            step();
            agent.agentMap.printMap();
            System.out.println("------------------------------------------------");
        }
    }

    private static Percept getCurrentPercepts(Coordinate agentCoordinates){
        Cell currentCell = gameMap.map[agentCoordinates.x][agentCoordinates.y];
        Percept currentPercepts = new Percept(currentCell.gold, currentCell.stench, currentCell.breeze);
        return currentPercepts;
    }

    private static void step() {
        // GRAB GOLD
        Coordinate newGoldCoordinate = agent.grab();
        if (newGoldCoordinate != null)
            gameMap.goldCoordinates = newGoldCoordinate;

        // MOVE AGENT
        Coordinate newAgentCoordinates = agent.move();
        gameMap.agentCoordinates = newAgentCoordinates;

        // UPDATE KB
        Percept newPercepts = getCurrentPercepts(newAgentCoordinates);
        agent.setCurrentPercepts(newPercepts);
    }

    public static void main(String[] args) {
        gameMap = new GameMap();

        Cell initCell = gameMap.map[0][0];
        agent = new Agent(new Percept(initCell.gold, initCell.stench, initCell.breeze));

        game();
    }
}
