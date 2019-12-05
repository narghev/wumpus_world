package ai.whumpusworld.View.Game;

import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.View.Image.BaseImage;
import ai.whumpusworld.View.Image.Images;

import java.awt.*;
import javax.swing.*;

class CellView extends JPanel {
    private BaseImage pitImage = Images.pitImage();
    private BaseImage goldImage = Images.goldImage();
    private BaseImage agentImage = Images.agentImage();
    private BaseImage breezeImage = Images.breezeImage();
    private BaseImage stenchImage = Images.stenchImage();
    private BaseImage whumpusImage = Images.whumpusImage();

    CellView(Cell cell) {
        super();
        setLayout(new FlowLayout());
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.red));

        this.pitImage.setVisible(cell.pit);
        this.goldImage.setVisible(cell.gold);
        this.agentImage.setVisible(cell.agent);
        this.breezeImage.setVisible(cell.breeze);
        this.stenchImage.setVisible(cell.stench);
        this.whumpusImage.setVisible(cell.whumpus);

        this.add(this.pitImage);
        this.add(this.goldImage);
        this.add(this.agentImage);
        this.add(this.breezeImage);
        this.add(this.stenchImage);
        this.add(this.whumpusImage);
    }
}
