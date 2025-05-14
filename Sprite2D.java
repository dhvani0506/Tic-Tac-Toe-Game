import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author (Dhvani & Brianna)
 * 
 * This is a subclass of Shape2D where the sprite is functioned.
 */
public class Sprite2D extends Shape2D {
    private BufferedImage[] frames;
    private int currentFrame;
    private boolean looping = true;

    /**
     * public Sprite2D
     * 
     * This is a constructor of Sprite2D class.
     * 
     * @param int xPos, int yPos, int width, int height, BufferedImage[] imageFrames
     */
    public Sprite2D(int xPos, int yPos, int width, int height, BufferedImage[] frames) {
        super(0, xPos, yPos, width, height);
        this.frames = frames;
        this.currentFrame = 0;
    }

    /**
     * public void Draw
     * 
     * This will draw the image of the selected sprite you want.
     * 
     * @param Graphics g
     */
    @Override
    public void draw(Graphics g) {
        if (frames != null && frames.length > 0) {
            g.drawImage(frames[currentFrame], xPos, yPos, width, height, null);
            if (looping) {
                currentFrame = (currentFrame + 1) % frames.length;
            } else if (currentFrame < frames.length - 1) {
                currentFrame++;
            }
        }
    }

    /**
     * public void setLooping
     * 
     * This will set the looping of this game.
     * 
     * @param boolean looping
     */
    public void setLooping(boolean looping) {
        this.looping = looping;
    }
}