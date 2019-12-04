package ai.whumpusworld;

import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.Map.GameMap;
import ai.whumpusworld.agent.Agent;
import ai.whumpusworld.agent.Percept;

import java.util.Vector;

public class Main {
    private static GameMap gameMap;
    private static Agent agent;

    private static boolean gameOver() {
        return gameMap.agentCoordinates.equals(new Coordinate(0, 0))
                && gameMap.goldCoordinates.equals(new Coordinate(0, 0));
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
        return new Percept(currentCell.gold, currentCell.stench, currentCell.breeze);
    }

    private static void killWhumpus() {
        Coordinate whumpusCoordinate = gameMap.whumpusCoordinates;
        Cell whumpusCell = gameMap.map[whumpusCoordinate.x][whumpusCoordinate.y];
        Vector<Coordinate> whumpusAdjacent = whumpusCoordinate.adjacentCoordinates();
        whumpusCell.whumpus = false;
        whumpusAdjacent.forEach(c -> {
            gameMap.map[c.x][c.y].stench = false;
        });
        gameMap.whumpusCoordinates = null;
    }

    private static void step() {
        // GRAB GOLD
        Coordinate newGoldCoordinate = agent.grab();
        if (newGoldCoordinate != null)
            gameMap.goldCoordinates = newGoldCoordinate;

        // SHOOT THE WHUMPUS
        boolean shootResult = agent.shoot();
        if (shootResult)
            killWhumpus();

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
