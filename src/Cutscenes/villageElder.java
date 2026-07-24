package Cutscenes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gamepanel;
import Menus.timer;
public class villageElder extends cutsceneSuperclass{
    gamepanel gp;
    BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public int elderX, elderY;
    public boolean moving=false;
    public String direction="down";
    int spriteCounter=0;
    int spriteNum=0;
    timer t1;
    public villageElder(gamepanel gp) {
        this.gp=gp;
        elderX=500;
        elderY=100;
        t1=new timer();
        try {
            cutsceneImage=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/village_Elder.png"));
            up1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_up2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_left2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_down2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/cutscenes/elder_right2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateAnims() {
            spriteCounter++;
            if (spriteCounter>=15) {
                if (spriteNum==0) {
                    spriteNum=1;
                }
                else {
                    spriteNum=0;
                }
                spriteCounter=0;
            }
        
    }
    
    public void drawElder(Graphics2D g2) {
        BufferedImage image=null;
        switch (direction) {
            case "up":
                if (spriteNum==0) {
                    image=up1;
                }
                else if(spriteNum==1) {
                    image=up2;
                }
                break;
        
            case "left":
                if (spriteNum==0) {
                    image=left1;
                }
                else if(spriteNum==1) {
                    image=left2;
                }
                break;
                case "down":
                if (spriteNum==0) {
                    image=down1;
                }
                else if(spriteNum==1) {
                    image=down2;
                }
                break;
                case "right":
                if (spriteNum==0) {
                    image=right1;
                }
                else if(spriteNum==1) {
                    image=right2;
                }
                break;
        }
        g2.drawImage(image, elderX, elderY, gamepanel.resTileSize, gamepanel.resTileSize, null);
    }
    public void moveElder(String dir) {
        direction=dir;
        switch (dir) {
            case "up":
                elderY-=4;
                break;
        
            case "left":
            elderX-=4;
                break;
            case "down":
            elderY+=4;
            break;
            case "right":
            elderX+=4;
            break;
        }
        updateAnims();
    }
    @Override
    public void runCutscene(Graphics2D g2) {
        g2.drawImage(cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
        drawElder(g2);
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24f, g2);
        Font font=g2.getFont();
            int height=gp.tDrawer.getTextHeight(font, g2);
        g2.setColor(Color.WHITE);
        boolean time1=t1.wait(3, gp.delta);
        if (time1==true) {
            if (elderY>=500) {
                gp.wordBoi.drawAll(10, height, "Meet me outside, "+gp.p1.Name+".We have a lot to talk about.", g2);
            }
            else {
                moveElder("down");
            }
        }
        else {
            gp.wordBoi.drawAll(10, height, "Oh, there you are! You've been asleep for a while now.", g2);
        }
    }
}
