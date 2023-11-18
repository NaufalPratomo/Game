import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SoccerGame extends JFrame implements ActionListener, KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SPEED = 5;
    private static final int WINNING_SCORE = 10;

    private int player1Y = HEIGHT / 2;
    private int player2Y = HEIGHT / 2;
    private int ballX = WIDTH / 2;
    private int ballY = HEIGHT / 2;
    private int ballSpeedX = 3; // Adjusted speed
    private int ballSpeedY = 3; // Adjusted speed
    private int player1Score = 0;
    private int player2Score = 0;

    public SoccerGame() {
        setTitle("Soccer Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(this);
        setFocusable(true);

        Timer timer = new Timer(16, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        // Update ball position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Check for collisions with walls
        if (ballX <= 0) {
            // Player 2 scores
            player2Score++;
            checkGameOver();
        } else if (ballX >= WIDTH - 20) {
            // Player 1 scores
            player1Score++;
            checkGameOver();
        }

        if (ballY <= 0 || ballY >= HEIGHT - 20) {
            ballSpeedY = -ballSpeedY;
        }

        // Check for collisions with players
        if (ballX <= 20 && (ballY >= player1Y && ballY <= player1Y + 80)) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballX >= WIDTH - 40 && (ballY >= player2Y && ballY <= player2Y + 80)) {
            ballSpeedX = -ballSpeedX;
        }
    }

    private void checkGameOver() {
        if (player1Score == WINNING_SCORE || player2Score == WINNING_SCORE) {
            JOptionPane.showMessageDialog(this, "Game Over! Player " + (player1Score == WINNING_SCORE ? "1" : "2") + " wins!");
            resetGame();
        }
    }

    private void resetGame() {
        player1Score = 0;
        player2Score = 0;
        ballX = WIDTH / 2;
        ballY = HEIGHT / 2;
        ballSpeedX = 5; // Adjusted speed
        ballSpeedY = 5; // Adjusted speed
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRect(20, player1Y, 20, 80);
        g.fillRect(WIDTH - 40, player2Y, 20, 80);

        g.fillOval(ballX, ballY, 20, 20);

        // Display scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Player 1: " + player1Score, 50, 50);
        g.drawString("Player 2: " + player2Score, WIDTH - 150, 50);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && player2Y > 0) {
            player2Y -= PLAYER_SPEED;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && player2Y < HEIGHT - 80) {
            player2Y += PLAYER_SPEED;
        } else if (e.getKeyCode() == KeyEvent.VK_W && player1Y > 0) {
            player1Y -= PLAYER_SPEED;
        } else if (e.getKeyCode() == KeyEvent.VK_S && player1Y < HEIGHT - 80) {
            player1Y += PLAYER_SPEED;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SoccerGame().setVisible(true));
    }
}
