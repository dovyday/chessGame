package piece;

import main.GamePanel;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop(int row, int col, String pieceColor, GamePanel gamePanel) {
        super(row, col, pieceColor, gamePanel);
    }

    public void drawMoves(Graphics2D g2) {
        // north-west
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            drawDot(g2, r, c);
        }

        // north-east
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            drawDot(g2, r, c);
        }

        // south-west
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            drawDot(g2, r, c);
        }

        // south-east
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            drawDot(g2, r, c);
        }
    }

    @Override
    public void computeLegalMoves(boolean[][] occupiedSquares) {
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (occupiedSquares[r][c]) {}
        }

        // north-east
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {

        }

        // south-west
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {

        }

        // south-east
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {

        }
    }


}
