package piece;

import main.GamePanel;

import java.awt.*;

public class Pawn extends Piece {

    boolean firstMove = true;

    public Pawn(int row, int col, String color, GamePanel gamePanel) {
        super(row, col, color, gamePanel);
    }

    @Override
    void computeLegalMoves(int[][] occupiedSquares) {
        fillWithZeros();

        int r = row;
        int c = col;
        int dir;         // “direction” of forward motion: white moves up (–1), black moves down (+1)
        int enemyCode;   // what occupiedSquares value counts as an enemy

        if ("W".equals(color)) {
            dir = -1;
            enemyCode = 2;   // black pieces are coded as 2
        } else {
            dir = +1;
            enemyCode = 1;   // white pieces are coded as 1
        }

        // ─── 1) One‐square forward ───────────────────────────────────────────
        int oneStepRow = r + dir;
        int oneStepCol = c;
        if (isRowColWithinBounds(oneStepRow, oneStepCol)) {
            // Only legal if the square directly in front is empty (0)
            if (occupiedSquares[oneStepRow][oneStepCol] == 0) {
                legalMoves[oneStepRow][oneStepCol] = true;

                // ─── 2) Two‐square forward (only if firstMove && both squares empty) ───
                int twoStepRow = r + (2 * dir);
                if (firstMove
                        && isRowColWithinBounds(twoStepRow, oneStepCol)
                        && occupiedSquares[twoStepRow][oneStepCol] == 0
                        && occupiedSquares[oneStepRow][oneStepCol] == 0)
                {
                    legalMoves[twoStepRow][oneStepCol] = true;
                }
            }
        }

        // ─── 3) Diagonal captures (one square forward, then left/right) ────────
        // Check (r + dir, c - 1) and (r + dir, c + 1)
        int[] dcCapture = { -1, +1 };
        for (int dcol : dcCapture) {
            int capRow = r + dir;
            int capCol = c + dcol;
            if (isRowColWithinBounds(capRow, capCol)) {
                // Only legal if there is an enemy piece there
                if (occupiedSquares[capRow][capCol] == enemyCode) {
                    legalMoves[capRow][capCol] = true;
                }
            }
        }
    }
}
