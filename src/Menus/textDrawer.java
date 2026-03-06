package Menus;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.IOException;

public class textDrawer {
    FontMetrics fm;
    public int getTextWidth(Font font, String text, Graphics2D g2) {
        g2.setFont(font);
        fm=g2.getFontMetrics();
        int textWidth=fm.stringWidth(text);
        return textWidth;
    }
    public int getTextHeight(Font font, Graphics2D g2) {
        g2.setFont(font);
        fm=g2.getFontMetrics();
        int textHeight=fm.getHeight();
        return textHeight;
    }
    public void setFont(String Fonte, int size, int fontType, Graphics2D g2) {
        Font font=new Font(Fonte, fontType, size);
        g2.setFont(font);
    }
    public void drawText(int x, int y, Font font, String text, Graphics2D g2) {
        g2.setFont(font);
        g2.drawString(text, x, y);
    }
    public void setCustomFont(String location, float size, Graphics2D g2) {
        Font pixelFont;
            try {
                pixelFont = Font.createFont(
   Font.TRUETYPE_FONT,
   getClass().getResourceAsStream(location)
).deriveFont(size);
g2.setFont(pixelFont);
            } catch (FontFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    public int scalex(double scaleX, int x) {
        return (int) (scaleX*x);
    }
    public int scaley(double scaleY, int y) {
        return (int) (scaleY*y);
    }
    public int scalewidth(double scaleX, int width) {
        return (int) (scaleX*width);
    }
    public int scaleheight(double scaleY, int height) {
        return (int) (scaleY*height);
    }
}
