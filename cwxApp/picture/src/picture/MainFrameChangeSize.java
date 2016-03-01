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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.event.IIOWriteProgressListener;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrameChangeSize {

	public static boolean isRunning = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("换图");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(new Rectangle(0, 100, 680, 200));
		frame.add(textArea);

		final JButton button = new JButton("请把源图片放入该文件同位置的source目录内n然后点我,在下面输入框写输出品质(1-100),不想改品质就留空");
		button.setBounds(new Rectangle(0, 0, 680, 100));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning) {
					String text = textArea.getText();
					if (text != null) {
						if (text.equals("")) {
							isRunning = true;
							click(button, -1);
						} else {
							try {
								int q = Integer.parseInt(text);
								if (q <= 0 | q > 100) {
									button.setText("请输入1-100数字或者留空，否则无法执行");
								} else {
									click(button, q);
								}
							} catch (Exception e2) {
								button.setText("请输入1-100数字或者留空，否则无法执行");
							}
						}
					}
				}
			}
		});
		frame.add(button);

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

	private static String filePath = "source";

	private static ArrayList<String> filelist = new ArrayList<String>();

	public static void click(final JButton button, final int quality) {

		Thread thread = new Thread() {
			@Override
			public void run() {

				try {

					button.setText("正在改图，请等待");

					File file = new File("result");
					if (!file.exists()) {
						file.mkdir();
					}

					getFiles(filePath);
					Collections.sort(filelist, new FileCompare());
					sizeThread(170, button, quality);
					sizeThread(230, button, quality);
					sizeThread(310, button, quality);

					button.setText("改图完成，请关闭");
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
					button.setText("出错了,错误日志在/error目录下");
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

	private static void getFiles(String filePath) {
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

	public static void sizeThread(final int size, final JButton button, int quality) {
		lastcount = 0;
		for (String it : filelist) {
			try {
				if (it.endsWith(".jpg")) {
					System.out.println(it);
					button.setText("change size to" + size + " from->" + it);
					resizeImage(new File(it), new File("result"), size, "jpg", quality);
				}
			} catch (Exception e) {
			}
		}
	}

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
	 * @param quality
	 *            输出图片的品质,-1为不修改
	 * @throws IOException
	 */
	public static void resizeImage(File sourceFile, File outDir, int size, String format, int quality) throws IOException {
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
		File dirFile = new File(outDir + "\\" + strs[strs.length - 3].toLowerCase());
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
		System.out.println(dirFile.getAbsolutePath() + "\\" + size);

		File sizeDir = new File(dirFile.getAbsolutePath() + "\\" + size);
		if (!sizeDir.exists()) {
			sizeDir.mkdir();
		}

		File outFile;
		if (lastcount < 10) {
			outFile = new File(dirFile.getAbsolutePath() + "\\" + size + "/0" + lastcount + "." + format);
		} else {
			outFile = new File(dirFile.getAbsolutePath() + "\\" + size + "/" + lastcount + "." + format);
		}
		FileOutputStream fos = new FileOutputStream(outFile);

		if (quality == -1) {
			ImageIO.write(image, format, fos);
		} else {
			writeJPEG(outFile, image, quality, null);
		}

		fos.flush();
		is.close();
		fos.close();
	}

	/**
	 * 保存图像到 JPEG 文件
	 * 
	 * @param file
	 *            保存的目标文件
	 * @param image
	 *            保存的源图像
	 * @param quality
	 *            保存的 JPEG 图像质量
	 * @param listener
	 *            保存进度监听器
	 */
	public static void writeJPEG(File file, BufferedImage image, int quality, IIOWriteProgressListener listener)
			throws FileNotFoundException, IOException {
		Iterator it = ImageIO.getImageWritersBySuffix("jpg");
		if (it.hasNext()) {
			FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
			ImageWriter iw = (ImageWriter) it.next();
			ImageWriteParam iwp = iw.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			iwp.setCompressionQuality((float) quality / 100f);
			iw.setOutput(fileImageOutputStream);
			iw.addIIOWriteProgressListener(listener);
			iw.write(null, new IIOImage(image, null, null), iwp);
			iw.dispose();
			fileImageOutputStream.flush();
			fileImageOutputStream.close();
		}
	}

}
