import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class XShape extends Shape2D {
    public XShape(int xPos, int yPos, int width, int height) {
        super(Shape2D.BLUE, xPos, yPos, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(fillColor);
        
        int padding = width / 4;
        g2d.drawLine(xPos + padding, yPos + padding, 
                    xPos + width - padding, yPos + height - padding);
        g2d.drawLine(xPos + padding, yPos + height - padding, 
                    xPos + width - padding, yPos + padding);
    }
}