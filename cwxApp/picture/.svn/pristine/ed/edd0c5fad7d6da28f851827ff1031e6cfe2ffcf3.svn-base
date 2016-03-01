package picture;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame {

	public static boolean isRunning = false;

	public static void main(String[] args) {
		JFrame frame = new JFrame("换图");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);

		final JButton button = new JButton("请将原图片放入C:\\source目录内，然后点我");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning) {
					isRunning = true;
					click(button);
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

	public static void click(final JButton button) {
		Thread thread = new Thread() {

			public void run() {
				button.setText("正在复制，请等待");
				List<String> paths = FileUtils.loadFiles("C:\\source");
				Collections.sort(paths, new FileCompare());
				for (String item : paths) {
					System.out.println(item);
					String[] strs = item.split("\\\\");
					String number = strs[strs.length - 3];
					int sizeInt = Integer.valueOf(strs[strs.length - 2]);

					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(number);
					number = m.replaceAll("").trim();

					int num = Integer.valueOf(number);
					String fileName = strs[strs.length - 1];
					if (num % 2 == 0) {
						int fileNum = Integer.valueOf(fileName.substring(0, fileName.indexOf(".")));
						if (sizeInt != 800) {
							fileNum += 16;
						} else {
							fileNum += 4;
						}
						fileName = new DecimalFormat("00").format(fileNum) + ".jpg";
					}

					if (!item.endsWith("zip")) {
						button.setText("正在复制->" + item);
						String newDir = "C:\\result\\" + strs[strs.length - 4] + "\\" + strs[strs.length - 4]
								+ new DecimalFormat("000").format((num + 1) / 2) + "\\" + strs[strs.length - 2] + "\\" + fileName;
						FileUtils.copyFile(item, newDir);
					}
				}
				button.setText("复制完成，请关闭");
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			};
		};
		thread.start();
	}

}
