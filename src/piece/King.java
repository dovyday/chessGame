package piece;

import main.GamePanel;


public class King extends Piece {

    public King(int row, int col, String color, GamePanel gamePanel) {
        super(row, col, color, gamePanel);
    }

    @Override
    void computeLegalMoves(int[][] occupiedSquares) {
        fillWithZeros();
        int r = row;
        int c = col;

        int[] dr = { -1, -1, -1,  0,  0,  1,  1,  1 };
        int[] dc = { -1,  0,  1, -1,  1, -1,  0,  1 };

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isRowColWithinBounds(nr, nc)) {
                checkLegalMoves(occupiedSquares, nr, nc);
            }
        }
    }
}
