package tile;

import main.GamePanel;
import piece.PieceManager;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public int[][] mapTileNum;
    public Tile[] tile;
    static int tileSize;
    boolean pieceSelected = false;
    Point pieceLocation;
    PieceManager pieceM;

    public TileManager(GamePanel gp, PieceManager pieceM) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        tile = new Tile[2];
        loadMap("/board/map.txt");
        loadTiles();
        tileSize = gp.getTileSize();
        this.pieceM = pieceM;
    }

    public void loadTiles() {
        tile[0] = new Tile();
        tile[0].setColor(new Color(0, 100, 0));
        tile[1] = new Tile();
        tile[1].setColor(new Color(255, 255, 255));
    }

    public void loadMap(String mapPath) {
        try {
            InputStream is  = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawBoardOnly(Graphics2D g2) {
        for (int row = 0; row < gp.maxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                int tileNum = mapTileNum[row][col];
                if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                    g2.setColor(tile[tileNum].getColor());
                    g2.fillRect(
                            col * gp.tileSize,
                            row * gp.tileSize,
                            gp.tileSize,
                            gp.tileSize
                    );
                }
            }
        }
    }

    public void drawHighlight(Graphics2D g2) {
        if (!pieceSelected) return;

        g2.setColor(new Color(255, 223, 93));
        g2.fillRect(getColFromPoint(pieceLocation) * tileSize, getRowFromPoint(pieceLocation) * tileSize, gp.tileSize, gp.tileSize);

    }

    public void drawMove(Graphics2D g2) {
        if (!pieceSelected) return;

        pieceM.drawPieceMoves(g2, pieceLocation);
    }


    public int getRowFromPoint(Point p) {
        return (int) p.y / tileSize;
    }
    public int getColFromPoint(Point p) {
        return (int) p.x / tileSize;
    }
    public void setPieceSelected(boolean selected) {
        this.pieceSelected = selected;
    }
    public boolean isPieceSelected() {
        return pieceSelected;
    }
    public Point getPoint() { return this.pieceLocation; }

    public void pieceSelected(Point p) {
        setPieceSelected(true);
        if (pieceLocation == null) {
            pieceLocation = p;
            System.out.println("pieceLocation was null");
        } else if(!checkIfTheSamePiece(p)) {
            pieceLocation = p;
            System.out.println("paspaude ant kito pieco kol active sitas");
        } else if (checkIfTheSamePiece(p)) {
          setPieceSelected(false);
          System.out.println("piece unselected");
          pieceLocation = null;
        }


    }
    public boolean checkIfTheSamePiece(Point newPoint) {
        return getRowFromPoint(pieceLocation) == getRowFromPoint(newPoint) && getColFromPoint(pieceLocation) == getColFromPoint(newPoint);
    }



}
