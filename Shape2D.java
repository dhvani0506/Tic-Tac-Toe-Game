import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape2D {
    protected int xPos, yPos;
    protected Color fillColor;
    protected int width, height;

    public Shape2D(int colorIndex, int xPos, int yPos, int width, int height) {
        this.fillColor = mapColor(colorIndex);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    protected Color mapColor(int colorIndex) {
        switch (colorIndex) {
            case RED: return Color.RED;
            case GREEN: return Color.GREEN;
            case BLUE: return Color.BLUE;
            case BLACK: return Color.BLACK;
            case GRAY: return Color.GRAY;
            case WHITE: return Color.WHITE;
            case YELLOW: return Color.YELLOW;
            case CYAN: return Color.CYAN;
            case MAGENTA: return Color.MAGENTA;
            case BROWN: return new Color(165, 42, 42);
            default: return Color.BLACK;
        }
    }

    public int getX() { return xPos; }
    public int getY() { return yPos; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }
    
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);

    // Color constants
    public static final int RED = 0, GREEN = 1, BLUE = 2,
                          BLACK = 3, GRAY = 4, WHITE = 5,
                          YELLOW = 6, CYAN = 7, MAGENTA = 8,
                          BROWN = 9;
}