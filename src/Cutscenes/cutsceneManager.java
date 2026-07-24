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
        cutscenes.put("crystal_extracted", new crystal_extracted(gp));
        cutscenes.put("evilBoi", new evilBoi(gp));
        cutscenes.put("Village", new village(gp));
        cutscenes.put("final_message", new final_message(gp));
        cutscenes.put("villageElder", new villageElder(gp));
    }
    public void startCutscene(String cutsceneId, Graphics2D g2) {
        cutsceneSuperclass boi=cutscenes.get(cutsceneId);
        boi.runCutscene(g2);
    }
    public void early_cutscene(String cutsceneId, Graphics2D g2, int WIDTH, int HEIGHT) {
        cutsceneSuperclass boi=cutscenes.get(cutsceneId);
        g2.drawImage(boi.cutsceneImage, 0, 0, WIDTH, HEIGHT, null);
    }
}
