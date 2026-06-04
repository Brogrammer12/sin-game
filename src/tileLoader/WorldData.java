package tileLoader;

public class WorldData {
    public int mapWidth, mapHeight;
    public tile[] tiles;
    public props[] props;
    public tileSuperclass mapTileNum[][];
    public boolean shouldCamMove;
    public WorldData(int mapWidth, int mapHeight, tile[] tiles, props[] props, tileSuperclass mapTileNum[][], boolean shouldCamMove) {
        this.mapWidth=mapWidth;
        this.mapHeight=mapHeight;
        this.tiles=tiles;
        this.props=props;
        this.mapTileNum=mapTileNum;
        this.shouldCamMove=shouldCamMove;
    }
}
