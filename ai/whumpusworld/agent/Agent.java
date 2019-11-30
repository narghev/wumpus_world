package ai.whumpusworld.agent;

import java.util.Vector;

import ai.whumpusworld.Coordinate;
import ai.whumpusworld.GameMap;

import java.util.Queue;

public class Agent {
    private Vector<Coordinate> steps;
    private Coordinate currentLocation;

    private GameMap agentMap;
    private boolean[][] visited;
    private Percept currentPercepts;

    public Agent(){
        steps = new Vector<>(1,1);
        agentMap = new GameMap();
        visited = new boolean[4][4];
        currentPercepts = new Percept(false, false, false);
        visited[0][0] = true;
        steps.addElement(new Coordinate(0, 0));
        currentLocation = new Coordinate(0, 0);
    }

    private void updateKnowledgeBase() {
        Vector<Coordinate> adjacentCoordinates = currentLocation.adjacentCoordinates();

        if (currentPercepts.glitter)
            agentMap.map[currentLocation.x][currentLocation.y].gold = true;

        adjacentCoordinates.forEach(coordinate -> {
            if (currentPercepts.breeze)
                agentMap.map[coordinate.x][coordinate.y].pit = true;
            else
                agentMap.map[coordinate.x][coordinate.y].pit = false;

            if (currentPercepts.stench)
                agentMap.map[coordinate.x][coordinate.y].whumpus = true;
            else
                agentMap.map[coordinate.x][coordinate.y].whumpus = false;
        });
    }

    public void doStep(Percept newPercepts) {
        this.currentPercepts = newPercepts;
        this.updateKnowledgeBase();
    }
}
