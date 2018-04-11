package com.frame;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * ͨ�ô����࣬���ڼ��ش��ں��������߳�
 * @author HuanCao
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends Frame {
	//���ڿ��
	private int width = FrameConstants.WIDTH;
	//���ڸ߶�
	private int height = FrameConstants.HEIGHT;
	//���ڱ���
	private String title = FrameConstants.TITLE;
	
	/* ����˽�����Ե�Getter��Setter */
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
	
	/* ���ش��� */
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

	/* �ڲ��߳��� */
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
	
	/* ˫���弼�������˸���� */
	private Image offscreenImage = null;
	public void update(Graphics g) {
		if(offscreenImage == null)
			offscreenImage = this.createImage(width, height);
		Graphics gOff = offscreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offscreenImage, 0, 0, null);
	}
	
}
