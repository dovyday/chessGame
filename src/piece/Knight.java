package piece;

import main.GamePanel;



public class Knight extends Piece {
    public Knight(int row, int col, String color, GamePanel gamePanel) {
        super(row, col, color, gamePanel);
    }

    @Override
    void computeLegalMoves(int[][] occupiedSquares) {
        fillWithZeros();

        int c = col;
        int r = row;

        int[] dr = { -2, -2, -1, -1, +1, +1, +2, +2 };
        int[] dc = { -1, +1, -2, +2, -2, +2, -1, +1 };

        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isRowColWithinBounds(nr, nc)) {
                checkLegalMoves(occupiedSquares, nr, nc);
            }
        }

    }

}
