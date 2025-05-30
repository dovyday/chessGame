package main;

import piece.PieceManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable, MouseListener {
    // SCREEN SETTINGS
    final int originalTileSize = 50;
    final double scale = 1.2;

    public final int tileSize = (int) (originalTileSize * scale);
    public final int maxScreenCol = 8;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    Thread gameThread;
    TileManager tileM = new TileManager(this);
    PieceManager pieceM = new PieceManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseListener(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
//                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >=  1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        pieceM.draw(g2);


        g2.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (pieceM.isPieceSelected(p)) {
            tileM.pieceSelected(p);
        }
        else {
            tileM.setPieceSelected(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
