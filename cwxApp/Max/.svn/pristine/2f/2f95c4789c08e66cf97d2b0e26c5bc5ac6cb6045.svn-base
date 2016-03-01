package com.cwx.max.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

public class FileService {

	public static String saveToSDCard(String name, String content) {

		FileOutputStream fos = null;
		File file = null;

		try {
			file = new File(Environment.getExternalStorageDirectory(), name);
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
		if (file == null) {
			return null;
		}
		return file.getAbsolutePath();
	}

	public void saveToRom(String name, String content) {
		// TODO Auto-generated method stub

	}

}
