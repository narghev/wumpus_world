package ai.whumpusworld.agent;

import java.util.Vector;
import java.util.Random;

import ai.whumpusworld.Map.AgentMap;
import ai.whumpusworld.Coordinate;

public class Agent {
    Random generator = new Random();

    private Vector<Coordinate> steps;
    private Coordinate currentLocation;
    private int currentLookingDirection; // 0 - left, 1 - up, 2 - right, 3 - down

    public AgentMap agentMap;
    private boolean[][] visited;
    private Percept currentPercepts;

    public Agent(){
        agentMap = new AgentMap();
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

    public Coordinate move() {
        int direction = this.decideMove();
        agentMap.map[currentLocation.x][currentLocation.y].agent = false;
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
        visited[currentLocation.x][currentLocation.y] = true;
        agentMap.map[currentLocation.x][currentLocation.y].agent = true;

        return currentLocation;
    }

    private boolean isSafe(Coordinate coordinate) {
        return !(agentMap.map[coordinate.x][coordinate.y].pit || agentMap.map[coordinate.x][coordinate.y].whumpus);
    }

    private boolean isVisited(Coordinate coordinate) {
        return visited[coordinate.x][coordinate.y];
    }

    private int getMoveDirection(Coordinate to) {
        Coordinate current = this.currentLocation;
        int currentX = current.x;
        int currentY = current.y;
        int toX = to.x;
        int toY = to.y;
        int deltaX = currentX - toX;
        int deltaY = currentY - toY;

        if (deltaX == 1)
            return 0; // left
        if (deltaX == -1)
            return 2; // right
        if (deltaY == 1)
            return 3; // up
        return 1; // down
    }

    private int decideMove() {
        Vector<Coordinate> canGo = new Vector<>(1, 1);
        Vector<Coordinate> adjacentCoordinates = currentLocation.adjacentCoordinates();
        adjacentCoordinates.forEach(adjacentCoordinate -> {
            if(isSafe(adjacentCoordinate) && !isVisited(adjacentCoordinate))
                canGo.addElement(adjacentCoordinate);
        });

        if (canGo.isEmpty()){
            Coordinate previousLocation = steps.elementAt(steps.size() - 2);
            int direction = getMoveDirection(previousLocation);
            return direction;
        }

        int randomCanGoCoordinateIndex = generator.nextInt(canGo.size());
        Coordinate shouldGo = canGo.elementAt(randomCanGoCoordinateIndex);
        int direction = getMoveDirection(shouldGo);
        return direction;
    }

    private void updateKnowledgeBase() {
        Vector<Coordinate> adjacentCoordinates = currentLocation.adjacentCoordinates();

        if (currentPercepts.glitter)
            agentMap.map[currentLocation.x][currentLocation.y].gold = true;

        adjacentCoordinates.forEach(coordinate -> {
            if (!visited[coordinate.x][coordinate.y]){
                agentMap.map[coordinate.x][coordinate.y].pit = currentPercepts.breeze;
                agentMap.map[coordinate.x][coordinate.y].whumpus = currentPercepts.stench;
            }
        });
    }



    public void setCurrentPercepts(Percept newPercepts) {
        this.currentPercepts = newPercepts;
        this.updateKnowledgeBase();
        agentMap.printMap();
    }
}
