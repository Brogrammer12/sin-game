package Main;
import javax.swing.JPanel;

import Cutscenes.cutsceneManager;
import Entities.Player;
import Menus.textDrawer;
import Menus.titleScreen;
import Menus.wordWrapper;
import tileLoader.tileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class gamepanel extends JPanel implements Runnable{
Thread thread;
public final int FPS=60;
public static final int ogTileSize=16;
    public static final int scale=3;
    public static final int resTileSize=ogTileSize*scale;
    public static final int maxScreenHoriz=20;
    public static final int maxScreenVert=12;
    public int screenWidth=resTileSize*maxScreenHoriz;
    public int screenHeight=resTileSize*maxScreenVert;
    public static final int GAME_WIDTH=960;
    public static final int GAME_HEIGHT=576;
    public int maxWorldHoriz=19;
    public int maxWorldVert=11;
    public int worldWidth=maxWorldHoriz*resTileSize;
    public int worldHeight=maxWorldVert*resTileSize;
    public boolean gameProcess=false;
    public String currentCutscene="meteorScene";
    public float delta=0f;
    public gameState state=gameState.TITLE_SCREEN;
public double lastTime;
public inputListener listener=new inputListener();
public titleScreen title=new titleScreen(this);
public textDrawer tDrawer=new textDrawer();
public wordWrapper wordBoi=new wordWrapper();
public cutsceneManager cManager=new cutsceneManager(this);
public gameProcessor gProcessor=new gameProcessor(this);
public Player p1=new Player(this);
public tileManager tManager=new tileManager(this);
public gamepanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(listener);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
}
public void startGame() {
    thread=new Thread(this);
    thread.start();
    this.requestFocusInWindow();
}
    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double nextDrawTime=System.nanoTime()+drawInterval;
        lastTime=System.nanoTime();
        while (thread!=null) {
            double now = System.nanoTime();
            delta = (float) ((now - lastTime) / 1_000_000_000.0); // seconds
            lastTime = now;
            update();
            repaint();
            //drawToTempScreen();
           // drawToScreen();
            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0) {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        screenWidth=getWidth();
        screenHeight=getHeight();
        double scaleX=screenWidth/(double)GAME_WIDTH;
        double scaleY=screenHeight/(double)GAME_HEIGHT;
        g2.scale(scaleX, scaleY);
        drawGame(g2, scaleX, scaleY);
    }
    public void drawGame(Graphics2D g2, double scaleX, double scaleY) {
        //gProcessor.runGame(g2);
        if (state==gameState.GAMEPLAY) {
            tManager.draw(g2);
        }
        if (state==gameState.TITLE_SCREEN) {
            title.drawTitleScreen(g2, scaleX, scaleY);
        }
        if (state==gameState.CUTSCENE) {
            cManager.startCutscene(currentCutscene, g2);
        }
    }
}
