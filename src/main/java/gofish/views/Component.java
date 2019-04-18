package gofish.views;

public class Component {
    private int x, y, width, height;
    private boolean clickable, draggable;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Accessors

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isClickable() { return clickable; }
    public boolean isDraggable() { return draggable; }

    // Mutators

    public void setX(int newX) { x = newX; }
    public void setY(int newY) { y = newY; }
    public void setWidth(int newWidth) { width = newWidth; }
    public void setHeight(int newHeight) { height = newHeight; }
    public void setClickable(boolean newClickable) { clickable = newClickable; }
    public void setDraggable(boolean newDraggable) { draggable = newDraggable; }

    // Actions

    public void draw(Frame f) {}
}