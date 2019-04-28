package gofish.views;

import gofish.models.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private int width, height, backgroundColor;

    public Frame(String name, int width, int height, int bgColor) {
        super(name);
        this.width = width;
        this.height = height;
        backgroundColor = bgColor;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void render() {}

    public void win(Player winner) {}

    public void loadMainMenu(ActionListener playBtn, ActionListener rulesBtn, ActionListener quitBtn) {
        clear();
        // Load main menu
        setContentPane(new MainMenu(playBtn, rulesBtn, quitBtn).getView());
        pack();
        setVisible(true);
    }

    public void clear() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }
}