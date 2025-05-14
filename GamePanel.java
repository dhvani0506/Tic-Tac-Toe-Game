import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private Board board;
    private AIPlayer ai;
    private boolean playerTurn;
    private String statusMessage;
    private int playerScore;
    private int aiScore;
    private BufferedImage backgroundImage;
    private Timer aiTimer;
    private String playerName = "Player";
    private Clip xSound, oSound, winSound, drawSound;

    // Monochromatic color palette
    private final Color PLAYER_COLOR = new Color(255, 165, 0);    // Orange (X)
    private final Color AI_COLOR = new Color(34, 139, 34);        // (O)
    private final Color BACKGROUND_COLOR = new Color(20, 20, 20); // Near-Black
    private final Color GRID_COLOR = new Color(70, 70, 70);       // Slate Gray
    private final Color SCORE_PANEL = new Color(35, 35, 35);      // Slightly Warmer Charcoal
    private final Color TEXT_COLOR = new Color(255, 223, 0);      // Golden Yellow
    private final Color STATUS_COLOR = new Color(192, 192, 192);  // Silver

    public GamePanel() {
        // Get player name
        playerName = JOptionPane.showInputDialog(this, "Enter your name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player";
        }

        loadSounds();
        initializeGame();
    }

    private void loadSounds() {
        try {
            xSound = loadSound("/Sound.wav");
            oSound = loadSound("/Sound.wav");
            winSound = loadSound("/Winning.wav");
            drawSound = loadSound("/Draw.wav");
        } catch (Exception e) {
            System.out.println("Some sounds couldn't be loaded. Game will continue without them.");
        }
    }

    private Clip loadSound(String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    private void initializeGame() {
        board = new Board();
        ai = new AIPlayer('O');
        playerTurn = true;
        statusMessage = playerName + "'s Turn (X)";
        playerScore = 0;
        aiScore = 0;

        // Initialize solid white background
        backgroundImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = backgroundImage.createGraphics();
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, 800, 800);
        g2d.dispose();

        setPreferredSize(new Dimension(600, 650));
        setFocusable(true);

        aiTimer = new Timer(800, e -> makeAIMove());
        aiTimer.setRepeats(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePlayerMove(e);
            }
        });
    }

    private void handlePlayerMove(MouseEvent e) {
        if (!playerTurn || board.checkWinner() != ' ' || board.isFull()) return;

        int cellWidth = getWidth() / 3;
        int cellHeight = (getHeight() - 50) / 3;
        int col = e.getX() / cellWidth;
        int row = e.getY() / cellHeight;

        if (row < 3 && col < 3 && board.makeMove(row, col, 'X')) {
            playSound(xSound);
            playerTurn = false;
            updateGameState();
            repaint();

            if (board.checkWinner() == ' ' && !board.isFull()) {
                aiTimer.start();
            }
        }
    }

    private void makeAIMove() {
        if (!playerTurn && board.checkWinner() == ' ' && !board.isFull()) {
            ai.makeMove(board);
            playSound(oSound);
            playerTurn = true;
            updateGameState();
            repaint();
        }
    }

    private void updateGameState() {
        char winner = board.checkWinner();
        if (winner != ' ') {
            if (winner == 'X') {
                statusMessage = playerName + " Wins!";
                playerScore++;
                playSound(winSound);
            } else {
                statusMessage = "AI Wins!";
                aiScore++;
                playSound(winSound);
            }
            showGameOverDialog();
        } else if (board.isFull()) {
            statusMessage = "Draw!";
            playSound(drawSound);
            showGameOverDialog();
        } else {
            statusMessage = playerTurn ? playerName + "'s Turn (X)" : "AI Thinking...";
        }
    }

    private void showGameOverDialog() {
        String message = statusMessage + "\n" + playerName + ": " + playerScore + " - AI: " + aiScore + "\nPlay again?";
        int response = JOptionPane.showConfirmDialog(
            this,
            message,
            "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            board.reset();
            playerTurn = true;
            statusMessage = playerName + "'s Turn (X)";
            repaint();
        } else {
            System.exit(0);
        }
    }

    private void playSound(Clip sound) {
        try {
            if (sound != null) {
                sound.setFramePosition(0);
                sound.start();
            }
        } catch (Exception e) {
            System.out.println("Error playing sound");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        int width = getWidth();
        int height = getHeight() - 50;

        // Draw grid with light gray lines
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(GRID_COLOR);
        
        // Vertical lines
        g2d.drawLine(width/3, 0, width/3, height);
        g2d.drawLine(2*width/3, 0, 2*width/3, height);
        
        // Horizontal lines
        g2d.drawLine(0, height/3, width, height/3);
        g2d.drawLine(0, 2*height/3, width, 2*height/3);

        // Draw X's and O's with black and gray
        int cellWidth = width / 3;
        int cellHeight = height / 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char cell = board.getCell(row, col);
                int x = col * cellWidth;
                int y = row * cellHeight;
                
                if (cell == 'X') {
                    drawX(g2d, x, y, cellWidth, cellHeight);
                } else if (cell == 'O') {
                    drawO(g2d, x, y, cellWidth, cellHeight);
                }
            }
        }

        // Draw score panel with off-white background
        g2d.setColor(SCORE_PANEL);
        g2d.fillRect(0, height, width, 50);
        
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        g2d.drawString(playerName + " (X): " + playerScore, 20, height + 30);
        g2d.drawString("AI (O): " + aiScore, width - 150, height + 30);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(STATUS_COLOR);
        g2d.drawString(statusMessage, width/2 - 80, height + 30);
    }

    private void drawX(Graphics2D g, int x, int y, int width, int height) {
        int padding = width / 4;
        g.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(PLAYER_COLOR);
        g.drawLine(x + padding, y + padding, x + width - padding, y + height - padding);
        g.drawLine(x + padding, y + height - padding, x + width - padding, y + padding);
    }

    private void drawO(Graphics2D g, int x, int y, int width, int height) {
        int padding = width / 4;
        g.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(AI_COLOR);
        g.drawOval(x + padding, y + padding, width - 2*padding, height - 2*padding);
    }
}