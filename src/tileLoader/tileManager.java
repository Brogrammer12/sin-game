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
    public tileSuperclass mapTileNum[][];
    public boolean shouldCamMove=true;
    public String realFile="C:\\Users\\NewAdmin\\Documents\\sin game\\src\\resources\\tileMaps\\start.tmj";
    public String currentTileset="";
    public tileManager(gamepanel gp) {
        this.gp=gp;
        tile=new tile[11];
        newMap(realFile);
    }
    public void tileLoader(JsonNode ts, int firstgid) {
        JsonNode tiles=ts.get("tiles");
        for (JsonNode boi:tiles) {
            String imageName="/resources"+boi.get("image").asText();
        int id=boi.get("id").asInt();
        try {
            BufferedImage tilesetImage=ImageIO.read(getClass().getResourceAsStream(imageName));
            tile[id-1+firstgid]=new tile();
            tile[id-1+firstgid].image=tilesetImage;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }
    public void newMap(String fileName) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            JsonNode root=mapper.readTree(new File(fileName));
            JsonNode dataNode=root.get("layers").get(0).get("data");
            JsonNode heightNode=root.get("layers").get(0).get("height");
            JsonNode widthNode=root.get("layers").get(0).get("width");
            JsonNode wut=root.get("properties");
            for (JsonNode custom:wut) {
                if (custom.get("name").asText().equals("camShouldMove")) {
                    shouldCamMove=custom.get("value").asBoolean();
                }
            }
            JsonNode tilesets=root.get("tilesets");
            for (JsonNode t:tilesets) {
                String tilePath="C:/Users/NewAdmin/Documents/sin game/src/resources"+t.get("source").asText();
                int gid=t.get("firstgid").asInt();
                JsonNode root3=mapper.readTree(new File(tilePath));
                tileLoader(root3, gid);
            }
        
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
            int screenX=(int) (worldX-gp.p1.worldX+gamepanel.GAME_WIDTH/2);
            int screenY=(int) (worldY-gp.p1.worldY+gamepanel.GAME_HEIGHT/2);
            g2.drawImage(tile[tileNum].image, screenX, screenY, gamepanel.resTileSize, gamepanel.resTileSize, null);
            col++;
            if (col==gp.maxWorldHoriz) {
                col=0;
                row++;
            }
        }
    }
}
