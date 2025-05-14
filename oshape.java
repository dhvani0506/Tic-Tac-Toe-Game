import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * @author (Dhvani & Brianna)
 * 
 * This is the oShape class that makes up the shape of O, that extends from Shape2D class.
 */
public class oshape extends Shape2D {

    /**
     * public oshape
     * 
     * This is the constructor.
     * 
     * @param int xPos, int yPos, int width, int height
     */
    public oshape(int xPos, int yPos, int width, int height) {
        super(Shape2D.RED, xPos, yPos, width, height);
    }

    /**
     * public void draw
     * 
     * This will draw out the shape of O.
     * 
     * @param Graphics g
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(fillColor);
        
        int padding = width / 4;
        g2d.drawOval(xPos + padding, yPos + padding, 
                    width - 2*padding, height - 2*padding);
    }
}