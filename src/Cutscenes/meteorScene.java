package Cutscenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gameProcessor;
import Main.gamepanel;
import Menus.fadeBlack;
import Menus.timer;

public class meteorScene extends cutsceneSuperclass{
    gamepanel gp;
    timer timer1, timer2;
    fadeBlack fBlack;
    public int meteorX, meteorY;
    public BufferedImage p1Right1, p1Right2, p1Up, p2Right1, p2Right2, p2Up;
    public BufferedImage meteor;
    public String direction="right";
    public double spriteCounter=0;
    public int spriteNum=0;
    public int fCode=0;
    public int p1x, p1y, p2x, p2y;
    public meteorScene(gamepanel gp) {
        this.gp=gp;
        p1x=0;
        p1y=gamepanel.GAME_HEIGHT-gamepanel.resTileSize-10;
        p2x=100;
        p2y=gamepanel.GAME_HEIGHT-gamepanel.resTileSize-10;
        meteorX=gamepanel.GAME_WIDTH-50;
        meteorY=10;
        loadImages();
        timer1=new timer();
        timer2=new timer();
        fBlack=new fadeBlack(gp);
    }
    public void loadImages() {
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/meteorScene.png"));
            p1Right1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/wut1.png"));
            p1Right2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/wut2.png"));
            p1Up=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/PlayerUp1.png"));
            p2Right1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/Player2Right1.png"));
            p2Right2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/Player2Right2.png"));
            p2Up=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/Player2Up1.png"));
            meteor=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/meteorFalling.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void runCutscene(Graphics2D g2) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        g2.drawImage(cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
        if (p2x<gamepanel.GAME_WIDTH/2) {
            spriteCounter+=gp.delta;
        if (spriteCounter>=0.25) {
            if (spriteNum==0) {
                spriteNum=1;
            }
            else {
                spriteNum=0;
            }
            spriteCounter=0;
        }
        }
        else {
            direction="up";
        }
        boolean done=timer1.wait(3, gp.delta);
        if (done==true) {
            BufferedImage p1Image=null;
            BufferedImage p2Image=null;
            switch (direction) {
                case "right":
                    if (spriteNum==1) {
                        p1Image=p1Right2;
                        p2Image=p2Right2;
                    }
                    else {
                        p1Image=p1Right1;
                        p2Image=p2Right1;
                    }
                    break;
            
                case "up":
                    if (spriteNum==1) {
                        p1Image=p1Up;
                        p2Image=p2Up;
                    }
                    else {
                        p1Image=p1Up;
                        p2Image=p2Up;
                    }
                    break;
            }
            if (p2x<gamepanel.GAME_WIDTH/2) {
                p1x+=2;
                p2x+=2;
            }
            g2.drawImage(p1Image, p1x, p1y, gamepanel.resTileSize, gamepanel.resTileSize, null);
            g2.drawImage(p2Image, p2x, p2y, gamepanel.resTileSize, gamepanel.resTileSize, null);
            Font font=g2.getFont();
            int height=gp.tDrawer.getTextHeight(font, g2);
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll(10, height, "On one fateful day, a meteor fell to the earth from outer space.", g2);
            if (p2x>=gamepanel.GAME_WIDTH/2) {
                meteorBoi(g2);
            }
        }
    }
    public void meteorBoi(Graphics2D g2) {
        g2.drawImage(meteor, meteorX, meteorY, gamepanel.resTileSize, gamepanel.resTileSize, null);
        meteorX--;
        meteorY++;
        boolean done=timer2.wait(0.75, gp.delta);
        if (done==true) {
            if (fCode>=1) {
                gp.cManager.early_cutscene("meteorFell", g2);
            }
            if (fCode==3) {
                gp.currentCutscene="meteorFell";
            }
            int fadeCode=fBlack.fadeToBlack(0.5, g2);
            fCode=fadeCode;
        }
    }
}
