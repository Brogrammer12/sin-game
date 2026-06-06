package tileLoader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.gamepanel;
import netscape.javascript.JSException;

public class tileManager {
    gamepanel gp;
    public tile[] tile;
    public props[] prop;
    public tileSuperclass mapTileNum[][];
    public int cameraX=20;
    public int cameraY=20;
    public boolean shouldCamMove=true;
    public String realFile="C:\\Users\\NewAdmin\\Documents\\sin_game\\src\\resources\\tileMaps\\start.tmj";
    public String currentTileset="";
    public BufferedImage[] propImages;
    public boolean[] collisions;
    public int mapWidth, mapHeight;
    public tileManager(gamepanel gp) {
        this.gp=gp;
        tile=new tile[100];
        prop=new props[100];
        propImages=new BufferedImage[100];
        collisions=new boolean[100];
        WorldData data=gp.mRegister.newMap(realFile);
        loadWorld(data);
    }
    public void loadWorld(WorldData data) {
        this.mapTileNum=data.mapTileNum;
        //this.tile=data.tiles;
        //this.prop=data.props;
        this.mapHeight=data.mapHeight;
        this.mapWidth=data.mapWidth;
        System.out.println("go fuck yourself");
    //this.tile=new tile[20];
        //this.prop=new props[20];
       // this.mapTileNum=Arrays.copyOf(data.mapTileNum, data.mapTileNum.length);
        this.tile=Arrays.copyOf(data.tiles, data.tiles.length);
        this.prop=Arrays.copyOf(data.props, data.props.length);
        this.shouldCamMove=data.shouldCamMove;
    }
    public void drawObjectLayer(Graphics2D g2) {
        for (int i=0; i<prop.length; i++) {
            if (prop[i]!=null) {
                int screenX;
                int screenY; 
                if (shouldCamMove==true) {
                    screenX = prop[i].x*3 - gp.p1.worldX + gamepanel.GAME_WIDTH / 2;
                screenY = (prop[i].y-prop[i].height)*3 - gp.p1.worldY + gamepanel.GAME_HEIGHT / 2; 
                }
                else {
                    screenX = prop[i].x*3+cameraX;
                screenY = (prop[i].y-prop[i].height)*3+cameraY; 
                }
                prop[i].draw(g2, screenX, screenY, prop[i].width*3, prop[i].height*3);
                //g2.drawImage(prop[i].image, screenX, screenY, prop[i].width*3, prop[i].height*3, null);
            }
        }
        
    }
    public void draw(Graphics2D g2) {
        int col=0;
        int row=0;
        while (col<mapWidth/gamepanel.resTileSize && row<mapHeight/gamepanel.resTileSize) {
            if (mapTileNum[col] [row]==null) {
                col++;
            if (col==mapWidth/gamepanel.resTileSize) {
                col=0;
                row++;
            }
            continue;
            }
                int tileNum=mapTileNum[col] [row].tileNum;
            int worldX=col*gamepanel.resTileSize;
            int worldY=row*gamepanel.resTileSize;
            int screenX;
            int screenY;
            if (shouldCamMove==true) {
                screenX=(int) (worldX-gp.p1.worldX+gamepanel.GAME_WIDTH/2);
            screenY=(int) (worldY-gp.p1.worldY+gamepanel.GAME_HEIGHT/2);
            }
            else {
                screenX=(int) (worldX+cameraX);
            screenY=(int) (worldY+cameraY);
            }
            if (tile[tileNum]!=null) {
                if (screenX>-gamepanel.resTileSize && screenX<=gamepanel.GAME_WIDTH && screenY>-gamepanel.resTileSize && screenY<=gamepanel.GAME_HEIGHT) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gamepanel.resTileSize, gamepanel.resTileSize, null);
                }
            }
            col++;
            if (col==mapWidth/gamepanel.resTileSize) {
                col=0;
                row++;
            }
            
        }
        drawObjectLayer(g2);
    }
}
