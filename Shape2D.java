import java.awt.Color;
import java.awt.Graphics;

/**
 * @author (Dhvani & Brianna)
 * 
 * This is the main superclass for all the subclasses of the shapes.
 */
public abstract class Shape2D {
    protected int xPos, yPos;
    protected Color fillColor;
    protected int width, height;

    /**
     * public Shape2D
     * 
     * This is the constructor of Shape2D
     * 
     * @param int colorIndex, int xPos, int yPos, int width, int height
     */
    public Shape2D(int colorIndex, int xPos, int yPos, int width, int height) {
        this.fillColor = mapColor(colorIndex);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    /**
     * protected Color mapColor
     * 
     * This method contains the list of all the colors needed
     * to change the color of the shape we want.
     * 
     * @param int colorIndex
     */
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

    /**
     * public int getX
     * 
     * This will recieve the X value.
     * 
     * @return xPos
     */
    public int getX() { return xPos; }
    
    /**
     * public int getY
     * 
     * This will recieve the Y value.
     * 
     * @return yPos
     */
    public int getY() { return yPos; }
    
    /**
     * public int getWidth
     * 
     * This will recieve the width value.
     * 
     * @return width
     */
    public int getWidth() { return width; }
    
    /**
     * public int getHeight
     * 
     * This will recieve the height value.
     * 
     * @return height
     */
    public int getHeight() { return height; }
    
    /**
     * public void setPosition
     * 
     * This is where we will set both of the x and y values of the position.
     * 
     * @param int x, int y
     */
    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }
    
    /**
     * public void setSize
     * 
     * This is where we will set the size of the shape we want.
     * 
     * @param int width, height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * public abstract void draw
     * 
     * This will draw the shapes.
     * 
     * @param Graphics g
     */
    public abstract void draw(Graphics g);

    // Color constants
    public static final int RED = 0, GREEN = 1, BLUE = 2,
                          BLACK = 3, GRAY = 4, WHITE = 5,
                          YELLOW = 6, CYAN = 7, MAGENTA = 8,
                          BROWN = 9;
}