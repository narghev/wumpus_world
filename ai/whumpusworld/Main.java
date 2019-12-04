package ai.whumpusworld;

import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.Map.GameMap;
import ai.whumpusworld.View.MapView;
import ai.whumpusworld.agent.Agent;
import ai.whumpusworld.agent.Percept;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Main {
    private static MapView mapView;
    private static GameMap gameMap;
    private static Agent agent;

    private static boolean gameOver() {
        return gameMap.agentCoordinates.equals(new Coordinate(0, 0))
                && gameMap.goldCoordinates.equals(new Coordinate(0, 0));
    }

    private static void game() throws InterruptedException {
        while (!gameOver()) {
            TimeUnit.SECONDS.sleep(3);
            step();
            mapView.repaint(gameMap);
            gameMap.printMap();
            System.out.println("------------------------------------------------");
        }
    }

    private static Percept getCurrentPercepts(Coordinate agentCoordinates){
        Cell currentCell = gameMap.map[agentCoordinates.x][agentCoordinates.y];
        return new Percept(currentCell.gold, currentCell.stench, currentCell.breeze);
    }

    private static void killWhumpus() {
        Cell whumpusCell = gameMap.map[gameMap.whumpusCoordinates.x][gameMap.whumpusCoordinates.y];
        Vector<Coordinate> whumpusAdjacent = gameMap.whumpusCoordinates.adjacentCoordinates();
        whumpusCell.whumpus = false;
        whumpusAdjacent.forEach(c -> {
            gameMap.map[c.x][c.y].stench = false;
        });
        gameMap.whumpusCoordinates = null;
    }

    private static void step() {
        // SHOOT THE WHUMPUS
        boolean shootResult = agent.shoot();
        if (shootResult)
            killWhumpus();

        // MOVE AGENT
        Coordinate newAgentCoordinates = agent.move();
        gameMap.setAgentCoordinates(newAgentCoordinates);

        // GRAB GOLD
        Coordinate newGoldCoordinate = agent.grab();
        if (newGoldCoordinate != null) {
            gameMap.setGoldCoordinates(newGoldCoordinate);
        }

        // UPDATE KB
        Percept newPercepts = getCurrentPercepts(newAgentCoordinates);
        agent.setCurrentPercepts(newPercepts);
    }

    public static void main(String[] args) throws InterruptedException {
        gameMap = new GameMap();

        Cell initCell = gameMap.map[0][0];
        agent = new Agent(new Percept(initCell.gold, initCell.stench, initCell.breeze));

        mapView = new MapView();
        game();
    }
}
