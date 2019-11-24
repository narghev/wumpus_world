package ai.whumpusworld.agent;

import java.util.Vector;

import ai.whumpusworld.Coordinate;
import ai.whumpusworld.GameMap;

import java.util.Queue;

public class Agent {
    public Queue<Coordinate> steps;
    public Coordinate currentLocation;

    private GameMap agentMap = new GameMap();
    private boolean[][] visited = new boolean[4][4];
    private Percept currentPercepts = new Percept(false, false, false);

    public Agent(){
        visited[0][0] = true;
        steps.add(new Coordinate(0, 0));
        currentLocation = new Coordinate(0, 0);
    }

    public void setCurrentPercepts(Percept newPercepts) {
        this.currentPercepts = newPercepts;
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
}
