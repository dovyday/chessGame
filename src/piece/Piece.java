package piece;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Piece {

    public BufferedImage image;
    public int col;
    public int row;
    String color;
    String name;

    public Piece(int col, int row, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
        initImage();
    }

    public void initImage() {
        Class<? extends Piece> c = getClass();
        String name = c.getSimpleName();
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pieces/" + name + color + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
