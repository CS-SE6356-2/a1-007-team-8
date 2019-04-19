package gofish.views;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentTest {
    public static Component comp = new Component(10, 9, 50, 33);

    // Test get/set x
    @Test
    public void testCompX() {
        int x = comp.getX();
        comp.setX(x + 5);
        assertEquals(comp.getX(), x+5);
    }

    // Test get/set y
    public void testCompY() {
        int y = comp.getY();
        comp.setY(y + 5);
        assertEquals(comp.getY(), y+5);
    }

    // Test get/set width
    public void testCompWidth() {
        int width = comp.getWidth();
        comp.setWidth(width + 5);
        assertEquals(width+5, comp.getWidth());
    }

    // Test get/set height
    public void testCompHeight() {
        int height = comp.getHeight();
        comp.setHeight(height + 5);
        assertEquals(height+5, comp.getHeight());
    }

    // Test get/set clickable
    public void testCompClickable() {
        boolean clickable = comp.isClickable();
        comp.setClickable(!clickable);
        assertEquals(!clickable, comp.isClickable());
    }

    // Test get/set draggable
    public void testCompDraggable() {
        boolean draggable = comp.isDraggable();
        comp.setDraggable(!draggable);
        assertEquals(!draggable, comp.isDraggable());
    }
}