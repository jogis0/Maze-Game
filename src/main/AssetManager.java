package main;

import object.Door;
import object.Gem;
import object.Key;

import java.util.Arrays;

public class AssetManager {
    GamePanel gp;
    public AssetManager(GamePanel gp){
        this.gp = gp;
    }

    public void setKey(int i, int x, int y){
        gp.obj[i] = new Key();
        gp.obj[i].worldX = x * gp.tileSize;
        gp.obj[i].worldY = y * gp.tileSize;
    }

    public void setDoor(int i, int x, int y){
        gp.obj[i] = new Door();
        gp.obj[i].worldX = x * gp.tileSize;
        gp.obj[i].worldY = y * gp.tileSize;
    }

    public void setGem(int i, int x, int y){
        gp.obj[i] = new Gem();
        gp.obj[i].worldX = x * gp.tileSize;
        gp.obj[i].worldY = y * gp.tileSize;
    }

    public void clear(){
        Arrays.fill(gp.obj, null);
    }
}
