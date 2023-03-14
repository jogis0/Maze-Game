package main;

import object.Door;
import object.Gem;
import object.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font futura_40, futura_80;
    BufferedImage [] tileImages = new BufferedImage[8];
    BufferedImage keyImage, gemImage, trophyImage;
    public boolean gameFinished = false;
    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int n = 20;
    public int width = 25;
    int selectedTileIndex = 0;
    public UI(GamePanel gp) throws IOException {
        this.gp = gp;
        futura_40 = new Font("Futura", Font.BOLD, 40);
        futura_80 = new Font("Futura", Font.BOLD, 80);

        Key key = new Key();
        Gem gem = new Gem();
        Door door = new Door();

        keyImage = key.image;
        gemImage = gem.image;

        tileImages[0] = gp.tileM.tile[0].image;
        tileImages[1] = gp.tileM.tile[1].image;
        tileImages[2] = gp.tileM.tile[2].image;
        tileImages[3] = gp.tileM.tile[3].image;
        tileImages[4] = gp.tileM.tile[4].image;
        tileImages[5] = key.image;
        tileImages[6] = door.image;
        tileImages[7] = gem.image;

        trophyImage = ImageIO.read(getClass().getResourceAsStream("/res/endScreen/trophy.png"));
    }
    public void draw(Graphics2D g2){
        if(gp.gameState == gp.titleState){
            drawTitleScreen(g2);
        }else if(gp.gameState == gp.editState){
            drawEditor(g2);
        }else if(gp.gameState == gp.playState){
            if(gameFinished == true){
                String text;
                int textLenght;
                int x, y;

                g2.drawImage(trophyImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

                g2.setFont(futura_80);
                g2.setColor(Color.BLUE);
                text = "Congratulations!";
                textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLenght / 2;
                y = gp.screenHeight / 2 - gp.tileSize * 2;
                g2.drawString(text, x, y);

                g2.setFont(futura_40);
                g2.setColor(Color.WHITE);
                text = "Your time: " + dFormat.format(playTime);
                textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLenght / 2;
                y = gp.screenHeight / 2;
                g2.drawString(text, x, y);

                text = "Your score: " + gp.player.score;
                textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth / 2 - textLenght / 2;
                y = gp.screenHeight / 2 + gp.tileSize;
                g2.drawString(text, x, y);

                gp.gameThread = null;
            }else{
                playTime +=(double) 1 / 60;
                g2.setFont(futura_40);
                g2.setColor(Color.WHITE);
                g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);

                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.hasKey, 74, 65);

                g2.drawImage(gemImage, gp.tileSize / 2, gp.tileSize * 3 / 2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.score, 74, 110);
            }
        }
    }
    public void drawTitleScreen(Graphics2D g2){
        int x, y;
        String text;
        g2.setFont(futura_80);
        g2.setColor(Color.WHITE);

        //Title
        int titleLenght = (int)g2.getFontMetrics().getStringBounds("THE GREAT GAME", g2).getWidth();
        x = gp.screenWidth / 2 - titleLenght / 2;
        y = gp.screenHeight / 2 - gp.tileSize * 3;
        g2.drawString("THE GREAT GAME", x, y);

        //Start
        g2.setFont(futura_40);
        int startLenght = (int)g2.getFontMetrics().getStringBounds("START", g2).getWidth();
        x = gp.screenWidth / 2 - startLenght / 2;
        y = gp.screenHeight / 2 + gp.tileSize;
        g2.drawString("START", x, y);

        //Edit map
        int editLenght = (int)g2.getFontMetrics().getStringBounds("EDIT MAP", g2).getWidth();
        x = gp.screenWidth / 2 - editLenght / 2;
        y = gp.screenHeight / 2 + gp.tileSize * 2;
        g2.drawString("EDIT MAP", x, y);

        //Quit
        int quitLenght = (int)g2.getFontMetrics().getStringBounds("QUIT", g2).getWidth();
        x = gp.screenWidth / 2 - quitLenght / 2;
        y = gp.screenHeight / 2 + gp.tileSize * 3;
        g2.drawString("QUIT", x, y);
    }

    public void drawEditor(Graphics2D g2){
        int x, y;
        String text;
        g2.setFont(futura_40);
        g2.setColor(Color.WHITE);

        int titleLenght = (int)g2.getFontMetrics().getStringBounds("EDITOR", g2).getWidth();
        x = gp.screenWidth - titleLenght - 20;
        y = gp.screenHeight / 2 - gp.tileSize * 5;
        g2.drawString("EDITOR", x, y);

        int saveLenght = (int)g2.getFontMetrics().getStringBounds("SAVE", g2).getWidth();
        x = gp.screenWidth - saveLenght - 20;
        y = gp.screenHeight / 2 - gp.tileSize * 2;
        g2.drawString("SAVE", x, y);

        int returnLength = (int)g2.getFontMetrics().getStringBounds("RETURN", g2).getWidth();
        x = gp.screenWidth - returnLength - 20;
        y = gp.screenHeight / 2 + gp.tileSize * 5;
        g2.drawString("RETURN", x, y);

        //Map Buttons
        int mapLenght = (int)g2.getFontMetrics().getStringBounds("MAP 01", g2).getWidth();
        x = gp.screenWidth - mapLenght - 20;
        y = gp.screenHeight / 2;
        g2.drawString("MAP 01", x, y);

        x = gp.screenWidth - mapLenght - 20;
        y = gp.screenHeight / 2 + gp.tileSize;
        g2.drawString("MAP 02", x, y);

        x = gp.screenWidth - mapLenght - 20;
        y = gp.screenHeight / 2 + gp.tileSize * 2;
        g2.drawString("MAP 03", x, y);

        //Map grid
        Rectangle [][] mapGrid = new Rectangle [n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mapGrid[i][j] = new Rectangle(j * width, i * width, width, width);
                g2.drawImage(tileImages[gp.tileM.mapLoaded[i][j]], j * width, i * width, width, width, null);
                g2.draw(mapGrid[i][j]);
            }
        }

        //Tile grid
        int tileNum = 8;
        Rectangle [] tileGrid = new Rectangle[tileNum];
        for(int i = 0; i < tileNum; ++i){
            tileGrid[i] = new Rectangle(i * width, 21 * width, width, width);
            g2.drawImage(tileImages[i], i * width, 21 * width, width, width, null);
            g2.draw(tileGrid[i]);
        }
    }
}
