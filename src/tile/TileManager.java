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

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        tile = new Tile[2];
        loadMap("/board/map.txt");
        loadTiles();
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
    }

}
