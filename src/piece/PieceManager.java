package piece;

import main.GamePanel;
import tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PieceManager {
    GamePanel gamePanel;
    ArrayList<Piece> pieceList = new ArrayList<>();
    int tileSize;
    int[][] occupiedSquares;

    public PieceManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tileSize = gamePanel.getTileSize();
        occupiedSquares = new int[8][8];
        initPieces();
        rebuildOccupied();
    }

    public void initPieces() {
        pieceList.add(new Rook(7, 0, "W", gamePanel));   // bottom-left corner
        pieceList.add(new Knight(7, 1, "W", gamePanel));
        pieceList.add(new Bishop(7, 2, "W", gamePanel));
        pieceList.add(new Queen(7, 3, "W", gamePanel));
        pieceList.add(new King(7, 4, "W", gamePanel));
        pieceList.add(new Bishop(7, 5, "W", gamePanel));
        pieceList.add(new Knight(7, 6, "W", gamePanel));
        pieceList.add(new Rook(7, 7, "W", gamePanel));   // bottom-right corner

        // ── White pawns on row 6 ─────────────────────────────────────
        for (int col = 0; col < 8; col++) {
            pieceList.add(new Pawn(6, col, "W", gamePanel));
        }

        // ── Black pawns on row 1 ─────────────────────────────────────
        for (int col = 0; col < 8; col++) {
            pieceList.add(new Pawn(1, col, "B", gamePanel));
        }

        // ── Black major pieces on row 0 ───────────────────────────────
        pieceList.add(new Rook(0, 0, "B", gamePanel));   // top-left corner
        pieceList.add(new Knight(0, 1, "B", gamePanel));
        pieceList.add(new Bishop(0, 2, "B", gamePanel));
        pieceList.add(new Queen(0, 3, "B", gamePanel));
        pieceList.add(new King(0, 4, "B", gamePanel));
        pieceList.add(new Bishop(0, 5, "B", gamePanel));
        pieceList.add(new Knight(0, 6, "B", gamePanel));
        pieceList.add(new Rook(0, 7, "B", gamePanel));   // top-right corner

    }

    public void draw(Graphics2D g2) {
        int tileSize = gamePanel.getTileSize();
        for (Piece piece : pieceList) {
            g2.drawImage(piece.image, piece.col * tileSize, piece.row * tileSize, tileSize, tileSize,null);
        }

    }

    public boolean isPieceSelected(Point p) {
        int col = (int) p.x / gamePanel.getTileSize();
        int row = (int) p.y / gamePanel.getTileSize();
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row) {
                return true;
            }
        }
        return false;
    }
    public void drawPieceMoves(Graphics2D g2, Point pieceLocation) {
        Piece selectedPiece = getPieceFromLocation(pieceLocation);
        if (selectedPiece == null) {
            System.out.println("Piece Not found");
        }
        selectedPiece.drawMoves(g2);

    }
    public Piece getPieceFromLocation(Point pieceLocation) {
        for (Piece piece : pieceList) {
            if ((int) pieceLocation.y / tileSize == piece.row && (int)pieceLocation.x / tileSize == piece.col) {
                return piece;
            }
        }
        return null;
    }

    public void rebuildOccupied() {
        // 1) Clear every cell to false
        for (int r = 0; r < 8; r++) {
            Arrays.fill(occupiedSquares[r], 0);
        }

        // 2) Mark each piece’s current square as occupied
        for (Piece p : pieceList) {
            if (Objects.equals(p.color, "W")) {
                occupiedSquares[p.row][p.col] = 1;
            }
            else {
                occupiedSquares[p.row][p.col] = 2;
            }
        }
    }


}
