package ai.whumpusworld.Cell;

public class Cell {
    public boolean set;
    public boolean pit;
    public boolean gold;
    public boolean agent;
    public boolean stench;
    public boolean breeze;
    public boolean whumpus;

    public Cell(boolean agent, boolean gold, boolean whumpus, boolean pit, boolean stench, boolean breeze) {
        this.pit = pit;
        this.set = false;
        this.gold = gold;
        this.agent = agent;
        this.stench = stench;
        this.breeze = breeze;
        this.whumpus = whumpus;
    }
}
