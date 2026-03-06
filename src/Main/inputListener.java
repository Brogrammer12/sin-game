package Main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class inputListener implements KeyListener, MouseListener, MouseMotionListener{
public Rectangle mouseRect=new Rectangle(0, 0, 1, 1);
public int mouseX, mouseY;
public boolean mouseClicked=false;
public boolean hasPressed=false;
public boolean isValidChar=false;
public boolean[] keyCodes=new boolean[256];
public char lastTyped=0;
public boolean isSymbol(char c) {
    String symbols = "!@#$%^&*()-_=+[]{};:'\",.<>/?\\|`~";
    return symbols.indexOf(c) >= 0;
}
public boolean noKeyPressed() {
    for (int i = 0; i < keyCodes.length; i++) {
        if (keyCodes[i]) return false;
    }
    return true;

}
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

    // ignore control characters (ENTER, ESC, BACKSPACE, TAB, etc.)
    if (Character.isISOControl(c)) {
        return;
    }

    // store the typed character so the game loop can consume it
    lastTyped = c;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode=e.getKeyCode();
       keyCodes[keycode]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode=e.getKeyCode();
        keyCodes[keycode]=false;
        hasPressed=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked=false;
        hasPressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        mouseRect=new Rectangle(mouseX, mouseY, 1, 1);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        mouseRect=new Rectangle(mouseX, mouseY, 1, 1);
    }

}
