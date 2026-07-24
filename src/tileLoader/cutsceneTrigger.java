package tileLoader;

import Main.gameState;
import Main.gamepanel;

public class cutsceneTrigger extends props implements Interactable{
    gamepanel gp;
    String data;
    String name;
    public cutsceneTrigger(gamepanel gp, String data, String name) {
        this.gp=gp;
        this.data=data;
        this.name=name;
    }

    @Override
    public void onInteract(gamepanel gp) {
        gp.currentCutscene=data;
        gp.state=gameState.CUTSCENE;
    }
}
