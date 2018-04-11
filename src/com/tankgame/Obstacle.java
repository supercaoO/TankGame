package com.tankgame;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ’œ∞≠ŒÔ¿‡
 * @author HuanCao
 *
 */
public class Obstacle extends GameObject {

	public Obstacle(String imgpath) throws FileNotFoundException, IOException {
		super(imgpath);
	}
	
	public void draw(Graphics g) {
		if(getObj() != null)
			g.drawImage(getObj(), (int)getXy().getX(), (int)getXy().getY(), null);
	}
}
