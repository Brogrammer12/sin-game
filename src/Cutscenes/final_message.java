package Cutscenes;

import Main.gameState;
import Main.gamepanel;
import Menus.timer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
public class final_message extends cutsceneSuperclass{
    gamepanel gp;
    timer timer1;
    timer timer2;
    timer timer3;
    timer timer4;
    public final_message(gamepanel gp) {
        this.gp=gp;
        timer1=new timer();
        timer2=new timer();
        timer3=new timer();
        timer4=new timer();
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/final.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void runCutscene(Graphics2D g2) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 48f, g2);
        Font font=g2.getFont();
        FontMetrics fm=g2.getFontMetrics();
            int height=gp.tDrawer.getTextHeight(font, g2);
             boolean done=timer1.wait(2.5, gp.delta);
             if (done==true) {
                boolean done2=timer2.wait(2.5, gp.delta);
                if (done2==true) {
                    boolean done3=timer3.wait(2.5, gp.delta);
                    if (done3==true) {
                        int width=fm.stringWidth("ITS YOUR TURN.");
                gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-width/2, gamepanel.GAME_HEIGHT/2-height, "ITS YOUR TURN.", g2);
                boolean done4=timer4.wait(2.5, gp.delta);
                if (done4==true) {
                    gp.state=gameState.GAMEPLAY;
                }
                    }
                    else {
                        int width=fm.stringWidth("AND NOW...");
                gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-width/2, gamepanel.GAME_HEIGHT/2-height, "AND NOW...", g2);
                    }
                }
                else {
                    int width=fm.stringWidth("ALL OTHERS HAVE FALLEN.");
                gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-width/2, gamepanel.GAME_HEIGHT/2-height, "ALL OTHERS HAVE FALLEN.", g2);
                }
             }
             else if(done==false) {
                int width=fm.stringWidth("YOU ARE THE ONLY ONE LEFT UNCORRUPTED.");
                gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-width/2, gamepanel.GAME_HEIGHT/2-height, "YOU ARE THE ONLY ONE LEFT UNCORRUPTED.", g2);
             }
    }
}
