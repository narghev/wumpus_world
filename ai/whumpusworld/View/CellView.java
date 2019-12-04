package ai.whumpusworld.View;

import ai.whumpusworld.View.Image.Images;

import java.awt.*;
import javax.swing.*;

class CellView extends JPanel {
    CellView() {
        super();
        setLayout(new FlowLayout());
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.add(Images.agentImage());
        this.add(Images.goldImage());
        this.add(Images.stenchImage());
        this.add(Images.whumpusImage());
    }
}
