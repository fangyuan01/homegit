package picture;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class FyPicture {

	// 裁切1 a(459,496) b(1662,1395)
	// 裁切2 a(1807,496) b(3010,1395)
	// 裁切3 a(459,1455) b(1662,2354)
	// 裁切4 a(1807,1455) b(3010,2354)

	// 裁切1 a(10,10) b(1590,1190)
	// 裁切2 a(10,1210) b(1590,2390)
	// 裁切3 a(10,2410) b(1590,3590)
	// 裁切4 a(10,3610) b(1590,4790)

	private static String proName = "dbt";

	private final static String SOURCE_DIR = "source";
	private final static String RESULT_DIR = "result";

	public static void main(String[] args) {
		File sourceDir = new File(SOURCE_DIR);
		if (!sourceDir.exists()) {
			sourceDir.mkdir();
		}
		File resultDir = new File(RESULT_DIR);
		if (!resultDir.exists()) {
			resultDir.mkdir();
		}
		// if (args[0] != null) {
		// proName = args[0];
		// }
		ArrayList<String> paths = FileUtils.loadFiles(SOURCE_DIR);
		Collections.sort(paths, new FileCompare());
		cp800(paths);
		size(paths, 170);
		size(paths, 230);
		size(paths, 310);
		System.out.println("finish!");
	}

	public final static void cp800(ArrayList<String> paths) {
		int i = 0;
		int section = 0;
		int pi = 1;
		for (String item : paths) {
			if (i % 4 == 0) {
				pi = 1;
				section++;
			}
			FileUtils.existsFile((RESULT_DIR + "\\" + proName + formatInt(section, 3) + "\\" + 800));
			File dirOneSection = new File(RESULT_DIR + "\\" + proName + formatInt(section, 3) + "\\" + 800);
			if (!dirOneSection.exists()) {
				dirOneSection.mkdir();
			}

			ImageUtils.scaleWidth(item, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 800);
			pi++;

			i++;
			System.out.println("load files->" + item);
		}
	}

	public final static void size(ArrayList<String> paths, int size) {
		int i = 0;
		int section = 0;
		int pi = 1;
		for (String item : paths) {
			if (i % 4 == 0) {
				pi = 1;
				section++;
			}
			FileUtils.existsFile((RESULT_DIR + "\\" + proName + formatInt(section, 3) + "\\" + size));
			File dirOneSection = new File(RESULT_DIR + "\\" + proName + formatInt(section, 3) + "\\" + size);
			if (!dirOneSection.exists()) {
				dirOneSection.mkdir();
			}

			// 裁切1 a(459,496) b(1662,1395)
			// 裁切2 a(1807,496) b(3010,1395)
			// 裁切3 a(459,1455) b(1662,2354)
			// 裁切4 a(1807,1455) b(3010,2354)

			// 读取源图像
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(item));
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 459, 496, 1203, 899, size);
				pi++;
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 1807, 496, 1203, 899, size);
				pi++;
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 459, 1455, 1203, 899, size);
				pi++;
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 1807, 1455, 1203, 899, size);
				pi++;
			} catch (IOException e) {
				e.printStackTrace();
			}

			i++;
			System.out.println("load files->" + item);
		}
	}

	public final static String formatInt(int number, int count) {
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(count);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(count);
		return nf.format(number);
	}

	/**
	 * 图像切割(按指定起点坐标和宽高切割)
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param result
	 *            切片后的图像地址
	 * @param x
	 *            目标切片起点坐标X
	 * @param y
	 *            目标切片起点坐标Y
	 * @param width
	 *            目标切片宽度
	 * @param height
	 *            目标切片高度
	 */
	public final static void cut(BufferedImage bi, String result, int x, int y, int width, int height, int size) {
		try {
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度

			double percent = (double) size / (double) width;
			int newWidth = (int) (width * percent);
			int newHeight = (int) (height * percent);

			if (srcWidth > 0 && srcHeight > 0) {
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				lasttimeinit();
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bi.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = tag.createGraphics();
				// 设置“抗锯齿”的属性
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static long time = 0;

	public static void lasttime(int line) {
		long timenow = System.currentTimeMillis();
		long t = timenow - time;
		System.out.println(t + " in line->" + line);
		time = timenow;
	}

	public static void lasttimeinit() {
		time = System.currentTimeMillis();
	}
}
