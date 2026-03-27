package Cutscenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gamepanel;
import Menus.fadeBlack;
import Menus.timer;

public class meteorFell extends cutsceneSuperclass{
    gamepanel gp;
    timer timer1;
    timer timer2;
    public int fCode;
    fadeBlack fBlack;
    public meteorFell(gamepanel gp) {
        this.gp=gp;
        timer1=new timer();
        timer2=new timer();
        fBlack=new fadeBlack(gp);
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/attempt 2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void runCutscene(Graphics2D g2) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        Font font=g2.getFont();
            int height=gp.tDrawer.getTextHeight(font, g2);
            g2.setColor(Color.WHITE);
        g2.drawImage(cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
        boolean done=timer1.wait(2.5, gp.delta);
        if (done==true) {
            boolean wut=timer2.wait(2, gp.delta);
            gp.wordBoi.drawAll(10, height, "There was some type of special crystal inside of the meteor.", g2);
            if (wut==true) {
                if (fCode>=1) {
                //gp.cManager.early_cutscene("researchLab", g2);
            }
            if (fCode==3) {
                //gp.currentCutscene="researchLab";
            }
                int fadeCode=fBlack.fadeToBlack(0.5, g2);
            fCode=fadeCode;
            }
        }
        else if(done==false) {
            gp.wordBoi.drawAll(10, height, "But it wasn't just any meteor. It glowed a bright white, even after crashing.", g2);
        }
    }
}
