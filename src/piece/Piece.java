package piece;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public abstract class Piece {

    public BufferedImage image;
    public int row; // y (rank)
    public int col; // x (file)
    String color;
    GamePanel gamePanel;
    boolean[][] legalMoves;

    public Piece(int row, int col, String color, GamePanel gamePanel) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.gamePanel = gamePanel;
        legalMoves = new boolean[row][col];
        initImage();
    }

    public void initImage() {
        Class<? extends Piece> c = getClass();
        String name = c.getSimpleName();
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pieces/" + name + color + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract void drawMoves(Graphics2D g2);

    void drawDot(Graphics2D g2, int boardRow, int boardCol) {
        int ts = gamePanel.getTileSize();

        // ─── Adjust this line to change the dot’s radius ─────────────────
        int radius = ts / 8;   // e.g. ts/8 for small, ts/6 for medium, ts/4 for large
        // ─────────────────────────────────────────────────────────────────

        // Center of the square in pixel coordinates:
        int cx = boardCol * ts + ts / 2;
        int cy = boardRow * ts + ts / 2;

        // Chess.com–style blue: RGB (89, 173, 255)
        Color dotColor = new Color(155, 172, 188, 255);

        g2.setColor(dotColor);
        int diameter = radius * 2;
        g2.fillOval(cx - radius, cy - radius, diameter, diameter);

    }
    public void computeLegalMoves(boolean[][] occupiedSquares) {

    }


}
