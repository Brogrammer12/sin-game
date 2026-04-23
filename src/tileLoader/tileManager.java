package tileLoader;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Graphics2D;
import Main.gamepanel;

public class tileManager {
    gamepanel gp;
    public tile[] tile;
    public tileSuperclass mapTileNum[][];
    public String realFile="C:\\Users\\NewAdmin\\Documents\\sin game\\src\\resources\\tileMaps\\start_room.tmj";
    public tileManager(gamepanel gp) {
        this.gp=gp;
        newMap(realFile);
        tile=new tile[5];
        tileLoader();
    }
    public void tileLoader() {
        try {
            tile[4]=new tile();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wooden_Floor.png"));
            tile[3]=new tile();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallBunkerUp.png"));
            tile[3].collision=true;
            tile[1]=new tile();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallBunkerLeft.png"));
            tile[1].collision=true;
            tile[0]=new tile();
            tile[0].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallBunkerDown.png"));
            tile[0].collision=true;
            tile[2]=new tile();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallBunkerRight.png"));
            tile[2].collision=true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void newMap(String fileName) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            JsonNode root=mapper.readTree(new File(fileName));
            JsonNode dataNode=root.get("layers").get(0).get("data");
            JsonNode heightNode=root.get("layers").get(0).get("height");
            JsonNode widthNode=root.get("layers").get(0).get("width");
            int[] data=mapper.readValue(dataNode.toString(), int[].class);
            int height=Integer.parseInt(heightNode.toString());
            int width=Integer.parseInt(widthNode.toString());
            mapTileNum=new tileSuperclass[width][height];
            gp.maxWorldHoriz=width;
            gp.maxWorldVert=height;
            gp.worldWidth=gp.maxWorldHoriz*gamepanel.resTileSize;
            gp.worldHeight=gp.maxWorldVert*gamepanel.resTileSize;
            for (int row=0; row<height; row++) {
                for (int col=0; col<width; col++) {
                    mapTileNum[col] [row]=new tileSuperclass();
                    mapTileNum[col] [row].tileNum=data[row*width+col]-1;
                }
            }
            //System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int col=0;
        int row=0;
        while (col<gp.maxWorldHoriz && row<gp.maxWorldVert) {
            int tileNum=mapTileNum[col] [row].tileNum;
            int worldX=col*gamepanel.resTileSize;
            int worldY=row*gamepanel.resTileSize;
            int screenX=(int) (worldX-gp.p1.worldX+gp.p1.screenX);
            int screenY=(int) (worldY-gp.p1.worldY+gp.p1.screenY);
            g2.drawImage(tile[tileNum].image, screenX, screenY, gamepanel.resTileSize, gamepanel.resTileSize, null);
            col++;
            if (col==gp.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
    }
}
