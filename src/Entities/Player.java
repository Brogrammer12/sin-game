package Entities;

import Main.gamepanel;
import tileLoader.Interactable;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Player extends entitySuperclass{
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public int spriteCounter=0;
    public int screenX, screenY;
    public double SCALEX, SCALEY;
    boolean position_instantiated=false;
    public boolean movementDisabled=false;
    public int spriteNum=1;
    gamepanel gp;
    public Player(gamepanel gp) {
        this.gp=gp;
        direction="up";
        speed=4;
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
        handleInteractions();
        instantiatePosition();
        handleMovementComplete();
    }

    public void load_Player_UI(Graphics2D g2) {
        //
    }
    public void disableMovement() {
        movementDisabled=true;
    }
    public void enableMovement() {
        movementDisabled=false;
    }
    public void handleMovementComplete() {
        if (gp.listener.keyCodes[KeyEvent.VK_W]==true) {
            direction="up";
            tryMove(0, -speed);
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_A]==true) {
            direction="left";
            tryMove(-speed, 0);
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_S]==true) {
            direction="down";
            tryMove(0, speed);
        }
        else if (gp.listener.keyCodes[KeyEvent.VK_D]==true) {
            direction="right";
            tryMove(speed, 0);
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
    public void instantiatePosition() {
        if (position_instantiated==false) {
            worldX=gp.worldWidth/2;
        worldY=gp.worldHeight/2;
        position_instantiated=true;
        }
        if (gp.tManager.shouldCamMove==false) {
            screenX=worldX+gp.tManager.cameraX;
            screenY=worldY+gp.tManager.cameraY;
        }
        else {
            screenX=gamepanel.GAME_WIDTH/2;
        screenY=gamepanel.GAME_HEIGHT/2;
        }
    }
public void handleInteractions() {
    playerHitbox=new Rectangle(8, 16, 32, 32);
        for (int i = 0; i < gp.tManager.prop.length; i++) {
    if (gp.tManager.prop[i]!=null) {
        Rectangle propRect=new Rectangle((gp.tManager.prop[i].x*3)-10, ((gp.tManager.prop[i].y-gp.tManager.prop[i].height)*3)-10, gp.tManager.prop[i].width*3+10, gp.tManager.prop[i].height*3+20);
        Rectangle playerCopy=new Rectangle(playerHitbox.x+worldX, playerHitbox.y+worldY, playerHitbox.width, playerHitbox.height);
         if (playerCopy.intersects(propRect)) {
        if (gp.tManager.prop[i] instanceof Interactable interactable) {
                    interactable.onInteract(gp);
                }
    }
    }
    }
}
    public void tryMove(int dx, int dy) {
        if (movementDisabled==false) {
            boolean isColliding=false;
        boolean propsExist=false;
            for (int i=0; i<gp.tManager.prop.length; i++) {
                if (gp.tManager.prop[i]!=null) {
                    propsExist=true;
                    boolean propcollision=gp.tManager.prop[i].collision;
                    Rectangle propRect=new Rectangle((gp.tManager.prop[i].x*3), ((gp.tManager.prop[i].y-gp.tManager.prop[i].height)*3),gp.tManager.prop[i].width*3,gp.tManager.prop[i].height*3);
                if (propcollision==true) {
                        isColliding=gp.cChecker.checkPropCollision(this, propRect, direction) || gp.cChecker.checkTileCollision(this, gp);
                    }
                    else {
                        isColliding=gp.cChecker.checkTileCollision(this, gp);
                    }
                if (isColliding==true) {
                    break;
                }
                }
            }
            if (propsExist==false) {
                isColliding=gp.cChecker.checkTileCollision(this, gp);
            }
            if (isColliding==false) {
                worldX+=dx;
                worldY+=dy;
            
                }
        }
    }
    public void draw(Graphics2D g2, double scaleX, double scaleY) {
        BufferedImage image=null;
        SCALEX=scaleX;
        SCALEY=scaleY;
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
