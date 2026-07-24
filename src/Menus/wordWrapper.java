package Menus;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import Main.gamepanel;

public class wordWrapper {
    //draws animated text to the screen. Pretty neat, right? I yoinked it from a different game
    public int wordStage=0;
    public int charat=0;
    public String previousText;
    public void drawAll(int x, int y, String dialogueText, Graphics2D g2) {
        if (!dialogueText.equals(previousText)) {
            charat=0;
            wordStage=0;
            previousText=dialogueText;
        }
        FontMetrics fm=g2.getFontMetrics();
        char[] E=dialogueText.toCharArray();
        String[] text=dialogueText.split(" ");
            for (int i=0; i<=charat; i++) {
                String finalString=String.valueOf(E[i]);
            g2.drawString(finalString, x, y);
            x+=fm.stringWidth(String.valueOf(E[i]));
            if (String.valueOf(E[i])==" ") {
                x+=fm.stringWidth(" ");
                wordStage++;
            }
            if (x+fm.stringWidth(text[wordStage])>=gamepanel.GAME_WIDTH) {
                x=100;
                y+=fm.getHeight();
            }
            if (x<0) {
                x=0;
            }
            }
            if (charat<E.length-1) {
                charat++;
            }
        previousText=dialogueText;
    }
}
