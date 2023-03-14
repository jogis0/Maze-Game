package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapLoaded[][];
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        mapLoaded = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("res/maps/map01.csv");
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bush.png"));
            tile[4].collision = true;

        }catch(IOException e){
           e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            int col = 0;
            int row = 0;
            int i = 0;
            while((line = br.readLine()) != null && col < gp.maxWorldCol && row < gp.maxWorldRow){
                while(col < gp.maxWorldCol){
                    String [] numbers = line.split(";"); //Splits string at " " and stores the numbers normally
                    int num = Integer.parseInt(numbers[col]);
                    mapLoaded[row][col] = num;
                    if(num == 5){
                        gp.am.setKey(i++, col, row);
                        num = 0;
                    }else if(num == 6){
                        gp.am.setDoor(i++, col, row);
                        num = 0;
                    }else if(num == 7) {
                        gp.am.setGem(i++, col, row);
                        num = 0;
                    }
                    mapTileNum[row][col] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
