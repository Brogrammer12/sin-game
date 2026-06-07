package Main;

import java.awt.Rectangle;

import Entities.entitySuperclass;

public class collisionChecker {
    public boolean checkPropCollision(entitySuperclass player, Rectangle prop, String direction) {
        Rectangle playerCopy=new Rectangle(player.playerHitbox.x+player.worldX, player.playerHitbox.y+player.worldY, player.playerHitbox.width, player.playerHitbox.height);
        switch (direction) {
            case "up":
                playerCopy.y-=4;
                if (playerCopy.intersects(prop)) {
            return true;
        }
                break;
                case "left":
                    playerCopy.x-=4;
                    if (playerCopy.intersects(prop)) {
            return true;
        }
                    break;
                    case "right":
                        playerCopy.x+=4;
                        if (playerCopy.intersects(prop)) {
            return true;
        }
                        break;
                        case "down":
                            playerCopy.y+=4;
                            if (playerCopy.intersects(prop)) {
            return true;
        }
                            break;
        }
        return false;
    }
    public boolean checkTileCollision(entitySuperclass entity, gamepanel gp) {
        int entityLeftWorldX=entity.worldX+entity.playerHitbox.x;
            int entityRightWorldX=entity.worldX+entity.playerHitbox.x+entity.playerHitbox.width;
            int entityTopWorldY=entity.worldY+entity.playerHitbox.y;
            int entityBottomWorldY=entity.worldY+entity.playerHitbox.y+entity.playerHitbox.height;
            int entityLeftCol=entityLeftWorldX/gamepanel.resTileSize;
            int entityRightCol=entityRightWorldX/gamepanel.resTileSize;
            int entityTopRow=entityTopWorldY/gamepanel.resTileSize;
            int entityBottomRow=entityBottomWorldY/gamepanel.resTileSize;
            int tileNum1,tileNum2;
            switch (entity.direction) {
                case "up":
                    try {
                        entityTopRow=(entityTopWorldY-entity.speed)/gamepanel.resTileSize;
                if (gp.tManager.mapTileNum[entityLeftCol] [entityTopRow]!=null && gp.tManager.mapTileNum[entityRightCol] [entityTopRow]!=null) {
                    tileNum1=gp.tManager.mapTileNum[entityLeftCol] [entityTopRow].tileNum;
                tileNum2=gp.tManager.mapTileNum[entityRightCol] [entityTopRow].tileNum;
                if (gp.tManager.tile[tileNum1].collision==true || gp.tManager.tile[tileNum2].collision==true) {
                    return true;
                }
                }
                else {
                    return false;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
                break;
                case "left":
                    try {
                        entityLeftCol=(entityLeftWorldX-entity.speed)/gamepanel.resTileSize;
                if (gp.tManager.mapTileNum[entityLeftCol] [entityTopRow]!=null && gp.tManager.mapTileNum[entityLeftCol] [entityBottomRow]!=null) {
                    tileNum1=gp.tManager.mapTileNum[entityLeftCol] [entityTopRow].tileNum;
                tileNum2=gp.tManager.mapTileNum[entityLeftCol] [entityBottomRow].tileNum;
                if (gp.tManager.tile[tileNum1].collision==true || gp.tManager.tile[tileNum2].collision==true) {
                    return true;
                }
                }
                else {
                    return false;
                }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        return true;
                    }
                break;
                case "down":
                    try {
                        entityBottomRow=(entityBottomWorldY+entity.speed)/gamepanel.resTileSize;
                if (gp.tManager.mapTileNum[entityLeftCol] [entityBottomRow]!=null && gp.tManager.mapTileNum[entityRightCol] [entityBottomRow]!=null) {
                    tileNum1=gp.tManager.mapTileNum[entityLeftCol] [entityBottomRow].tileNum;
                tileNum2=gp.tManager.mapTileNum[entityRightCol] [entityBottomRow].tileNum;
                if (gp.tManager.tile[tileNum1].collision==true || gp.tManager.tile[tileNum2].collision==true) {
                    return true;
                }
                }
                else {
                    return false;
                }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        return true;
                    }
                break;
                case "right":
                    try {
                        entityRightCol=(entityRightWorldX+entity.speed)/gamepanel.resTileSize;
                if (gp.tManager.mapTileNum[entityRightCol] [entityTopRow]!=null && gp.tManager.mapTileNum[entityRightCol] [entityBottomRow]!=null) {
                    tileNum1=gp.tManager.mapTileNum[entityRightCol] [entityTopRow].tileNum;
                tileNum2=gp.tManager.mapTileNum[entityRightCol] [entityBottomRow].tileNum;
                if (gp.tManager.tile[tileNum1].collision==true || gp.tManager.tile[tileNum2].collision==true) {
                    return true;
                }
                }
                else {
                    return false;
                }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        return true;
                    }
                break;
            }
        return false;
    }
}
