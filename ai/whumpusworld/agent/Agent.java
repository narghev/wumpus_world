package ai.whumpusworld.agent;

import java.util.Stack;
import java.util.Vector;
import java.util.Random;

import ai.whumpusworld.Map.AgentMap;
import ai.whumpusworld.Coordinate;
import ai.whumpusworld.Cell.AgentCell;

public class Agent {
    Random generator = new Random();

    private Stack<Coordinate> steps;
    private Coordinate currentLocation;

    public AgentMap agentMap;
    private boolean[][] visited;
    private Percept currentPercepts;
    private boolean grabbed;

    public Agent(Percept initialPercepts){
        agentMap = new AgentMap();
        visited = new boolean[4][4];
        currentLocation = new Coordinate(0, 0);
        steps = new Stack<>();
        currentPercepts = new Percept(false, false, false);
        grabbed = false;

        visited[0][0] = true;
        steps.push(new Coordinate(0, 0));
        this.setCurrentPercepts(initialPercepts);
    }

    private void moveUp() {
        currentLocation = new Coordinate(currentLocation.x, currentLocation.y + 1);
    }

    private void moveDown() {
        currentLocation = new Coordinate(currentLocation.x, currentLocation.y - 1);
    }

    private void moveRight() {
        currentLocation = new Coordinate(currentLocation.x + 1, currentLocation.y);
    }

    private void moveLeft() {
        currentLocation = new Coordinate(currentLocation.x - 1, currentLocation.y);
    }

    private void moveBackWithGold() {
        steps.pop();
        agentMap.map[currentLocation.x][currentLocation.y].gold.data = false;
        currentLocation = steps.peek();
        agentMap.map[currentLocation.x][currentLocation.y].gold.data = true;
    }

    public Coordinate move() {
        agentMap.map[currentLocation.x][currentLocation.y].agent.data = false;

        if (grabbed) {
            moveBackWithGold();
        }
        else {
            int direction = this.decideMove();
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

            steps.push(currentLocation);
            visited[currentLocation.x][currentLocation.y] = true;
        }

        agentMap.map[currentLocation.x][currentLocation.y].agent.data = true;

        return currentLocation;
    }

    private boolean isSafe(Coordinate coordinate) {
        return !(agentMap.map[coordinate.x][coordinate.y].pit.data || agentMap.map[coordinate.x][coordinate.y].whumpus.data);
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
            steps.pop();
            Coordinate previousLocation = steps.peek();
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
            agentMap.map[currentLocation.x][currentLocation.y].gold.data = true;

        adjacentCoordinates.forEach(coordinate -> {
            if (!visited[coordinate.x][coordinate.y]){
                AgentCell cell = agentMap.map[coordinate.x][coordinate.y];
                if (!cell.pit.alreadySet || !currentPercepts.breeze) {
                    agentMap.map[coordinate.x][coordinate.y].pit.data = currentPercepts.breeze;
                    cell.pit.alreadySet = true;
                }
                if (!cell.whumpus.alreadySet || !currentPercepts.stench) {
                    agentMap.map[coordinate.x][coordinate.y].whumpus.data = currentPercepts.stench;
                    cell.whumpus.alreadySet = true;
                }
            }
        });
    }

    public void setCurrentPercepts(Percept newPercepts) {
        this.currentPercepts = newPercepts;
        this.updateKnowledgeBase();
    }

    public Coordinate grab() {
        AgentCell currentCell = agentMap.map[currentLocation.x][currentLocation.y];
        if (!grabbed && currentCell.agent.data && currentCell.gold.data)
            grabbed = true;
        if (grabbed)
            return currentLocation;
        return null;
    }
}
