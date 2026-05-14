package tileLoader;

import Main.gamepanel;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Chest extends props implements Interactable{
    gamepanel gp;
    String loot;
    boolean isOpened=false;
    public Chest(gamepanel gp, String loot) {
        this.gp=gp;
        this.loot=loot;
    }
    @Override
    public void onInteract(gamepanel gp) {
        if (gp.listener.keyCodes[KeyEvent.VK_E]==true && isOpened==false) {
            try {
                image=ImageIO.read(getClass().getResourceAsStream("/resources/props/chestOpen.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isOpened=true;
        }
    }
}
