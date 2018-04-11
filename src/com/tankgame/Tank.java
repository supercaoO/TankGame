package com.tankgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.frame.FrameConstants;
import com.frame.ImageUtil;

/**
 * ÃπøÀ¿‡
 * @author HuanCao
 *
 */
public class Tank extends GameObject {
	private final BufferedImage[] dirs = new BufferedImage[4];
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean fire;
	private int cnt;
	private int direc;
	private ArrayList<Bullet> bullet = new ArrayList<>();
	private boolean alive = true;
	
	public Tank(String path0, String path1, String path2, String path3) throws FileNotFoundException, IOException {
		super(path0);
		dirs[0] = ImageUtil.creatImage(path0);
		dirs[1] = ImageUtil.creatImage(path1);
		dirs[2] = ImageUtil.creatImage(path2);
		dirs[3] = ImageUtil.creatImage(path3);
		this.direc = (int)(Math.random() * 4);
		this.setObj(dirs[direc]);
		this.cnt = 0;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void draw(Graphics g, GameObject[] obj, GameObject[] obj1) throws FileNotFoundException, IOException {
		if(getXy().getX() <= FrameConstants.LEFT_RIGHT_BOTTOM)
			setXy(new Point(FrameConstants.LEFT_RIGHT_BOTTOM, (int)getXy().getY()));
		if(getXy().getX() >= TGFConstants.WIDTH - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE)
			setXy(new Point(TGFConstants.WIDTH - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE, (int)getXy().getY()));
		if(getXy().getY() <= FrameConstants.TOP)
			setXy(new Point((int)getXy().getX(), FrameConstants.TOP));
		if(getXy().getY() >= TGFConstants.HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE)
			setXy(new Point((int)getXy().getX(), TGFConstants.HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE));
		g.drawImage(getObj(), (int)getXy().getX(), (int)getXy().getY(), null);
		keyMove(obj, obj1);
		for(Bullet blt : bullet) {
			if( blt.getXy().getX() <= FrameConstants.LEFT_RIGHT_BOTTOM ||
				blt.getXy().getX() >= TGFConstants.WIDTH - FrameConstants.LEFT_RIGHT_BOTTOM - blt.getSize() ||
				blt.getXy().getY() <= FrameConstants.TOP ||
				blt.getXy().getY() >= TGFConstants.HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - blt.getSize())
			{
				blt = null;
			}
			if(blt != null)
				blt.draw(g, obj, obj1);
		}
	}
	
	public void keyMove(GameObject[] obj, GameObject[] obj1) throws FileNotFoundException, IOException {
		if(up)
			direc = 0;
		else if(right)
			direc = 1;
		else if(down)
			direc = 2;
		else if(left)
			direc = 3;
		if(up) {
			setXy(new Point((int)getXy().getX(), (int)getXy().getY() - TGFConstants.TANK_SPEED));
			setObj(dirs[0]);
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)getXy().getX(), (int)getXy().getY() + TGFConstants.TANK_SPEED));
				setCol(false);
			}
		}
		else if(right) {
			setXy(new Point((int)(getXy().getX() + TGFConstants.TANK_SPEED), (int)getXy().getY()));
			setObj(dirs[1]);
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)(getXy().getX() - TGFConstants.TANK_SPEED), (int)getXy().getY()));
				setCol(false);
			}
		}
		else if(down) {
			setXy(new Point((int)getXy().getX(), (int)(getXy().getY() + TGFConstants.TANK_SPEED)));
			setObj(dirs[2]);
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)getXy().getX(), (int)(getXy().getY() - TGFConstants.TANK_SPEED)));
				setCol(false);
			}
		}
		else if(left) {
			setXy(new Point((int)(getXy().getX() - TGFConstants.TANK_SPEED), (int)getXy().getY()));
			setObj(dirs[3]);
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)(getXy().getX() + TGFConstants.TANK_SPEED), (int)getXy().getY()));
				setCol(false);
			}
		}
		Bullet b = new Bullet(TGFConstants.BULLET_PATH, getXy(), direc);
		b.setDirection(direc);
		if(fire) {
			if(cnt == 0) {
				cnt = 10;
				bullet.add(b);
			}
		}
		--cnt;
		if(cnt < 0)
			cnt = 0;
	}
	
	public void pressDir(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			left = true;
			break;
		case 38:
			up = true;
			break;
		case 39:
			right = true;
			break;
		case 40:
			down = true;
			break;
		case KeyEvent.VK_W:
			fire = true;
			break;
		default:
			break;
		}
	}
	
	public void releaseDir(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			left = false;
			break;
		case 38:
			up = false;
			break;
		case 39:
			right = false;
			break;
		case 40:
			down = false;
			break;
		case KeyEvent.VK_W:
			fire = false;
			break;
		default:
			break;
		}
	}
}
