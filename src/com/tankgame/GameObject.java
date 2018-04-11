package com.tankgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.frame.ImageUtil;

/**
 * 游戏物体类
 * @author HuanCao
 *
 */
public class GameObject {
	private BufferedImage obj;
	private Point xy;
	private boolean col;
	
	public GameObject() {
		
	}
	
	public GameObject(String imgpath) throws FileNotFoundException, IOException {
		obj = ImageUtil.creatImage(imgpath);
		int i = (int)(Math.random() * (TGFConstants.LAST_POS + 1));
		this.setXy(TGFConstants.POSITION[i]);
		TGFConstants.POSITION[i] = TGFConstants.POSITION[TGFConstants.LAST_POS];
		--TGFConstants.LAST_POS;
	}
	
	public boolean isCol() {
		return col;
	}

	public void setCol(boolean col) {
		this.col = col;
	}

	public BufferedImage getObj() {
		return obj;
	}

	public Point getXy() {
		return xy;
	}

	public void setXy(Point xy) {
		this.xy = xy;
	}

	public void setObj(BufferedImage obj) {
		this.obj = obj;
	}

	public void draw(Graphics g) {
		g.drawImage(obj, (int)xy.getX(), (int)xy.getY(), null);
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)xy.getX(), (int)xy.getY(), this.obj.getWidth(), this.obj.getHeight());
	}
	
	public int checkCol(GameObject[] obj) {
		for(int i = 0; i < obj.length; ++i) {
			if(obj[i] != null)
				setCol(this.getRect().intersects(obj[i].getRect()));
			if(isCol())
				return i;
		}
		return -1;
	}
}
