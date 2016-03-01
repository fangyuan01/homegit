package picture;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class Picture {

	private static ArrayList<String> filelist = new ArrayList<String>();

	public static void main(String[] args) throws UnsupportedEncodingException {

		// System.out.println("start");
		getFiles("source");
		Collections.sort(filelist, new FileCompare());
		// // cpy("170");
		// // cpy("230");
		// cpy("310");
		// // cpy("800");
		// // cpy800();
		sizeThread(170);
		sizeThread(230);
		// // sizeThread(310);
	}

	public static void cpy(String s) {
		for (String it : filelist) {
			String strs[] = it.split("\\\\");
			String size = strs[5];
			String newDir = "result\\" + strs[4] + "\\" + size + "\\";
			if (!size.equals(s)) {
				// copyFile(it, newDir + strs[7]);
			} else {
				if (!lastDir.equals(newDir)) {
					lastDir = newDir;
					lastcount = 1;
				} else {
					lastcount++;
				}
				String str = new DecimalFormat("00").format(lastcount); // 补零
				copyFile(it, newDir + str + ".jpg");
			}
		}
	}

	public static void sizeThread(final int size) {
		lastcount = 0;
		for (String it : filelist) {
			try {
				if (it.endsWith(".jpg")) {
					System.out.println(it);
					resizeImage(new File(it), new File("result"), size, "jpg");
				}
			} catch (Exception e) {
			}
		}
	}

	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	static void getFiles(String filePath) {
		File root = new File(filePath);
		if (!root.exists()) {
			System.out.println(filePath + " 不存在");
			return;
		}
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFiles(file.getAbsolutePath());
			} else {
				if (file.getParentFile().getName().equals("310")) {
					filelist.add(file.getAbsolutePath());
				}
			}
		}
	}

	private static int lastcount = 1;
	private static String lastDir = "";

	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * 
	 * @param is
	 *            上传的图片的输入流
	 * @param os
	 *            改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size
	 *            新图片的宽
	 * @param format
	 *            新图片的格式
	 * @throws IOException
	 */
	public static void resizeImage(File sourceFile, File outDir, int size, String format) throws IOException {
		InputStream is = new FileInputStream(sourceFile);
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent = size / width;
		int newWidth = (int) (width * percent);
		int newHeight = (int) (height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_USHORT_555_RGB);
		// 创建新图
		Graphics2D graphics2d = image.createGraphics();
		// 设置“抗锯齿”的属性
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		graphics2d.drawImage(prevImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

		String strs[] = sourceFile.getAbsolutePath().split("\\\\");
		File dirFile = new File(outDir + "/" + strs[4].toLowerCase());
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		System.out.println("lastdis->" + lastDir);
		if (!lastDir.equals(dirFile.getAbsolutePath())) {
			lastDir = dirFile.getAbsolutePath();
			lastcount = 1;
		} else {
			lastcount++;
		}
		System.out.println(dirFile.getAbsolutePath() + "/" + size);

		File sizeDir = new File(dirFile.getAbsolutePath() + "/" + size);
		if (!sizeDir.exists()) {
			sizeDir.mkdir();
		}

		File outFile;
		if (lastcount < 10) {
			outFile = new File(dirFile.getAbsolutePath() + "/" + size + "/0" + lastcount + "." + format);
		} else {
			outFile = new File(dirFile.getAbsolutePath() + "/" + size + "/" + lastcount + "." + format);
		}
		FileOutputStream fos = new FileOutputStream(outFile);

		ImageIO.write(image, format, fos);
		fos.flush();
		is.close();
		fos.close();
	}

	/**
	 * 复制单个文件,如果路径不存在则创建
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:\\fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:\\fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		System.out.println("copy: " + oldPath + " to->" + newPath);

		String[] strs = newPath.split("\\\\");
		String pathnow = strs[0];
		for (int i = 1; i < strs.length; i++) {
			File file = new File(pathnow);
			if (!file.exists()) {
				file.mkdir();
			}
			pathnow = pathnow + "\\" + strs[i];
		}

		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(new File(oldPath));
			fo = new FileOutputStream(new File(newPath));
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
