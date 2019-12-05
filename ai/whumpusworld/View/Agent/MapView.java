package ai.whumpusworld.View.Agent;

import ai.whumpusworld.Map.AgentMap;
import ai.whumpusworld.View.Agent.CellView;

import javax.swing.JFrame;
import java.awt.*;

public class MapView extends JFrame {
    private CellView[][] cells = new CellView[4][4];

    public MapView(){
        super();
        setLayout(new GridLayout(4, 4, 0, 0));
        setSize(800, 800);
        setResizable(true);

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                CellView newCell = new CellView();
                cells[i][j] = newCell;
                this.add(newCell);
            }

        setVisible(true);
    }

    public void repaint(AgentMap map) {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                cells[i][j].update(map.map[i][j]);
    }
}
