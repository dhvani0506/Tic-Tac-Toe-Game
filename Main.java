import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    private static JFrame frame;
    private static GamePanel panel;
    private static boolean isFullScreen = false;
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new GamePanel();
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    toggleFullScreen();
                }
            }
        });
    }

    private static void toggleFullScreen() {
        if (isFullScreen) {
            device.setFullScreenWindow(null);
            frame.getContentPane().setPreferredSize(new Dimension(600, 600)); // Restore preferred size
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the window
        } else {
            frame.getContentPane().setPreferredSize(null); // Clear preferred size for full-screen
            frame.pack(); // Repack to update frame size
            device.setFullScreenWindow(frame);
        }
        isFullScreen = !isFullScreen;
    }
}