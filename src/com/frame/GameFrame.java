package com.frame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 通用窗口类，用于加载窗口和启动画线程
 * @author HuanCao
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends Frame {
	//窗口宽度
	private int width = FrameConstants.WIDTH;
	//窗口高度
	private int height = FrameConstants.HEIGHT;
	//窗口标题
	private String title = FrameConstants.TITLE;
	
	/* 三个私有属性的Getter和Setter */
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* 加载窗口 */
	public void launchFrame() {
		setLocation(FrameConstants.X_LOCATION, FrameConstants.Y_LOCATION);
		setSize(width, height);
		setTitle(title);
		setVisible(true);
		
		new PaintThread().start();
		
		addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		});
	}

	/* 内部线程类 */
	class PaintThread extends Thread {

		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* 双缓冲技术解决闪烁问题 */
	private Image offscreenImage = null;
	public void update(Graphics g) {
		if(offscreenImage == null)
			offscreenImage = this.createImage(width, height);
		Graphics gOff = offscreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offscreenImage, 0, 0, null);
	}
	
}
