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
    public String realFile="C:\\Users\\NewAdmin\\Documents\\sin game\\src\\resources\\tileMaps\\start.tmj";
    public String currentTileset="";
    public BufferedImage[] propImages;
    public boolean[] collisions;
    public tileManager(gamepanel gp) {
        this.gp=gp;
        tile=new tile[11];
        prop=new props[11];
        propImages=new BufferedImage[11];
        collisions=new boolean[11];
        newMap(realFile);
    }
    public void tileLoader(JsonNode ts, int firstgid, JsonNode mapRoot) {
        JsonNode tiles=ts.get("tiles");
        JsonNode property=ts.get("properties");
        boolean object=false;
        for (JsonNode wut:property) {
            if (wut.get("value").asBoolean()==true && wut.get("name").asText().equals("object")) {
                object=true;
            }
            else {
                object=false;
            }
        }
        for (JsonNode boi:tiles) {
            String imageName="/resources"+boi.get("image").asText();
        int id=boi.get("id").asInt();
        try {
            BufferedImage tilesetImage=ImageIO.read(getClass().getResourceAsStream(imageName));
            if (object==false) {
                tile[id-1+firstgid]=new tile();
            tile[id-1+firstgid].image=tilesetImage;
            JsonNode tileproperty=boi.get("properties");
            for (JsonNode sixseven:tileproperty) {
                if (sixseven.get("name").asText().equals("collision") && sixseven.get("value").asBoolean()==true) {
                    tile[id-1+firstgid].collision=true;
                }
                else {
                    tile[id-1+firstgid].collision=false;
                }
            }
            }
            else {
                System.out.println("wut");
                propImages[id]=tilesetImage;
                JsonNode propProperty=boi.get("properties");
                for (JsonNode sixseven:propProperty) {
                    if (sixseven.get("name").asText().equals("collision") && sixseven.get("value").asBoolean()==true) {
                        collisions[id]=true;
                        System.out.println("detected collision boi");
                    }
                    else {
                        collisions[id]=false;
                        System.out.println("didnt detect shet. :(");
                    }
                }
            }
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
                tileLoader(root3, gid, root);
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
                    if (data[row*width+col]==0) {
                        continue;
                    }
                    else {
                         mapTileNum[col] [row]=new tileSuperclass();
                    mapTileNum[col] [row].tileNum=data[row*width+col]-1;
                    }
                }
            }
            ObjectMapper mapper2=new ObjectMapper();
        JsonNode root2;
             root2 = mapper2.readTree(new File(realFile));
            JsonNode thingie=root2.get("layers").get(1).get("objects");
        int index=0;
        for (JsonNode e:thingie) {
            String type=e.get("name").asText();
            String functionField=e.get("properties").get(0).get("value").asText();
            switch (type) {
                case "Chest":
                    prop[index]=new Chest(gp, functionField);
                    break;
            
                default:
                    prop[index]=new props();
                    break;
            }
           prop[index].x=e.get("x").asInt();
           prop[index].y=e.get("y").asInt();
           prop[index].width=e.get("width").asInt();
           prop[index].height=e.get("height").asInt();
           prop[index].image=propImages[index];
           prop[index].collision=collisions[index];
            index++;
        }
            //System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                g2.drawImage(prop[i].image, screenX, screenY, prop[i].width*3, prop[i].height*3, null);
            }
        }
        
    }
    public void draw(Graphics2D g2) {
        int col=0;
        int row=0;
        while (col<gp.maxWorldHoriz && row<gp.maxWorldVert) {
            if (mapTileNum[col] [row]==null) {
                col++;
            if (col==gp.maxWorldHoriz) {
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
                if (screenX>0 && screenX<=gamepanel.GAME_WIDTH && screenY>0 && screenY<=gamepanel.GAME_HEIGHT) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gamepanel.resTileSize, gamepanel.resTileSize, null);
                }
            }
            col++;
            if (col==gp.maxWorldHoriz) {
                col=0;
                row++;
            }
            
        }
        drawObjectLayer(g2);
    }
}
