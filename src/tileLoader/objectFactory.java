package tileLoader;

import Main.gamepanel;

public class objectFactory {
    public static props create(String type, gamepanel gp, String data) {
        switch (type) {
                case "Chest":
                    return new Chest(gp, data);
                    case "Exit":
                        return new Exit(gp, data);
                default:
                    return new props();
            }
    }
}
