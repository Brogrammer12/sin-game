package tileLoader;

import Main.gamepanel;

public class Chest extends props implements Interactable{
    gamepanel gp;
    String loot;
    public Chest(gamepanel gp, String loot) {
        this.gp=gp;
        this.loot=loot;
    }
    @Override
    public void onInteract(gamepanel gp) {
        System.out.println("chest interaction system working");
    }
}
