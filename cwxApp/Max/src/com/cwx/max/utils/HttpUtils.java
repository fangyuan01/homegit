package com.cwx.max.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpUtils {
	/**
	 * 读取数据
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inputStream) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}

		inputStream.close();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 获取网上图片
	 * 
	 * @throws Exception
	 */
	public static byte[] testGetImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(50 * 1000); // 设置链接超时时间6s
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			return readStream(inputStream);
		}
		return null;
	}

	/**
	 * 获取网址的html
	 * 
	 * @throws Exception
	 */
	public static String testGetHtml(String urls) throws Exception {
		Log.v("GetHtml->", urls);
		String urlpath = urls;
		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(50 * 1000); // 设置链接超时时间6s
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			byte[] data = readStream(inputStream);
			String html = new String(data);
			// System.out.println(html);
			return html;
		}
		return null;
	}
}
