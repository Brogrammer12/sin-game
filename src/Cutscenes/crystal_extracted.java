package Cutscenes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gamepanel;
import Menus.fadeBlack;
import Menus.timer;
public class crystal_extracted extends cutsceneSuperclass{
    gamepanel gp;
    fadeBlack fBlack;
    timer timer1;
    timer timer2;
    int fCode;
    public crystal_extracted(gamepanel gp) {
        this.gp=gp;
        fBlack=new fadeBlack(gp);
        timer1=new timer();
        timer2=new timer();
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/crystal_in_tube.png"));
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
        g2.drawImage(cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
        boolean done=timer1.wait(3, gp.delta);
        gp.wordBoi.drawAll(10, height, "Researchers extracted the mineral, and after some experiments they found out it had the power of MAGIC.", g2);
        if (done==true) {
            if (fCode>=1) {
                gp.cManager.early_cutscene("evilBoi", g2, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT);
            }
            if (fCode==3) {
                gp.currentCutscene="evilBoi";
            }
            int fadeCode=fBlack.fadeToBlack(0.5, g2);
        fCode=fadeCode;
        }
    }
}
