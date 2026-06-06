package tileLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.gamepanel;

public class tableWithBook extends props implements Interactable{
gamepanel gp;
String[] text;
String name;
int page=-1;
    public tableWithBook(gamepanel gp, String text, String name) {
        this.gp=gp;
        this.text=text.split("/");
        this.name=name;
    }
    @Override
    public void onInteract(gamepanel gp) {
        if (gp.listener.keyCodes[KeyEvent.VK_E]==true && gp.listener.hasPressed==false) {
            turnPage();
            gp.listener.hasPressed=true;
        }
    }
    @Override
    public void applySavedState() {

    }

    public void turnPage() {
        if (page == -1) {
    page = 0; // open book
    gp.p1.disableMovement();
} else {
    page++;
    if (page >= text.length) {
        page = -1; // close book
        gp.p1.enableMovement();
    }
}
    }
    @Override
public void draw(Graphics2D g2, int x, int y, int width, int height) {
    super.draw(g2, x, y, width, height);
    drawText(g2);
}
public void drawText(Graphics2D g2) {
    if (page!=-1) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        g2.setColor(Color.WHITE);
        gp.wordBoi.drawAll(10, 35, text[page], g2);
    }
}

}
