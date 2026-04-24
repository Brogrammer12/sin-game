package Entities;

import Main.gamepanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Player {
    public String direction="up";
    public int worldX;
    public int worldY;
    public int speed=4;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public int spriteCounter=0;
    public int screenX, screenY;
    boolean position_instantiated=false;
    public int spriteNum=1;
    gamepanel gp;
    public Player(gamepanel gp) {
        this.gp=gp;
        worldX=gp.worldWidth/2;
        screenX=gamepanel.GAME_WIDTH/2;
        screenY=gamepanel.GAME_HEIGHT/2;
        worldY=gp.worldHeight/2;
        loadImages();
    }
    public void loadImages() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerUp1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerUp2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerLeft2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerDown1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerDown2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/resources/Player/PlayerRight2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void update() {
        if (position_instantiated==false) {
            worldX=gp.worldWidth/2;
        worldY=gp.worldHeight/2;
        position_instantiated=true;
        }
        if (gp.listener.keyCodes[KeyEvent.VK_W]==true) {
            direction="up";
            worldY-=speed;
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_A]==true) {
            direction="left";
            worldX-=speed;
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_S]==true) {
            direction="down";
            worldY+=speed;
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_D]==true) {
            direction="right";
            worldX+=speed;
        }
        if (gp.listener.keyCodes[KeyEvent.VK_W]==true || gp.listener.keyCodes[KeyEvent.VK_A]==true || gp.listener.keyCodes[KeyEvent.VK_S]==true || gp.listener.keyCodes[KeyEvent.VK_D]==true) {
            spriteCounter++;
        if (spriteCounter>=15) {
            if (spriteNum==1) {
                spriteNum=2;
            }
            else if(spriteNum==2) {
                spriteNum=1;
            }
            spriteCounter=0;
        }
        }
    }

    public void load_Player_UI(Graphics2D g2) {
        //
    }
    public void draw(Graphics2D g2) {
        BufferedImage image=null;
        switch (direction) {
            case "up":
                if (spriteNum==1) {
                    image=up1;
                }
                else {
                    image=up2;
                }
                break;
            case "left":
                if (spriteNum==1) {
                    image=left1;
                }
                else {
                    image=left2;
                }
                break;
                case "down":
                if (spriteNum==1) {
                    image=down1;
                }
                else {
                    image=down2;
                }
                break;
                case "right":
                if (spriteNum==1) {
                    image=right1;
                }
                else {
                    image=right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamepanel.resTileSize, gamepanel.resTileSize, null);
    }
}
