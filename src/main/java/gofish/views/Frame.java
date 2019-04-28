package gofish.views;

import gofish.models.Player;

import javax.swing.*;

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
}