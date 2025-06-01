package piece;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
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
        legalMoves = new boolean[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
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

    public void drawMoves(Graphics2D g2, int[][] occupiedSquares) {
        computeLegalMoves(occupiedSquares);
        for (int i = 0; i < legalMoves.length; i++) {
            for (int j = 0; j < legalMoves.length; j++) {
                if (legalMoves[i][j]) {
                    drawDot(g2, i, j);
                }
            }
        }
    }

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
    abstract void computeLegalMoves(int[][] occupiedSquares);

    public void fillWithZeros() {
        for (boolean[] legalMove : legalMoves) {
            Arrays.fill(legalMove, false);
        }
    }

    public int checkLegalMoves(int[][] occupiedSquares, int row, int col) {
        if (occupiedSquares[row][col] == 0) {
            legalMoves[row][col] = true;
        } else if (occupiedSquares[row][col] == 1 && Objects.equals(this.color, "W")) { // white piece into white piece
            legalMoves[row][col] = false;
            return -1;
        } else if (occupiedSquares[row][col] == 1 && Objects.equals(this.color, "B")) { // black piece into white piece
            legalMoves[row][col] = true;
            return -1;
        } else if (occupiedSquares[row][col] == 2 && Objects.equals(this.color, "W")) { // white piece into black piece
            legalMoves[row][col] = true;
            return -1;
        } else if (occupiedSquares[row][col] == 2 && Objects.equals(this.color, "B")) { // black piece into black piece
            legalMoves[row][col] = false;
            return -1;
        }
        return 0;
    }

    public boolean isRowColWithinBounds(int row, int col) {
        if (row < 0 || row >= gamePanel.maxScreenRow) {
            return false;
        }
        if (col < 0 || col >= gamePanel.maxScreenCol) {
            return false;
        }
        return true;
    }

    public boolean isLegalMove(int col, int row) {
        if(legalMoves[row][col]) {
            return true;
        }
        return false;
    }

    public void movePieceLocation(int col, int row) {
        this.row = row;
        this.col = col;
    }


}
