package com.tankgame;

import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.frame.ImageUtil;

public class Bullet extends GameObject {
	private int direction;
	private int dx;
	private int dy;
	private int size;

	public Bullet(String imgpath, Point p, int direc) throws FileNotFoundException, IOException {
		setObj(ImageUtil.creatImage(imgpath));
		this.size = getObj().getWidth();
		switch (direc) {
			case 0:
				setXy(	new Point((int)(p.getX() + TGFConstants.OBJ_SIZE / 2 - getSize() / 2 ),
						(int)p.getY()));
				break;
			case 1:
				setXy(	new Point((int)(p.getX() + TGFConstants.OBJ_SIZE - 7),
						(int)(p.getY() + TGFConstants.OBJ_SIZE / 2 - getSize() / 2)));
				break;
			case 2:
				setXy(	new Point((int)(p.getX() + TGFConstants.OBJ_SIZE / 2 - getSize() / 2),
						(int)(p.getY() + TGFConstants.OBJ_SIZE) - 7));
				break;
			case 3:
				setXy(new Point((int)p.getX(), (int)(p.getY() + TGFConstants.OBJ_SIZE / 2 - getSize() / 2)));
				break;
			default:
				break;
		}
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void draw(Graphics g, GameObject[] obj, GameObject[] obj1) {
		if(!isCol()) {
			int i = checkCol(obj);
			if(i == -1) {
				int i1 = checkCol(obj1);
				if(i1 != -1) {
					obj1[i1] = null;
					setCol(true);
				}
			}
			else {
				obj[i] = null;
				setCol(true);
			}
		}
		if(!isCol()) {
			setShoot();
			setXy(new Point((int)(getXy().getX() + dx), (int)(getXy().getY() + dy)));
			g.drawImage(getObj(), (int)getXy().getX(), (int)getXy().getY(), null);
			
		}
	}
	
	public void setShoot() {
		switch (direction) {
		case 0:
			dx = 0;
			dy = - TGFConstants.BULLET_SPEED;
			break;
		case 1:
			dx = TGFConstants.BULLET_SPEED;
			dy = 0;
			break;
		case 2:
			dx = 0;
			dy = TGFConstants.BULLET_SPEED;
			break;
		case 3:
			dx = - TGFConstants.BULLET_SPEED;
			dy = 0;
			break;
		default:
			break;
		}
	}
}
