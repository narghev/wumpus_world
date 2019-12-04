package ai.whumpusworld.View;

import javax.swing.JFrame;
import java.awt.*;

public class MapView extends JFrame {
    public MapView(){
        super();
        setLayout(new GridLayout(4, 4, 0, 0));
        setSize(800, 800);
        setResizable(true);

        for (int i = 0; i < 16; i++)
            this.add(new CellView());

        setVisible(true);
    }
}
