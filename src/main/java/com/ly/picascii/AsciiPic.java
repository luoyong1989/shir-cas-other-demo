package com.ly.picascii;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AsciiPic {

	/**
     * @param path
	 *            图片路径
	 */
	public static void createAsciiPic(final String path,int w,int h) throws IOException {
		final String base = "@#&$%*o!;.";// 字符串由复杂到简单

		String newPath = resize(w,h,path);
		try {
			final BufferedImage image = ImageIO.read(new File(newPath));
			for (int y = 0; y < image.getHeight(); y += 2) {
				for (int x = 0; x < image.getWidth(); x++) {
					final int pixel = image.getRGB(x, y);
					final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
					final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
					final int index = Math.round(gray * (base.length() + 1) / 255);
//					System.out.println(index);
					System.out.print(index >= base.length() ? "	" : String.valueOf(base.charAt(index)));
				}
				System.out.println();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}finally {
			new File(newPath).delete();
		}
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public static String resize(int w, int h,String path) throws IOException {
		String newPath = getNewPath(path);
		Image img = ImageIO.read(new File(path));
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(newPath);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
		return newPath;
	}

	public static String getNewPath(String path){;
		int i = path.lastIndexOf(".");
		String lastpaht = path.substring(i);
		String name = path.substring(0,i);
		path = name+"1"+lastpaht;
		System.out.println("path = " + path);
		return path;
	}
	/**
	 * test
	 *
	 * @param args
	 */
	public static void main(final String[] args) throws IOException {
		AsciiPic.createAsciiPic("/Users/ly/Desktop/5.jpeg",100,200);
		//resize(40,40,"/Users/ly/Desktop/WechatIMG30.jpeg");
	}
}