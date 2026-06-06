package tileLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Main.gamepanel;

public class mapRegister {
    gamepanel gp;
    public tile[] tile;
    public props[] prop;
    public props[] mapProps;
    public int worldWidth, worldHeight, worldHoriz, worldVert;
    public tileSuperclass mapTileNum[][];
    public boolean shouldCamMove;
    public mapRegister(gamepanel gp) {
        this.gp=gp;
        tile=new tile[100];
        prop=new props[100];
        mapProps=new props[100];
    }
    public void tileLoader(JsonNode ts, int firstgid, JsonNode mapRoot) {
        JsonNode tiles=ts.get("tiles");
        JsonNode property=ts.get("properties");
        boolean object=false;
        for (JsonNode wut:property) {
            if (wut.get("value").asBoolean()==true && wut.get("name").asText().equals("object")) {
                object=true;
                break;
            }
        }
        for (JsonNode boi:tiles) {
            String imageName="/resources"+boi.get("image").asText();
        int id=boi.get("id").asInt();
        try {
            BufferedImage tilesetImage=ImageIO.read(getClass().getResourceAsStream(imageName));
            if (object==false) {
                tile[id]=new tile();
            tile[id].image=tilesetImage;
            JsonNode tileproperty=boi.get("properties");
            for (JsonNode sixseven:tileproperty) {
                if (sixseven.get("name").asText().equals("collision") && sixseven.get("value").asBoolean()==true) {
                    tile[id].collision=true;
                }
                else {
                    System.out.println("no collision here");
                    tile[id].collision=false;
                }
            }
            }
            else {
                System.out.println("wut");
                prop[firstgid+id]=new props();
                prop[firstgid+id].image=tilesetImage;
                JsonNode propProperty=boi.get("properties");
                for (JsonNode sixseven:propProperty) {
                    if (sixseven.get("name").asText().equals("collision") && sixseven.get("value").asBoolean()==true) {
                        prop[firstgid+id].collision=true;
                    }
                    else {
                        prop[firstgid+id].collision=false;
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }
    public WorldData newMap(String fileName) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            JsonNode root=mapper.readTree(new File(fileName));
            JsonNode r=root.get("layers");
            JsonNode dataNode=null, widthNode=null, heightNode=null;
            for (JsonNode wutr:r) {
                if (wutr.get("name").asText().equals("Tile Layer 1")) {
                    dataNode=wutr.get("data");
                    heightNode=wutr.get("height");
                    widthNode=wutr.get("width");
                }
            }
            JsonNode wut=root.get("properties");
            for (JsonNode custom:wut) {
                if (custom.get("name").asText().equals("camShouldMove")) {
                    shouldCamMove=custom.get("value").asBoolean();
                }
            }
            JsonNode tilesets=root.get("tilesets");
            for (JsonNode t:tilesets) {
                String tilePath="C:/Users/NewAdmin/Documents/sin_game/src/resources"+t.get("source").asText();
                int gid=t.get("firstgid").asInt();
                JsonNode root3=mapper.readTree(new File(tilePath));
                tileLoader(root3, gid, root);
            }
        
            int[] data=mapper.readValue(dataNode.toString(), int[].class);
            int height=Integer.parseInt(heightNode.toString());
            int width=Integer.parseInt(widthNode.toString());
            mapTileNum=new tileSuperclass[width][height];
            worldHoriz=width;
            worldVert=height;
            worldWidth=worldHoriz*gamepanel.resTileSize;
            worldHeight=worldVert*gamepanel.resTileSize;
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
             root2 = mapper2.readTree(new File(fileName));
             JsonNode E=root2.get("layers");
            JsonNode thingie=null;
            for (JsonNode sun:E) {
                if (sun.get("name").asText().equals("Object Layer 1")) {
                    thingie=sun.get("objects");
                }
            }
        int index=0;
        mapProps=new props[100];
            for (JsonNode e:thingie) {
            String type=e.get("type").asText();
            String name=e.get("name").asText();
            String functionField=null;
            try {
                functionField=e.get("properties").get(0).get("value").asText();
            }
            catch (Exception en) {
                en.printStackTrace();
            }
            
            int gid=e.get("gid").asInt();
            mapProps[index]=objectFactory.create(type, gp, functionField, name);
           mapProps[index].x=e.get("x").asInt();
           mapProps[index].y=e.get("y").asInt();
           mapProps[index].width=e.get("width").asInt();
           mapProps[index].height=e.get("height").asInt();
           mapProps[index].image=prop[gid].image;
           mapProps[index].collision=prop[gid].collision;
           mapProps[index].applySavedState();
            index++;
        }
            //System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WorldData data=new WorldData(worldWidth, worldHeight, tile, mapProps, mapTileNum, shouldCamMove);
        return data;
    }
}
