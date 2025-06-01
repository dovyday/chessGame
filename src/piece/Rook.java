package piece;

import main.GamePanel;

import java.awt.*;

public class Rook extends Piece {
    public Rook(int row, int col, String color, GamePanel gamePanel) {
        super(row, col, color, gamePanel);
    }

    @Override
    void computeLegalMoves(int[][] occupiedSquares) {
        fillWithZeros();

        int r = row;
        int c = col;


        int[] dr = { -1, +1,  0,  0 };
        int[] dc = {  0,  0, -1, +1 };

        for (int d = 0; d < 4; d++) {
            int step = 1;
            while (true) {
                int nr = r + dr[d] * step;
                int nc = c + dc[d] * step;

                if (!isRowColWithinBounds(nr, nc)) {
                    break;
                }

                int stopSignal = checkLegalMoves(occupiedSquares, nr, nc);
                if (stopSignal == -1) {
                    break;
                }
                step++;
            }
        }
    }



}
