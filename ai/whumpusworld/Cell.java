package ai.whumpusworld;

public class Cell {
    public boolean agent;
    public boolean gold;
    public boolean whumpus;
    public boolean pit;
    public boolean stench;
    public boolean breeze;

    public Cell(boolean agent, boolean gold, boolean whumpus, boolean pit, boolean stench, boolean breeze) {
        this.agent = agent;
        this.gold = gold;
        this.whumpus = whumpus;
        this.pit = pit;
        this.stench = stench;
        this.breeze = breeze;
    }
}
