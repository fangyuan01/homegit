package picture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;

public class FileUtils {

	/**
	 * 获取某一目录(包括其子目录)下所有文件
	 * 
	 * @param path
	 * @return 所有文件路径的list
	 */
	public static ArrayList<String> loadFiles(String path, JButton button) {
		filelist.clear();
		getFiles(path, button);
		ArrayList<String> rt = new ArrayList<>();
		for (String st : filelist) {
			rt.add(st);
		}
		return rt;
	}

	/**
	 * 获取某一目录(包括其子目录)下所有文件
	 * 
	 * @param path
	 * @return 所有文件路径的list
	 */
	public static ArrayList<String> loadFiles(String path) {
		filelist.clear();
		getFiles(path);
		ArrayList<String> rt = new ArrayList<>();
		for (String st : filelist) {
			rt.add(st);
		}
		return rt;
	}

	private static ArrayList<String> filelist = new ArrayList<String>();

	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
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
				filelist.add(file.getAbsolutePath());
			}
		}
	}

	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	private static void getFiles(String filePath, JButton button) {
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
				getFiles(file.getAbsolutePath(), button);
			} else {
				button.setText("search file ->" + file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
			}
		}
	}

	/**
	 * 检查目标路径是否存在，不在则创建
	 * 
	 * @param path
	 *            目标路径
	 */
	public static void existsFile(String path) {
		String[] strs = path.split("\\\\");
		String pathnow = strs[0];
		for (int i = 1; i < strs.length; i++) {
			File file = new File(pathnow);
			if (!file.exists()) {
				file.mkdir();
			}
			pathnow = pathnow + "\\" + strs[i];
		}
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

		existsFile(newPath);

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

	public static void saveToFile(String dir, String content) {
		FileOutputStream fos = null;
		try {
			// Environment.getExternalStorageDirectory()。获取sd卡的路径
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日yyyy年HH时mm分ss秒");
			String fileName = "error_log_" + sdf.format(System.currentTimeMillis()) + ".txt";
			File dirf = new File(dir);
			if (!dirf.exists()) {
				dirf.mkdir();
			}
			File file = new File(dir, fileName);
			fos = new FileOutputStream(file);
			fos.write(content.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
