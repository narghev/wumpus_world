package ai.whumpusworld.agent;

import java.util.Vector;

import ai.whumpusworld.Coordinate;
import ai.whumpusworld.GameMap;

import java.util.Queue;

public class Agent {
    private Vector<Coordinate> steps;
    private Coordinate currentLocation;
    private int currentLookingDirection;
//    0 - left
//    1 - up
//    2 - right
//    3 - down

    private GameMap agentMap;
    private boolean[][] visited;
    private Percept currentPercepts;

    public Agent(){
        agentMap = new GameMap();
        visited = new boolean[4][4];
        currentLocation = new Coordinate(0, 0);
        steps = new Vector<>(1,1);
        currentPercepts = new Percept(false, false, false);
        currentLookingDirection = 2;

        visited[0][0] = true;
        steps.addElement(new Coordinate(0, 0));
    }

    private void moveUp() {
        currentLookingDirection = 1;
        currentLocation = new Coordinate(currentLocation.x, currentLocation.y + 1);
    }

    private void moveDown() {
        currentLookingDirection = 3;
        currentLocation = new Coordinate(currentLocation.x, currentLocation.y - 1);
    }

    private void moveRight() {
        currentLookingDirection = 2;
        currentLocation = new Coordinate(currentLocation.x + 1, currentLocation.y);
    }

    private void moveLeft() {
        currentLookingDirection = 0;
        currentLocation = new Coordinate(currentLocation.x - 1, currentLocation.y);
    }

    private void move(int direction) {
        switch (direction) {
            case 0:
                moveLeft();
                break;
            case 1:
                moveUp();
                break;
            case 2:
                moveRight();
                break;
            case 3:
                moveDown();
                break;
        }
        steps.addElement(currentLocation);
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
