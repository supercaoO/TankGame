package com.tankgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.frame.FrameConstants;
import com.frame.GameFrame;
import com.frame.ImageUtil;

/**
 * 坦克游戏窗口类
 * @author HuanCao
 *
 */

@SuppressWarnings("serial")
public class TankGameFrame extends GameFrame {
	private BufferedImage loading;
	private Tank me;
	private Obstacle[] obs = new Obstacle[TGFConstants.OBS_NUM];
	private Tanks[] ts = new Tanks[TGFConstants.TANK_NUM];
	private boolean enter;
	private boolean again;
	private boolean win;
	private int deadTanks;
	
	public TankGameFrame() throws FileNotFoundException, IOException {
		this.setWidth(TGFConstants.WIDTH);
		this.setHeight(TGFConstants.HEIGHT);
		this.setTitle(TGFConstants.TITLE);
		this.loading = ImageUtil.creatImage("E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Loading.jpg");
		this.me = new Tank( "E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank0.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank1.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank2.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank3.png");
		for(int i = 0; i < TGFConstants.OBS_NUM; ++i) {
			obs[i] = new Obstacle("E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Obstacle.png");
		}
		for(int i = 0; i < TGFConstants.TANK_NUM; ++i) {
			ts[i] = new Tanks(  "E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks0.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks1.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks2.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks3.png");
		}
	}
	
	
	
	@Override
	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
	}
	
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				enter = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_B) {
				again = true;
				win = false;
			}
			me.pressDir(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			me.releaseDir(e);
		}
		
	}

	private static void printInfo(Graphics g, String mes, String font, int x, int y, int pixelSize, Color color) {
		Color c = g.getColor();
		g.setColor(color);
		g.setFont(new Font(font, Font.BOLD, pixelSize));
		g.drawString(mes, x, y);
		g.setColor(c);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(loading, FrameConstants.LEFT_RIGHT_BOTTOM, FrameConstants.TOP, null);
		if(enter) {
			for(Obstacle o : obs)
				if(o != null)
					o.draw(g);
			GameObject[] m = new GameObject[1];
			m[0] = me;
			deadTanks = 0;
			for(Tanks t : ts)
				if(t != null)
					try {
						t.draw(g, obs, m);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				else
					++deadTanks;
			if(deadTanks == TGFConstants.TANK_NUM)
				win = true;
			if(m[0] == null)
				me.setAlive(false);
			if(me.isAlive()) {
				try {
					me.draw(g, obs, ts);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(win) {
					printInfo( g, "WINNER!", "Berlin Sans FB Demi",
							(int)(TGFConstants.WIDTH * 0.26), TGFConstants.HEIGHT / 2, 120, Color.DARK_GRAY);
				}
			}
			else {
				printInfo( g, "GAME OVER", "Berlin Sans FB Demi",
						(int)(TGFConstants.WIDTH * 0.22), TGFConstants.HEIGHT / 2, 100, Color.DARK_GRAY);
			}
		}
		else {
			printInfo( g, "Press Enter to Begin", "High Tower Text",
					(int)(TGFConstants.WIDTH * 0.25), TGFConstants.HEIGHT / 3, 55, Color.DARK_GRAY);
			printInfo( g, "PRESS SPACE TO SHOOT", "Berlin Sans FB",
					FrameConstants.LEFT_RIGHT_BOTTOM * 2, TGFConstants.HEIGHT - 2 * FrameConstants.LEFT_RIGHT_BOTTOM, 20, Color.DARK_GRAY);
		}
		if(again) {
			again = false;
			try {
				for(int i = 0; i < TGFConstants.OBJ_HNUM; ++i)
					for(int j = 0; j < TGFConstants.OBJ_WNUM; ++j) {
						TGFConstants.POSITION[i * TGFConstants.OBJ_HNUM + j] = new Point(FrameConstants.LEFT_RIGHT_BOTTOM + j * TGFConstants.OBJ_SIZE, FrameConstants.TOP + i * TGFConstants.OBJ_SIZE);
					}
				TGFConstants.LAST_POS = 399;
				me = new Tank(  "E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank0.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank1.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank2.png",
								"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tank3.png");
				for(int i = 0; i < TGFConstants.OBS_NUM; ++i) {
					obs[i] = new Obstacle("E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Obstacle.png");
				}
				for(int i = 0; i < TGFConstants.TANK_NUM; ++i) {
					ts[i] = new Tanks(  "E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks0.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks1.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks2.png",
							"E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Tanks3.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
