package gofish.views;

import gofish.models.Player;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private int width, height, backgroundColor;

    public Frame(String name, int width, int height, int bgColor) {
        super(name);
        this.width = width;
        this.height = height;
        backgroundColor = bgColor;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void render() {}

    public void win(Player winner) {}

    public void loadPanel(JPanel panel) {
       // clear();
        // Load pane
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    // probably don't need
    public void clear() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }
}