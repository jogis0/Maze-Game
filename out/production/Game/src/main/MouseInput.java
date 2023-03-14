package main;

import com.opencsv.CSVWriter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.file.Path;

public class MouseInput implements MouseListener {

    GamePanel gp;
    String map01 = "res/maps/map01.csv";
    String map02 = "res/maps/map02.csv";
    String map03 = "res/maps/map03.csv";
    String mapPath = map01;
    public MouseInput(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        //Check if mouse clicked on tile grid or tile selection strip
        if(gp.gameState == gp.editState){
            for(int i = 0; i < gp.ui.n; i++){
                for(int j = 0; j < gp.ui.n; j++){
                    if(mouseX >= j * gp.ui.width && mouseX <= (j + 1) * gp.ui.width && mouseY >= i * gp.ui.width && mouseY <= (i + 1) * gp.ui.width){
                        gp.tileM.mapLoaded[i][j] = gp.ui.selectedTileIndex;
                    }
                }
                if(mouseX >= i * gp.ui.width && mouseX <= (i + 1) * gp.ui.width && mouseY >= 21 * gp.ui.width && mouseY <= 22 * gp.ui.width){
                    gp.ui.selectedTileIndex = i;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        //"Start" lenght = 132, "Edit Map" = 190, "Quit" = 96
        if(gp.gameState == gp.titleState){
            if(mouseX >= gp.screenWidth / 2 - 66 && mouseX <= gp.screenWidth / 2 + 66){
                if(mouseY <= gp.screenHeight / 2 + gp.tileSize && mouseY >= gp.screenHeight / 2 + gp.tileSize - 40){
                    gp.gameState = gp.playState;
                }
            }
            if(mouseX >= gp.screenWidth / 2 - 95 && mouseX <= gp.screenWidth / 2 + 95){
                if(mouseY <= gp.screenHeight / 2 + gp.tileSize * 2 && mouseY >= gp.screenHeight / 2 + gp.tileSize * 2 - 40){
                    gp.gameState = gp.editState;
                }
            }
            if(mouseX >= gp.screenWidth / 2 - 48 && mouseX <= gp.screenWidth / 2 + 48){
                if(mouseY <= gp.screenHeight / 2 + gp.tileSize * 3 && mouseY >= gp.screenHeight / 2 + gp.tileSize * 3 - 40){
                    System.exit(1);
                }
            }
        }else if(gp.gameState == gp.editState){
            //Save Button
            if(mouseX >= gp.screenWidth - 127 && mouseX <= gp.screenWidth - 20){
                if(mouseY <= gp.screenHeight / 2 - gp.tileSize * 2 && mouseY >= gp.screenHeight / 2 - gp.tileSize * 2 - 40){
                    try {
                        File file = new File(mapPath);
                        FileWriter outputFile = new FileWriter(file);
                        CSVWriter writer = new CSVWriter(outputFile, ';',
                                CSVWriter.NO_QUOTE_CHARACTER,
                                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                CSVWriter.DEFAULT_LINE_END);

                        System.out.println("Save button pressed!");

                        for(int i = 0; i < gp.maxWorldRow; ++i){
                            String [] line = new String[gp.maxWorldCol];
                            for(int j = 0; j < gp.maxWorldCol; ++j){
                                line[j] = Integer.toString(gp.tileM.mapLoaded[i][j]);
                            }
                            writer.writeNext(line);
                        }
                        writer.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            //Map Buttons
            if(mouseX >= gp.screenWidth - 143 && mouseX <= gp.screenWidth - 20){
                if(mouseY <= gp.screenHeight / 2 && mouseY >=gp.screenHeight / 2 - 40){
                    mapPath = map01;
                    gp.tileM.loadMap(map01);
                }else if(mouseY <= gp.screenHeight / 2 + gp.tileSize && mouseY >= gp.screenHeight / 2 + gp.tileSize - 40){
                    mapPath = map02;
                    gp.tileM.loadMap(map02);
                }else if(mouseY <= gp.screenHeight / 2 + gp.tileSize * 2 && mouseY >= gp.screenHeight / 2 + gp.tileSize * 2 - 40){
                    mapPath = map03;
                    gp.tileM.loadMap(map03);
                }
            }

            //Return Button
            if(mouseX >= gp.screenWidth - 160 && mouseX <= gp.screenWidth - 20){
                if(mouseY <= gp.screenHeight / 2 + gp.tileSize * 5 && mouseY >= gp.screenHeight / 2 + gp.tileSize * 5 - 40){
                    gp.gameState = gp.titleState;
                    gp.tileM.loadMap(map01);
                }

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
