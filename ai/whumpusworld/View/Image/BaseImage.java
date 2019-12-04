package ai.whumpusworld.View.Image;

import javax.swing.*;
import java.awt.*;

class BaseImage extends JPanel {
    private Image image;

    public BaseImage(String path) {
        super();
        this.image = new ImageIcon(this.getClass().getResource(path)).getImage();
        this.setPreferredSize(new Dimension(70, 70));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
