package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16p x 16p tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48p x 48p tile
    public final int maxScreenCol = 16; // 4:3 Aspect ratio
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768p
    public final int screenHeight = tileSize * maxScreenRow; // 576p

    //World settings
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public AssetManager am = new AssetManager(this);
    public SuperObject obj[] = new SuperObject[400];
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    MouseInput mouseI = new MouseInput(this);
    public Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);

    public int gameState;
    public final int playState = 1;
    public final int titleState = 2;
    public final int editState = 3;

    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Drawing will be done in an offscreen buffer
        this.addKeyListener(keyH);
        this.addMouseListener(mouseI);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000 / FPS; // 0,0166 seconds
        double nextDrawTime = System.currentTimeMillis() + drawInterval;

        while(gameThread != null){
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setupGame(){
        gameState = titleState;
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        if(gameState == titleState || gameState == editState){
            ui.draw(g2);
        }else{
            tileM.draw(g2);
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            player.draw(g2);
            ui.draw(g2);
        }
        g2.dispose();
    }
}
