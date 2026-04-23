package Cutscenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gamepanel;
import Menus.fadeBlack;
import Menus.timer;

public class evilBoi extends cutsceneSuperclass{
    gamepanel gp;
    timer timer1;
    timer timer2;
    fadeBlack fBlack;
    int fCode;
    public evilBoi(gamepanel gp) {
        this.gp=gp;
        timer1=new timer();
        timer2=new timer();
        fBlack=new fadeBlack(gp);
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/evil_scientist.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void runCutscene(Graphics2D g2) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        Font font=g2.getFont();
            int height=gp.tDrawer.getTextHeight(font, g2);
            g2.setColor(Color.WHITE);
        g2.drawImage(cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
         boolean done=timer1.wait(2.5, gp.delta);
        if (done==true) {
            boolean wut=timer2.wait(2, gp.delta);
            gp.wordBoi.drawAll(10, height, "The magic crystal had the power to corrupt, and with it, he started his conquest to rule the world.", g2);
            if (wut==true) {
                if (fCode>=1) {
                gp.cManager.early_cutscene("Village", g2, gamepanel.GAME_WIDTH*2, gamepanel.GAME_HEIGHT);
            }
            if (fCode==3) {
                gp.currentCutscene="Village";
            }
                int fadeCode=fBlack.fadeToBlack(0.5, g2);
            fCode=fadeCode;
            }
        }
        else if(done==false) {
            gp.wordBoi.drawAll(10, height, "But one researcher wanted to use the power for his own good. He stole the magic crystal.", g2);
        }
    }
}
