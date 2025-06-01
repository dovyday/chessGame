package piece;

import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class Bishop extends Piece {

    public Bishop(int row, int col, String pieceColor, GamePanel gamePanel) {
        super(row, col, pieceColor, gamePanel);
    }


    @Override
    public void computeLegalMoves(int[][] occupiedSquares) {
        fillWithZeros();
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if(checkLegalMoves(occupiedSquares, r, c) == -1) {
                break;
            }
        }

        // north-east
        for (int r = row - 1, c = col + 1; r >= 0 && c < 8; r--, c++) {
            if(checkLegalMoves(occupiedSquares, r, c) == -1) {
                break;
            }
        }

        // south-west
        for (int r = row + 1, c = col - 1; r < 8 && c >= 0; r++, c--) {
            if(checkLegalMoves(occupiedSquares, r, c) == -1) {
                break;
            }
        }

        // south-east
        for (int r = row + 1, c = col + 1; r < 8 && c < 8; r++, c++) {
            if(checkLegalMoves(occupiedSquares, r, c) == -1) {
                break;
            }
        }
    }


}
