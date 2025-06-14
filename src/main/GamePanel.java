package main;

import piece.MoveManager;
import piece.Piece;
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
    PieceManager pieceM = new PieceManager(this);
    TileManager tileM = new TileManager(this, pieceM);
    MoveManager moveM = new MoveManager(this, tileM, pieceM);

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

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        tileM.drawBoardOnly(g2);

        tileM.drawHighlight(g2);

        pieceM.draw(g2);

        tileM.drawMove(g2);


        g2.dispose();

    }

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();
        Piece clickedPiece = pieceM.getPieceFromLocation(clickPoint);

        // Case 1: No piece is selected yet
        if (! tileM.isPieceSelected()) {
            if (clickedPiece != null) {
                // We clicked on a piece → select it
                tileM.pieceSelected(clickPoint);
            }
            // else: clicked on empty square while no piece is selected → do nothing
            return;
        }

        // Case 2: A piece is already selected
        // “currentlySelectedPoint” is the location of the piece we had selected before
        Point currentlySelectedPoint = tileM.getPoint();

        if (clickedPiece != null) {
            // We clicked on another piece (or the same one):
            // Let TileManager.pieceSelected(...) handle toggling or switching
            tileM.pieceSelected(clickPoint);
        }
        else {
            // We clicked on an empty square → attempt to move the previously selected piece
            moveM.movePiece(clickPoint);
            // In any case, clear the selection after attempting the move:
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
