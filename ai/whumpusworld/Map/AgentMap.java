package ai.whumpusworld.Map;

import ai.whumpusworld.Coordinate;

public class AgentMap extends EmptyMap {

    public AgentMap () {
        super();
        agentCoordinates = new Coordinate(0, 0);
        this.map[0][0].agent = true;
    }
}
