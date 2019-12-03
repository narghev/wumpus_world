package ai.whumpusworld.Cell;

public class AgentCell {
    public CellData pit;
    public CellData gold;
    public CellData agent;
    public CellData stench;
    public CellData breeze;
    public CellData whumpus;

    public AgentCell(boolean agent, boolean gold, boolean whumpus, boolean pit, boolean stench, boolean breeze) {
        this.pit = new CellData(pit);
        this.gold = new CellData(gold);
        this.agent = new CellData(agent);
        this.stench = new CellData(stench);
        this.breeze = new CellData(breeze);
        this.whumpus = new CellData(whumpus);
    }
}
