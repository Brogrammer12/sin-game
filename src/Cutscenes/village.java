package Cutscenes;

import Main.gamepanel;
import Menus.fadeBlack;
import Menus.timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
public class village extends cutsceneSuperclass{
    gamepanel gp;
    timer timer1;
    fadeBlack fBlack;
    int cutsceneX=0;
    int fCode;
    public village(gamepanel gp) {
        this.gp=gp;
        timer1=new timer();
        fBlack=new fadeBlack(gp);
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/village.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void runCutscene(Graphics2D g2) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        Font font=g2.getFont();
            int height=gp.tDrawer.getTextHeight(font, g2);
            g2.setColor(Color.WHITE);
        g2.drawImage(cutsceneImage, cutsceneX, 0, gamepanel.GAME_WIDTH*2, gamepanel.GAME_HEIGHT, null);
        if (cutsceneX>-gamepanel.GAME_WIDTH) {
            cutsceneX-=5;
        }
         boolean done=timer1.wait(4, gp.delta);
        gp.wordBoi.drawAll(10, height, "The corruption decimated the world. MAGIC was too much for the world to handle.", g2);
        if (done==true) {
            if (fCode>=1) {
                gp.cManager.early_cutscene("final_message", g2, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT);
            }
            if (fCode==3) {
                gp.currentCutscene="final_message";
            }
            int fadeCode=fBlack.fadeToBlack(0.5, g2);
        fCode=fadeCode;
        }
    }
}
