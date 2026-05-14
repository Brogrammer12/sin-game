package tileLoader;

import Main.gamepanel;

public class Exit extends props implements Interactable{
String targetMapInfo;
gamepanel gp;
    public Exit(gamepanel gp, String targetMapInfo) {
        this.gp=gp;
        this.targetMapInfo=targetMapInfo;
    }
    @Override
    public void onInteract(gamepanel gp) {
        String[] targetInfo=targetMapInfo.split(" ");
        gp.tManager.newMap(targetInfo[0]);
        gp.p1.worldX=Integer.parseInt(targetInfo[1]);
        gp.p1.worldX=Integer.parseInt(targetInfo[2]);
        gp.p1.screenX=gamepanel.GAME_WIDTH/2;
        gp.p1.screenY=gamepanel.GAME_HEIGHT/2;
    }

}
