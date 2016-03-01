package picture;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CutPictureFrame {

	public static boolean isRunning = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("切图");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);

		final JButton button = new JButton("请将原图片放入source目录内，在编辑框内输入漫画名(英文),然后点我");

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(new Rectangle(0, 100, 680, 200));

		button.setBounds(new Rectangle(0, 0, 680, 100));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning) {
					isRunning = true;
					proName = textArea.getText();
					click(button);
				}
			}
		});
		frame.add(button);
		frame.add(textArea);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setPreferredSize(new Dimension(680, 520));
		int frameWidth = frame.getPreferredSize().width;
		int frameHeight = frame.getPreferredSize().height;
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);

		frame.setResizable(false);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("exit");
			}
		}));

		frame.setVisible(true);
	}

	private static void click(final JButton button) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
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
					button.setText("搜索图片中...");
					ArrayList<String> paths = FileUtils.loadFiles(SOURCE_DIR);
					Collections.sort(paths, new FileCompare());
					size(paths, 170, button);
					size(paths, 230, button);
					size(paths, 310, button);
					cp800(paths, button);
					button.setText("切图完成，请关闭");
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});

				} catch (Exception e) {
					String msg = e.toString();
					for (StackTraceElement item : e.getStackTrace()) {
						msg = msg + "\n" + item.toString();
					}
					button.setText("出错了,错误日志在error目录下");
					FileUtils.saveToFile("error", msg);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					System.out.println(msg);
				}
			}
		};
		thread.start();
	}

	public final static void cp800(ArrayList<String> paths, JButton button) {
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
			button.setText("正在裁切 size->" + "800" + " file->" + item);
		}
	}

	public final static void size(ArrayList<String> paths, int size, JButton button) {
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
				button.setText("正在裁切 size->" + size + " file->" + item + " count->" + pi);
				bi = ImageIO.read(new File(item));
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 459, 496, 1203, 899, size);
				pi++;
				button.setText("正在裁切 size->" + size + " file->" + item + " count->" + pi);
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 1807, 496, 1203, 899, size);
				pi++;
				button.setText("正在裁切 size->" + size + " file->" + item + " count->" + pi);
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 459, 1455, 1203, 899, size);
				pi++;
				button.setText("正在裁切 size->" + size + " file->" + item + " count->" + pi);
				cut(bi, dirOneSection.getAbsolutePath() + "\\" + formatInt(pi, 2) + ".jpg", 1807, 1455, 1203, 899, size);
				pi++;
			} catch (IOException e) {
				e.printStackTrace();
			}

			i++;
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

	private static String proName = "dbt";

	private final static String SOURCE_DIR = "source";
	private final static String RESULT_DIR = "result";

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
