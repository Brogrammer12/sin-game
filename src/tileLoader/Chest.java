package tileLoader;

import Main.gamepanel;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Chest extends props implements Interactable{
    gamepanel gp;
    String loot;
    String name;
    boolean isOpened=false;
    public Chest(gamepanel gp, String loot, String name) {
        this.gp=gp;
        this.loot=loot;
        this.name=name;
    }
    @Override
    public void onInteract(gamepanel gp) {
        if (gp.listener.keyCodes[KeyEvent.VK_E]==true && isOpened==false) {
            try {
                open();
                image=ImageIO.read(getClass().getResourceAsStream("/resources/props/chestOpen.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isOpened=true;
        }
    }
    public void open() {
    state.put("opened", true);
    gp.wState.propStates.put(name, state);
}
@Override
public void applySavedState() {
    if (gp.wState.propStates.containsKey(name)) {
    state = gp.wState.propStates.get(name);
}
    if (state.containsKey("opened")) {
    isOpened = (boolean) state.get("opened");
    if (isOpened) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/props/chestOpen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}

}
