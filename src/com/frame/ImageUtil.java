package com.frame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 用于读取本地图片的工具类
 * @author HuanCao
 *
 */
public class ImageUtil {
	public static BufferedImage creatImage(String imgPath) throws FileNotFoundException, IOException {
		File img = new File(imgPath);
		return ImageIO.read(new FileInputStream(img));
	}
}
