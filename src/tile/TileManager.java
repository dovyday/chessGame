package tile;

import main.GamePanel;

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

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        tile = new Tile[2];
        loadMap("/board/map.txt");
        loadTiles();
        tileSize = gp.getTileSize();
    }

    public void loadTiles() {
        tile[0] = new Tile();
        tile[0].setColor(new Color(0, 100, 0));
        tile[1] = new Tile();
        tile[1].setColor(new Color(255, 255, 255));
    }

    public void loadMap(String mapPath) {

        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        for(int row = 0; row < gp.maxScreenRow; row++) {
            for(int col = 0; col < gp.maxScreenCol; col++) {
                int tileNum = mapTileNum[row][col]; // Note: row first
                if(tileNum >= 0 && tileNum < gp.tileSize && tile[tileNum] != null) {
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
        if (pieceSelected) {
            g2.setColor(new Color(255, 223, 93));
            g2.fillRect(getRowFromPoint(pieceLocation) * tileSize, getColFromPoint(pieceLocation) * tileSize, gp.tileSize, gp.tileSize);
        }
    }



    public int getRowFromPoint(Point p) {
        return (int) p.x / tileSize;
    }

    public int getColFromPoint(Point p) {
        return (int) p.y / tileSize;
    }
    public void setPieceSelected(boolean selected) {
        this.pieceSelected = selected;
    }
    public boolean isPieceSelected() {
        return pieceSelected;
    }
    public void getPoint(Point p) {
        this.pieceLocation = p;
    }

    public void pieceSelected(Point p) {
        setPieceSelected(true);
        if (pieceLocation == null) {
            pieceLocation = p;
            System.out.println("pieceLocation was null");
        }
        else if(!checkIfTheSamePiece(p)) {
            pieceLocation = p;
            System.out.println("paspaude ant kito pieco kol active sitas");
        }


    }
    public boolean checkIfTheSamePiece(Point newPoint) {
        return getRowFromPoint(pieceLocation) == getRowFromPoint(newPoint) && getColFromPoint(pieceLocation) == getColFromPoint(newPoint);
    }

}
