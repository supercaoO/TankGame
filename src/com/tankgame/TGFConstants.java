package com.tankgame;

import java.awt.Point;

import com.frame.FrameConstants;

/**
 * 坦克游戏的伴随常量类
 * @author HuanCao
 *
 */
public class TGFConstants {
	public static final int WIDTH = 1000 + 2 * FrameConstants.LEFT_RIGHT_BOTTOM;
	public static final int HEIGHT = 1000 + FrameConstants.LEFT_RIGHT_BOTTOM + FrameConstants.TOP;
	public static final String TITLE = "TANK GAME";
	public static final int OBJ_SIZE = 50;
	public static final int OBJ_WNUM = (WIDTH - 2 * FrameConstants.LEFT_RIGHT_BOTTOM) / OBJ_SIZE;
	public static final int OBJ_HNUM = (HEIGHT - FrameConstants.LEFT_RIGHT_BOTTOM - FrameConstants.TOP) / OBJ_SIZE;
	public static Point[] POSITION = new Point[OBJ_WNUM * OBJ_HNUM];
	static {
		for(int i = 0; i < OBJ_HNUM; ++i)
			for(int j = 0; j < OBJ_WNUM; ++j) {
				POSITION[i * OBJ_HNUM + j] = new Point(FrameConstants.LEFT_RIGHT_BOTTOM + j * OBJ_SIZE, FrameConstants.TOP + i * OBJ_SIZE);
			}
	}
	public static int LAST_POS = 399;
	public static final int TANK_NUM = 12;
	public static final int OBS_NUM = (OBJ_HNUM * OBJ_WNUM - TANK_NUM - 1) * 2 / 3;
	public static final int TANK_SPEED = 10;
	public static final int TANKS_SPEED = 3;
	public static final String BULLET_PATH = "E:\\JAVA-PROJECTS\\TankGame\\src\\Images\\Bullet.png";
	public static final int BULLET_SPEED = 10;
}
