package Main;

import java.awt.Graphics2D;
import Cutscenes.cutsceneSuperclass;

public class gameProcessor {
    gamepanel gp;
    public gameProcessor(gamepanel gp) {
        this.gp=gp;
    }
    public void runGame(Graphics2D g2) {
        if (gp.state==gameState.CUTSCENE) {
            gp.cManager.startCutscene(gp.currentCutscene, g2);
        }
    }
}
