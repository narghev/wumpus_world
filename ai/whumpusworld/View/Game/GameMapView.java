package ai.whumpusworld.View.Game;

import ai.whumpusworld.Map.GameMap;

import javax.swing.*;
import java.awt.*;

public class GameMapView extends JFrame {
    private CellView[][] cells = new CellView[4][4];

    public GameMapView(GameMap map){
        super();
        setLayout(new GridLayout(4, 4, 0, 0));
        setSize(800, 800);
        setResizable(true);

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                CellView newCell = new CellView(map.map[i][j]);
                cells[i][j] = newCell;
                this.add(newCell);
            }

        setVisible(true);
    }
}
