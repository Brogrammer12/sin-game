package tileLoader;

import java.awt.Graphics2D;

import Main.gamepanel;

public class Exit extends props implements Interactable{
String targetMapInfo;
gamepanel gp;
String name;
    public Exit(gamepanel gp, String targetMapInfo, String name) {
        this.gp=gp;
        this.targetMapInfo=targetMapInfo;
        this.name=name;
    }
    @Override
    public void onInteract(gamepanel gp) {
        String[] targetInfo=targetMapInfo.split(" ");
        WorldData data=gp.mRegister.newMap(targetInfo[0]);
        gp.tManager.loadWorld(data);
        gp.p1.worldX=Integer.parseInt(targetInfo[1]);
        gp.p1.worldY=Integer.parseInt(targetInfo[2]);
        gp.p1.screenX=gamepanel.GAME_WIDTH/2;
        gp.p1.screenY=gamepanel.GAME_HEIGHT/2;
    }

}
