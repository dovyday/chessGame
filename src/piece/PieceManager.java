package piece;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class PieceManager {
    GamePanel gamePanel;
    ArrayList<Piece> pieceList = new ArrayList<>();


    public PieceManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initPieces();
    }

    public void initPieces() {
        pieceList.add(new Bishop(7, 2, "W"));
        pieceList.add(new Bishop(7, 5, "W"));
        pieceList.add(new Bishop(0, 2, "B"));
        pieceList.add(new Bishop(0, 5, "B"));
        pieceList.add(new Rook(7, 0, "W"));
        pieceList.add(new Rook(7, 7, "W"));
        pieceList.add(new Rook(0, 0, "B"));
        pieceList.add(new Rook(0, 7, "B"));
        pieceList.add(new Queen(7, 3, "W"));
        pieceList.add(new Queen(0, 3, "B"));
        pieceList.add(new King(7, 4, "W"));
        pieceList.add(new King(0, 4, "B"));
        pieceList.add(new Knight(7, 1, "W"));
        pieceList.add(new Knight(7, 6, "W"));
        pieceList.add(new Knight(0, 1, "B"));
        pieceList.add(new Knight(0, 6, "B"));
        for (int i = 0; i < 8; i++) {
            pieceList.add(new Pawn(1, i, "B"));
            pieceList.add(new Pawn(6, i, "W"));
        }

    }

    public void draw(Graphics2D g2) {
        int tileSize = gamePanel.getTileSize();
        for (Piece piece : pieceList) {
            g2.drawImage(piece.image, piece.row * tileSize, piece.col * tileSize, tileSize, tileSize,null);
        }

    }

    public boolean isPieceSelected(Point p) {
        int col = (int) p.y / gamePanel.getTileSize();
        int row = (int) p.x / gamePanel.getTileSize();
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row) {
                return true;
            }
        }
        return false;
    }


}
