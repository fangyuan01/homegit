package com.hbueschoolhelper.utils;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

public class CameraTools {

	public static final String AppPath = "/ZhaikerCache/";

	public static int crop = 180;

	/**
	 * 清空缓存文件
	 */
	public static void deleteCatchFile() {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + AppPath);
		deleteDir(file);
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**
	 * 调取相册并缩放
	 * 
	 * @param activity
	 * @return 图片路径
	 */
	public static String getPhotoFromPhotos(Activity activity, int requestCode) {
		File dirFile = new File(Environment.getExternalStorageDirectory().getPath() + AppPath);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File sdcardTempFile = new File(Environment.getExternalStorageDirectory().getPath() + AppPath, "cache_img_"
				+ SystemClock.currentThreadTimeMillis() + CameraTools.class.hashCode() + "p.jpg");
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", crop);// 输出图片大小
		intent.putExtra("outputY", crop);
		activity.startActivityForResult(intent, requestCode);
		return sdcardTempFile.getPath();
	}

	/**
	 * 调取相机并缩放
	 * 
	 * @param activity
	 * @return
	 */
	public static String getPhotoFromCamera(Activity activity, int requestCode) {
		File dirFile = new File(Environment.getExternalStorageDirectory().getPath() + AppPath);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		File sdcardTempFile = new File(Environment.getExternalStorageDirectory().getPath() + AppPath, "cache_img_"
				+ SystemClock.currentThreadTimeMillis() + CameraTools.class.hashCode() + "c.jpg");
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// File file = new File(Environment.getExternalStorageDirectory()
		// + "/myimage/", String.valueOf(System.currentTimeMillis())
		// + ".jpg");
		// openCameraIntent.putExtra("crop", "true");
		// openCameraIntent.putExtra("aspectX", 1);// 裁剪框比例
		// openCameraIntent.putExtra("aspectY", 1);
		// openCameraIntent.putExtra("outputX", crop);// 输出图片大小
		// openCameraIntent.putExtra("outputY", crop);
		String path = sdcardTempFile.getPath();
		Uri imageUri = Uri.fromFile(sdcardTempFile);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		activity.startActivityForResult(openCameraIntent, requestCode);
		return path;
	}
}
