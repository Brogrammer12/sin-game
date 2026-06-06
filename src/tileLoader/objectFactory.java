package tileLoader;

import Main.gamepanel;

public class objectFactory {
    public static props create(String type, gamepanel gp, String data, String name) {
        switch (type) {
                case "Chest":
                    return new Chest(gp, data, name);
                    case "Exit":
                        return new Exit(gp, data, name);
                        case "Table":
                            return new tableWithBook(gp, data, name);
                default:
                    return new props();
            }
    }
}
