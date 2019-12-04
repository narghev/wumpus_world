package ai.whumpusworld.View;

import ai.whumpusworld.View.Image.AgentImage;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

class CellView extends JPanel {
    CellView() {
        super();
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.add(new AgentImage());

    }
}
