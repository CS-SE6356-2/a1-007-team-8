package gofish.views;

import gofish.models.Player;

public class Frame {
    private int width, height, backgroundColor;

    public Frame(int width, int height, int bgColor) {
        this.width = width;
        this.height = height;
        backgroundColor = bgColor;
    }

    public void render() {}

    public void win(Player winner) {}
}