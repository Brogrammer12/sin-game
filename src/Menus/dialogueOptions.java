package Menus;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.gamepanel;
public class dialogueOptions {
    //reusable UI that shows a piece of dialogue with a list of options. Handles Rectangles automatically, thank u jesus
    public String[] E;
    public Rectangle[] A;
    public wordWrapper wBoi;
    public wordWrapper[] wBois;
    public dialogueOptions(String[] options) {
        E=options;
        wBois=new wordWrapper[E.length];
        wBoi=new wordWrapper();
        for (int i=0; i<E.length; i++) {
            wBois[i]=new wordWrapper();
        }
    }
    public void dialogueWithOption(Graphics2D g2, wordWrapper wordboi, String text, int x, int y, double scaleX, double scaleY) {
        A=new Rectangle[E.length];
        g2.setColor(Color.WHITE);
        Font font=g2.getFont();
        FontMetrics fm=g2.getFontMetrics();
        wBoi.drawAll(x-fm.stringWidth(text)/2, y, text, g2);
        String completeString="";
        for (int i=0; i<E.length; i++) {
            completeString+=E[i];
        }
        int textCenter = x + fm.stringWidth(text) / 2;
        int optionsWidth = fm.stringWidth(completeString);
        int buffer=20*(E.length-1);
        int startX =(int) (scaleX*((x-fm.stringWidth(completeString)/2)-buffer));
        A[0]=new Rectangle();
        A[0].x=startX;
        A[0].y=(int) (scaleY*(y+2*fm.getHeight()));
        A[0].width=(int) (scaleY*(fm.stringWidth(E[0])));
        A[0].height=(int) (scaleY*fm.getHeight());
        int nextX=startX;
        for (int i=0; i<A.length; i++) {
            if (i!=0) {
                A[i]=new Rectangle();
                A[i].x=nextX;
                A[i].y=(int) (scaleY*(y+2*fm.getHeight()));
                A[i].width=(int) (scaleX*(fm.stringWidth(E[i])));
            A[i].height=(int) (scaleY*fm.getHeight());
                wBois[i].drawAll((int) (nextX/scaleX), (int) ((A[i].y+fm.getHeight())/scaleY), E[i], g2);
                nextX+=(int) (scaleX*(fm.stringWidth(E[i])+20));
            }
            else {
                wBois[0].drawAll((int) (nextX/scaleX), (int) ((A[0].y+fm.getHeight())/scaleY), E[0], g2);
                nextX+=(int) (scaleX*(fm.stringWidth(E[0])+20));
            }
        }
    }
    public Rectangle getRectangle(int index) {
        return A[index];
    }
}
