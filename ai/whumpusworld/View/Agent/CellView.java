package ai.whumpusworld.View.Agent;

import ai.whumpusworld.Cell.AgentCell;
import ai.whumpusworld.Cell.Cell;
import ai.whumpusworld.View.Image.BaseImage;
import ai.whumpusworld.View.Image.Images;
import ai.whumpusworld.agent.Percept;

import java.awt.*;
import javax.swing.*;

class CellView extends JPanel {
    private BaseImage pitImage = Images.pitImage();
    private BaseImage goldImage = Images.goldImage();
    private BaseImage agentImage = Images.agentImage();
    private BaseImage breezeImage = Images.breezeImage();
    private BaseImage stenchImage = Images.stenchImage();
    private BaseImage whumpusImage = Images.whumpusImage();

    CellView(AgentCell cell) {
        super();
        setLayout(new FlowLayout());
        this.setBackground(cell.pit.alreadySet ? Color.white : Color.black);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.red));

        this.pitImage.setVisible(cell.pit.data);
        this.goldImage.setVisible(cell.gold.data);
        this.agentImage.setVisible(cell.agent.data);
        this.breezeImage.setVisible(cell.breeze.data);
        this.stenchImage.setVisible(cell.stench.data);
        this.whumpusImage.setVisible(cell.whumpus.data);

        this.add(this.pitImage);
        this.add(this.goldImage);
        this.add(this.agentImage);
        this.add(this.breezeImage);
        this.add(this.stenchImage);
        this.add(this.whumpusImage);
    }

    void update(AgentCell cell) {
        if (cell.pit.alreadySet)
            this.setBackground(Color.white);
        this.pitImage.setVisible(cell.pit.data);
        this.goldImage.setVisible(cell.gold.data);
        this.agentImage.setVisible(cell.agent.data);
        this.whumpusImage.setVisible(cell.whumpus.data);
    }
}
