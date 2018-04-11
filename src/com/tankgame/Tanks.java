package com.tankgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.frame.FrameConstants;
import com.frame.ImageUtil;

public class Tanks extends GameObject {
	private final BufferedImage[] dirs = new BufferedImage[4];
	private int turnCnt;
	private int fireCnt;
	private int direc;
	private ArrayList<Bullet> bullet = new ArrayList<>();
	
	public Tanks(String path0, String path1, String path2, String path3) throws FileNotFoundException, IOException {
		super(path0);
		dirs[0] = ImageUtil.creatImage(path0);
		dirs[1] = ImageUtil.creatImage(path1);
		dirs[2] = ImageUtil.creatImage(path2);
		dirs[3] = ImageUtil.creatImage(path3);
		this.direc = (int)(Math.random() * 4);
		this.setObj(dirs[direc]);
	}
	
	public void draw(Graphics g, GameObject[] obj, GameObject[] obj1) throws FileNotFoundException, IOException {
		if(getXy().getX() <= FrameConstants.LEFT_RIGHT_BOTTOM) {
			setXy(new Point(FrameConstants.LEFT_RIGHT_BOTTOM, (int)getXy().getY()));
			direc = (int)(Math.random() * 3);
		}
		if(getXy().getX() >= TGFConstants.WIDTH - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE) {
			setXy(new Point(TGFConstants.WIDTH - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE, (int)getXy().getY()));
			direc = (int)(Math.random() * 4);
		}
		if(getXy().getY() <= FrameConstants.TOP) {
			setXy(new Point((int)getXy().getX(), FrameConstants.TOP));
			direc = (int)(Math.random() * 3 + 1);
		}
		if(getXy().getY() >= TGFConstants.HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE) {
			setXy(new Point((int)getXy().getX(), TGFConstants.HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - TGFConstants.OBJ_SIZE));
			direc = (int)(Math.random() * 4);
		}
		g.drawImage(getObj(), (int)getXy().getX(), (int)getXy().getY(), null);
		randomMove(obj, obj1);
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
	
	public void randomMove(GameObject[] obj, GameObject[] obj1) throws FileNotFoundException, IOException {
		while(turnCnt == 30) {
			direc = (int)(Math.random() * 4);
			turnCnt = 0;
		}
		++turnCnt;
		setObj(dirs[direc]);
		switch (direc) {
		case 0:
			setXy(new Point((int)getXy().getX(), (int)(getXy().getY() - TGFConstants.TANKS_SPEED)));
			checkCol(obj);
			if(!isCol())
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)getXy().getX(), (int)(getXy().getY() + TGFConstants.TANKS_SPEED)));
				setCol(false);
			}
			break;
		case 1:
			setXy(new Point((int)(getXy().getX() + TGFConstants.TANKS_SPEED), (int)getXy().getY()));
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)(getXy().getX() - TGFConstants.TANKS_SPEED), (int)getXy().getY()));
				setCol(false);
			}
			break;
		case 2:
			setXy(new Point((int)getXy().getX(), (int)(getXy().getY() + TGFConstants.TANKS_SPEED)));
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)getXy().getX(), (int)(getXy().getY() - TGFConstants.TANKS_SPEED)));
				setCol(false);
			}
			break;
		case 3:
			setXy(new Point((int)(getXy().getX() - TGFConstants.TANKS_SPEED), (int)getXy().getY()));
			checkCol(obj);
			if(!isCol())	
				checkCol(obj1);
			if(isCol()) {
				setXy(new Point((int)(getXy().getX() + TGFConstants.TANKS_SPEED), (int)getXy().getY()));
				setCol(false);
			}
			break;
		default:
			break;
		}
		Bullet b = new Bullet(TGFConstants.BULLET_PATH, getXy(), direc);
		b.setDirection(direc);
		while(fireCnt == 20) {
			fireCnt = 0;
			bullet.add(b);
		}
		++fireCnt;
		
	}
}
