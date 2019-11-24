package ai.whumpusworld;

import ai.whumpusworld.agent.Agent;
import ai.whumpusworld.agent.Percept;

public class Main {
    private static GameMap gameMap = new GameMap();
    private static Agent agent = new Agent();

    private static boolean checkGameOver() {
        return gameMap.agentCoordinates.equals(new Coordinate(0, 0)) && gameMap.goldCoordinates.equals(new Coordinate(0, 0));
    }

    private static void game() {
        if (checkGameOver()) {
            // TODO
            return;
        }
    }

    private static void step() {
        Coordinate agentCoordinate = gameMap.agentCoordinates;
        Cell currentCell = gameMap.map[agentCoordinate.x][agentCoordinate.y];
        Percept currentPercepts = new Percept(currentCell.gold, currentCell.stench, currentCell.breeze);
        agent.setCurrentPercepts(currentPercepts);
    }

    public static void main(String[] args) {

    }
}
