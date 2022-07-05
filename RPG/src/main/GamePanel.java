package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

//import character.Enemy;
import character.Player;
import object.MasterObject;
import skill.Projectile;
import tile.Map;
import tile.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	//setting for screen
	final int originalTileSize = 48; //tile size//
	final int scale = 1;
	
	public final int tileSize = originalTileSize * scale; // 48x1
	public final int maxScreenCol = 32; //32x48
	public final int maxScreenRow = 18; //18x48
	public final int screenWidth = tileSize * maxScreenCol; //48x24
	public final int screenHeight = tileSize * maxScreenRow; //48x16
	
	//setting for world
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	//System
	int FPS = 60;//game's refresh rate per second
	
	TileManager tile = new TileManager(this);
	//Map map = new Map(this);
	public KeyInput key = new KeyInput(this);
	Sound BGM = new Sound();
	Sound soundFX = new Sound();
	Thread gameThread;
	
	public CollisionCheck Colchecker = new CollisionCheck(this);
	public EventHandler Event = new EventHandler(this);
	public ItemGenerator ItemGen = new ItemGenerator(this);
	public Player player = new Player(this,key);
	public UI ui = new UI(this);
	
	public MasterObject obj[] = new MasterObject[10];
	public character.Character npc[] = new character.Character[10];
	public character.Character mob[] = new character.Character[20];
	
	public ArrayList<Projectile> projectileList = new ArrayList<>();
	
	public int GameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogState = 3;
	public final int statState = 4;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	    this.setBackground(Color.black);
	    this.addKeyListener(key);
	    this.setFocusable(true);// like set game mode input in UE 
	    //this.requestFocus();
	    this.setDoubleBuffered(true); //improve render for performance
	}
	//SET UP
	public void SetUpGame() {
		ItemGen.setObject();
		ItemGen.setNPC();
		//map.loadMap("Level1.txt");
		//playBGM(0);
		GameState = playState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	//This class is implemented Runnable -> this "run" method will automatically execute whenever there is a thread running. 
	public void run() {
		//this method run once
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/FPS;
		//per 1s run FPS times  
		double delta = 0;//interval timer
		long lastTime = System.nanoTime();//time of when this method execute
		long currentTime;
		long timer = 0;//timer in nano time;
		int drawCount = 0;
		
		//this part run multiple times per second
		//Game tick 
		while(gameThread != null) {
			// event tick with 0,016s interval
			//tick condition
			currentTime = System.nanoTime();//Current time of the system.
			
			delta += (currentTime - lastTime)/drawInterval;//delta increased with the interval of (current time - last time this part run)
			timer += (currentTime - lastTime);//timer increased with interval of cur time and last time, whenever it's reached 1s(1000000000 nano sec)
			lastTime = currentTime;//last time reset -> equal to current time 
			//System.out.println("FPS: " + (currentTime - lastTime));
			if(delta >= 1) {
				// = 1 when current timer == 0,016s
				//tick body
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				//when 1s passed 
				//reset draw count 
				drawCount = 0;
				//reset timer
				timer = 0;
			}
			
			
		}
	}
	public void update() {
		if(GameState == playState) {
			player.update();
			Event.checkEvent();
			
			for(int i = 0;i < npc.length;i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0;i < mob.length;i++) {
				if(mob[i] != null) {
					mob[i].update();
				}
			}
			for(int i = 0;i < projectileList.size();i++) {
				if(projectileList.get(i) != null) {
					projectileList.get(i).update();
				}
				if(projectileList.get(i).active == false) {
					projectileList.remove(i);
				}
			}
		}
		if(GameState == pauseState) {
			//Stop every action
		}
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tile.draw(g2);
		
		for(int i = 0; i < obj.length ;i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		for(int i = 0; i < npc.length;i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		for(int i = 0; i < mob.length;i++) {
			if(mob[i] != null) {
				mob[i].draw(g2);
			}
		}
		for(int i = 0; i < projectileList.size();i++) {
			if(projectileList.get(i) != null) {
				projectileList.get(i).draw(g2);
			}
		}
		player.draw(g2);
		//Paint player component
		ui.draw(g2);
		g2.dispose();//save memory
		
	}
	
	//Sound Stuff
	public void playBGM(int i) {
		if(GameState == pauseState) {
			BGM.stop();
		}
		BGM.setFile(i);
		BGM.play();
		BGM.loop();
	}
	public void stopSound() {
		BGM.stop();
	}
	public void playSound(int i) {
		soundFX.setFile(i);
		soundFX.play();
	}
	
}