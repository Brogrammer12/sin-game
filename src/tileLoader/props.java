package tileLoader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class props {
    public BufferedImage image;
    public int x, y, width, height;
    public boolean collision;
    public int propNum;
    public int firstgid;
    public int globalId;
    public HashMap<String, Object> state=new HashMap<>();
    public void applySavedState() {

    }
    public void draw(Graphics2D g2, int x, int y, int width, int height) {
        g2.drawImage(image, x, y, width, height, null);
    }
}
