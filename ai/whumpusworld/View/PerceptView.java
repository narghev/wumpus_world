package ai.whumpusworld.View;

import ai.whumpusworld.View.Image.BaseImage;
import ai.whumpusworld.View.Image.Images;
import ai.whumpusworld.agent.Percept;

import javax.swing.*;
import java.awt.*;

public class PerceptView extends JFrame {
    private BaseImage breeze;
    private BaseImage stench;
    private BaseImage glitter;

    public PerceptView() {
        super();
        setLayout(new GridLayout(1, 3, 0, 0));
        setSize(300, 100);
        setResizable(true);

        this.breeze = Images.breezeImage();
        this.stench = Images.stenchImage();
        this.glitter = Images.goldImage();

        this.glitter.setVisible(false);
        this.breeze.setVisible(false);
        this.stench.setVisible(false);

        this.add(this.glitter);
        this.add(this.breeze);
        this.add(this.stench);

        this.setVisible(true);
    }

    public void repaint(Percept percept) {
        this.glitter.setVisible(percept.glitter);
        this.breeze.setVisible(percept.breeze);
        this.stench.setVisible(percept.stench);
    }
}
