package Cutscenes;

import java.awt.Graphics2D;
import java.util.HashMap;

import Cutscenes.cutsceneSuperclass;
import Main.gamepanel;

public class cutsceneManager {
    gamepanel gp;
    public HashMap<String, cutsceneSuperclass> cutscenes=new HashMap<>();
    public cutsceneManager(gamepanel gp) {
        this.gp=gp;
        cutscenes.put("meteorScene", new meteorScene(gp));
        cutscenes.put("meteorFell", new meteorFell(gp));
    }
    public void startCutscene(String cutsceneId, Graphics2D g2) {
        cutsceneSuperclass boi=cutscenes.get(cutsceneId);
        boi.runCutscene(g2);
    }
    public void early_cutscene(String cutsceneId, Graphics2D g2) {
        cutsceneSuperclass boi=cutscenes.get(cutsceneId);
        g2.drawImage(boi.cutsceneImage, 0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT, null);
    }
}
